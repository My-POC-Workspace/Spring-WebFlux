package com.upload.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.FixedKeySet;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.model.RahulPOJO;
import com.upload.model.User;
import com.upload.repository.UserRepository;
import com.upload.service.FileUploadService;
import com.upload.service.UserServiceImpl;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private FileUploadService fileUploadService;

//	private Path basePath = Paths.get("C:/UploadedOnNew1/");

//	@Value("${project.upload}")
//	private String path;
//	
	@Autowired
	private UserRepository userRepository;

//	@PostMapping("/addUser")
//	public Mono<User> addUser(@RequestBody User user, @RequestParam("requestPath") String requestPath,
//			@RequestParam("multipartFile") MultipartFile multipartFile) {
//
//		return userServiceImpl.saveUser(user, requestPath, multipartFile);
//
//	}
//	
	@PostMapping(value = "/uploadPhoto", consumes = {
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
	public String fileUpload(@RequestParam String requestPath, @RequestParam MultipartFile multipartFile) {
		System.out.println("in upload files...");
		try {
			fileUploadService.uploadPhoto(requestPath, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "File uploaded successfully....";
	}

//	
//	@PostMapping(value = "/uploadPhoto"/* ,consumes = {org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE} */)
//	public String fileUpload(@RequestBody RahulPOJO rahul) {
//		System.out.println("in upload files...");
//		try {
//			fileUploadService.uploadPhoto(rahul);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "File uploaded successfully....";

//	}

//	return fileUploadService.uploadPhoto(requestPath, multipartFile).map(filepath -> {
//		user.setFilePath(filepath);
//		return user;
//	}).flatMap(userRepository::save);

//	@PostMapping( "/upload")
//	public void upload( @RequestPart("path") String path ,@RequestPart("fiMono") Mono<FilePart> fiMono, @RequestPart("user") User user){
////			fiMono.flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename()))).then();
//		fiMono
//		.flatMap(f -> {	
////		fp.transferTo(Paths.get(path).resolve(fp.filename()));
//
//		f.transferTo(Paths.get(path).resolve(f.filename()));
////		System.out.println(f.filename());
//		user.setFilePath(path);
//		return userRepository.save(user);	
//		});	

//	
	@PostMapping("/upload")
	public Mono<User> upload(@RequestPart("path") String path, @RequestPart("fiMono") Mono<FilePart> fiMono,
			@RequestPart("user") User user) {

		return fiMono.flatMap(fp -> {
			fp.transferTo(Paths.get(path).resolve(fp.filename()));
			File file = new File(path);
			if (file.exists()) {
				System.out.println("In file.exist()");
				fp.transferTo(Paths.get(path).resolve(fp.filename()));
				user.setFilePath(path);
			} else {
				System.out.println("In !file.exist()");
				file.mkdir();
				fp.transferTo(Paths.get(path).resolve(fp.filename()));
				user.setFilePath(path + file);
			}
			return userRepository.save(user);
		}).doOnError(err -> log.error("Error occured -->" + err));

	}

//	return fiMono.doOnNext(f -> f.filename())
//			.flatMap(fp -> {
//				fp.transferTo(Paths.get(path).resolve(fp.filename()));
//				user.setFilePath(Paths.get(path));
//				return userRepository.save(user);
//			})
//			.doOnError(err -> log.error("Error occured -->" + err));
	
	
	

}
