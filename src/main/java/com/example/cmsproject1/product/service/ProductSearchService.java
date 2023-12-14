package com.example.cmsproject1.product.service;

import static com.example.cmsproject1.product.exception.ErrorCode.NOT_FOUND_PRODUCT;

import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

  private final ProductRepository productRepository;

  public List<Product> searchByName(String productName) {
    return productRepository.searchByName(productName);
  }
  public Product getByProductId(Long productId) {
    return productRepository.findWithProductItemsById(productId)
        .orElseThrow(() -> new CustomException(NOT_FOUND_PRODUCT));
  }

  public List<Product> getListByProductIds(List<Long> productIds) {
    return productRepository.findAllByIdIn(productIds);
  }

}
