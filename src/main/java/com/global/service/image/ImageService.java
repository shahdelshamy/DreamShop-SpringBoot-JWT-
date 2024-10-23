package com.global.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.global.DTO.ImageDto;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Image;
import com.global.models.Product;
import com.global.repositories.ImageRepository;
import com.global.service.product.ProductService;

@Service
public class ImageService implements ImageInterface {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Image getImageById(int id) {
		return imageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("The Image Not Found"));
	}

	@Override
	public void deleteImageById(int id) {
		imageRepository.deleteById(id);
	}

	@Override
	public List<ImageDto> saveImages(int productId, List<MultipartFile> files) throws SerialException, SQLException, IOException {
		Product product=productService.getProductById(productId);
		List<ImageDto>images=new ArrayList<>();
		
		for(MultipartFile file: files) {
			Image image=new Image();
			image.setFileName(file.getOriginalFilename());
			image.setFileType(file.getContentType());
			image.setImage(new SerialBlob(file.getBytes()));
			String buildUrl ="api/v1/images/image/download/";
			String url=buildUrl+image.getId();
			Image savedImage=imageRepository.save(image);
			String originUrl=buildUrl+savedImage.getId();
			imageRepository.save(savedImage);
			
			ImageDto imageDto=new ImageDto();
			imageDto.setFileName(savedImage.getFileName());
			imageDto.setDownloadUrlString(savedImage.getDownloadUrl());
			imageDto.setId(savedImage.getId());
			
			images.add(imageDto);
		}	
		return images;
	}

	@Override
	public ImageDto updateImage(MultipartFile file, int imageId) throws SerialException, SQLException, IOException {
		Image image =this.getImageById(imageId);
		image.setFileName(file.getOriginalFilename());
		image.setFileType(file.getContentType());
		image.setImage(new SerialBlob(file.getBytes()));
		Image imageSaved=imageRepository.save(image);
		
		ImageDto imaegDto=new ImageDto();
		imaegDto.setId(imageSaved.getId());
		imaegDto.setFileName(imageSaved.getFileName());
		imaegDto.setDownloadUrlString(imageSaved.getDownloadUrl());
		return imaegDto;
	}

}
