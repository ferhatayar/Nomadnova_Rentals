package com.ferhatayar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ferhatayar.dto.DtoCarImages;

public interface IRestCarImageController {

	public RootEntity<List<DtoCarImages>> getImagesByCarId(Long carId);
	
	public RootEntity<DtoCarImages> saveOrUpdateImage(Long carId, MultipartFile imageFile, Long imageId);
	
	public ResponseEntity<Void> deleteImage(Long imageId);
	
}
