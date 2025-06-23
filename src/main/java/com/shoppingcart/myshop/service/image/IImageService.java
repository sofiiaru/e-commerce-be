package com.shoppingcart.myshop.service.image;

import com.shoppingcart.myshop.dto.ImageDto;
import com.shoppingcart.myshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);

}
