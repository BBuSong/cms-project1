package com.example.cmsproject1.product.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cmsproject1.product.AddProductForm;
import com.example.cmsproject1.product.AddProductItemForm;
import com.example.cmsproject1.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductRepository productRepository;

  @Test
  void ADD_PROJECT_TEST() {
    Long userId = 1L;
  }


  private static AddProductItemForm makeProductItemForm(Long productId, String productName) {
    return AddProductItemForm.builder()
        .productId(productId)
        .productName(productName)
        .price(10000)
        .deliveryFee(3000)
        .build();
  }
}