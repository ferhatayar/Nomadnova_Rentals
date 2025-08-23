package com.ferhatayar.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCarImageIU {

	 private Long carId;  
	 
	 private MultipartFile imageFile;    
	
}
