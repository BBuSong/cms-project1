package com.example.cmsproject1.basket.application;


import com.example.cmsproject1.product.repository.ProductRepository;
import com.example.cmsproject1.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasketApplicationTest {

  @Autowired
  private BasketApplication basketApplication;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductRepository productRepository;


}