package com.upload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.model.RahulPOJO;

import reactor.core.publisher.Mono;

@Service
public class FileUploadService {

	public Mono<String> uploadPhoto(String path, MultipartFile file) throws IOException {

//		return Mono.fromCallable(() -> {
//			String originalFilename = rahul.getMultipartFile().getOriginalFilename();
//			
//			String uploadPath = rahul.getRequestPath() + File.separator + originalFilename;
//			
//			File file = new File(uploadPath);
//			if(!file.exists()) {
//				file.mkdir();
//			}
//			
//			
//			Files.copy(rahul.getMultipartFile().getInputStream(), Paths.get(uploadPath));
//			
//			
//			return uploadPath;
//		});

		return Mono.fromCallable(()->{
			String originalFileName = file.getOriginalFilename();

			// Where To upload...
			String filePath = path + File.separator + originalFileName;

			// Create Folder if not created...
			File file2 = new File(path);
			if (!file2.exists()) {
				file2.mkdir();
			}

//			File Copy...
			Files.copy(file.getInputStream(), Paths.get(filePath));

			return originalFileName;
		});
//		

		
//	return null;	
	}

}


//public String uploadImage(String path, MultipartFile file) throws IOException {
//	
//	// Original File name to be copied...
//	String originalFileName = file.getOriginalFilename(); 
//	
//	// Where To upload...
//	String filePath  = path + File.separator+originalFileName; 
//	
//	// Create Folder if not created...
//	File file2 = new File(path);
//	if(!file2.exists()) {
//		file2.mkdir();
//	}
//	
////	File Copy...
//	Files.copy(file.getInputStream(), Paths.get(filePath));
//	
//	return originalFileName;
//	
//}
