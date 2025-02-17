package com.upload.model;

import java.nio.file.Path;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class User {
	
	@Id
	private String id;
	private String name;
	private int age;
	private String PanNumber;
	
	private String filePath;
	

}
