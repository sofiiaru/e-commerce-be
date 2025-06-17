package com.shoppingcart.myshop.repository;

import com.shoppingcart.myshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category finaByName(String name);

    Category findByName(String name);

    boolean existsByName(String name);
}
