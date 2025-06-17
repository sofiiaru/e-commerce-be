package com.shoppingcart.myshop.repository;

import com.shoppingcart.myshop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
