package com.shoppingcart.myshop.controller;

import com.shoppingcart.myshop.exceptions.ResourceNotFoundException;
import com.shoppingcart.myshop.model.Product;
import com.shoppingcart.myshop.request.AddProductRequest;
import com.shoppingcart.myshop.request.UpdateProductRequest;
import com.shoppingcart.myshop.response.ApiResponse;
import com.shoppingcart.myshop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            return ResponseEntity.ok(new ApiResponse("Found!", productService.getAllProducts()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error!", e.getMessage()));
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Found!", product));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Added!", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest product, @PathVariable Long productId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Updated!", productService.updateProduct(product, productId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted!", productId ));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndName(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryNameAndBrand(category, brand);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/name/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand/{brand}")
    public ResponseEntity<ApiResponse> getProductByBrand(@PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category/{category}")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategoryName(category);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name/count")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Found!", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}









