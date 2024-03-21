package com.upload.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.upload.model.User;
import com.upload.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileUploadService fileUploadService;

	public Mono<User> saveUser(User user, String requestPath, MultipartFile multipartFile) throws IOException {
		return null;
//		
//		Mono<User> save = userRepository.save(user);
//		Mono<User> findById = userRepository.findById(save.map(e -> e.getId()));

//		return fileUploadService.uploadPhoto(requestPath, multipartFile).map(filepath -> {
//			user.setFilePath(filepath);
//			return user;
//		}).flatMap(userRepository::save);
		
//		return fiMono.doOnNext(fp -> System.out.println(fp.filename()))
//				.flatMap(fp -> fp.transferTo(Paths.get(path).resolve(fp.filename())))
//				.then();
		
		
//
	}
	
	
//	public Mono<User> saveU(User user, String requestPath, MultipartFile multipartFile){
//		fileUploadService.uploadPhoto(requestPath, multipartFile);
//		user.setFilePath(requestPath);
//		return userRepository.save(user);
//		
//	}
	
//	public Mono<User> saveUser(User user, String requestPath, MultipartFile multipartFile){
//	
//	}

}
	
