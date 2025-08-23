package com.ferhatayar.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ferhatayar.dto.DtoCarImages;

public interface ICarImagesService {

	public List<DtoCarImages> getImagesByCarId(Long carId);
	
	public DtoCarImages saveOrUpdateImage(Long carId, MultipartFile imageFile, Long imageId);
	
	public void deleteImage(Long imageId);
	
}
