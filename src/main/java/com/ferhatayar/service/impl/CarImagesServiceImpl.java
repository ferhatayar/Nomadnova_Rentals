package com.ferhatayar.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ferhatayar.dto.DtoCarImageIU;
import com.ferhatayar.dto.DtoCarImages;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.CarImages;
import com.ferhatayar.model.Cars;
import com.ferhatayar.repository.CarImagesRepository;
import com.ferhatayar.repository.CarRepository;
import com.ferhatayar.service.FileStorageService;
import com.ferhatayar.service.ICarImagesService;

@Service
public class CarImagesServiceImpl implements ICarImagesService{

	@Autowired
	private CarImagesRepository carImagesRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	
	@Override
	public List<DtoCarImages> getImagesByCarId(Long carId) {
        List<CarImages> images = carImagesRepository.findByCarId(carId);
        return images.stream().map(img -> {
            DtoCarImages dto = new DtoCarImages();
            dto.setId(img.getId());
            dto.setImageUrl(img.getImageUrl());
            return dto;
        }).collect(Collectors.toList());
    }
	
	@Transactional
	@Override
    public DtoCarImages saveOrUpdateImage(Long carId, MultipartFile imageFile, Long imageId) {
		Cars car = carRepository.findById(carId)
	            .orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.CAR_NOT_FOUND,carId.toString())));

	    CarImages carImage;
	    if (imageId != null) {
	        carImage = carImagesRepository.findById(imageId)
	                .orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.IMAGE_NOT_FOUND,imageId.toString())));
	        fileStorageService.deleteFile(carImage.getImageUrl());
	    } else {
	        carImage = new CarImages();
	        carImage.setCar(car);
	    }

	    String imageUrl = fileStorageService.storeFile(imageFile);
	    carImage.setImageUrl(imageUrl);

	    CarImages saved = carImagesRepository.save(carImage);

	    DtoCarImages dto = new DtoCarImages();
	    dto.setId(saved.getId());
	    dto.setImageUrl(saved.getImageUrl());
	    return dto;
    }
	
	@Transactional
	@Override
    public void deleteImage(Long imageId) {
        CarImages carImage = carImagesRepository.findById(imageId)
                .orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.IMAGE_NOT_FOUND,imageId.toString())));

        fileStorageService.deleteFile(carImage.getImageUrl());

        carImagesRepository.delete(carImage);
    }
	
}
