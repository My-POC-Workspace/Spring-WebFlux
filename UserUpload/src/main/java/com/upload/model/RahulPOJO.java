package com.upload.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RahulPOJO {
	
	private String requestPath;
	private MultipartFile multipartFile;

}
