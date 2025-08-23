package com.ferhatayar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ferhatayar.controller.IRestCarImageController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoCarImages;
import com.ferhatayar.service.ICarImagesService;

@RestController
@RequestMapping("/rest/api/car-images")
public class RestCarImageControllerImpl extends RestBaseController implements IRestCarImageController{

	@Autowired
	private ICarImagesService carImagesService;
	
	@GetMapping("/{carId}")
	@Override
	public RootEntity<List<DtoCarImages>> getImagesByCarId(@PathVariable(name = "carId") Long carId) {
		return ok(carImagesService.getImagesByCarId(carId));
	}

	@PostMapping
	public RootEntity<DtoCarImages> saveOrUpdateImage(
	        @RequestParam("carId") Long carId,
	        @RequestParam("imageFile") MultipartFile imageFile,
	        @RequestParam(value = "imageId", required = false) Long imageId
	) {
	    DtoCarImages saved = carImagesService.saveOrUpdateImage(carId, imageFile, imageId);
	    return RootEntity.ok(saved);
	}

	@DeleteMapping("/delete/{imageId}")
	@Override
	public ResponseEntity<Void> deleteImage(@PathVariable(name = "imageId") Long imageId) {
		carImagesService.deleteImage(imageId);
		return ResponseEntity.noContent().build();
	}

}
