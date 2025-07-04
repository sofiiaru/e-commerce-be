package com.shoppingcart.myshop.service.image;

import com.shoppingcart.myshop.dto.ImageDto;
import com.shoppingcart.myshop.exceptions.ResourceNotFoundException;
import com.shoppingcart.myshop.model.Image;
import com.shoppingcart.myshop.model.Product;
import com.shoppingcart.myshop.repository.ImageRepository;
import com.shoppingcart.myshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final ProductService productService;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with this id"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("No image found with this id");
        });
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files) {
           try {
               Image image = new Image();
               image.setFileName(file.getOriginalFilename());
               image.setFileType(file.getContentType());
               image.setImage(new SerialBlob(file.getBytes()));
               image.setProduct(product);

               String buildDownloadUrl = "/api/v1/images/image/download/";
               String downloadUrl = buildDownloadUrl + image.getId();
               image.setDownloadUrl(downloadUrl);

               Image savedImage = imageRepository.save(image);
               savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
               imageRepository.save(image);

               ImageDto imageDTO = new ImageDto();
               imageDTO.setId(savedImage.getId());
               imageDTO.setImageName(savedImage.getFileName());
               imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
               savedImageDto.add(imageDTO);

           } catch(IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
           }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
