package com.example.cmsproject1.product.service;

import com.example.cmsproject1.product.AddProductForm;
import com.example.cmsproject1.product.UpdateProductForm;
import com.example.cmsproject1.product.UpdateProductItemForm;
import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.entity.ProductItem;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.exception.ErrorCode;
import com.example.cmsproject1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public Product addProduct(Long userId, AddProductForm form) {
    return productRepository.save(Product.of(userId, form));
  }

  @Transactional
  public Product updateProduct(Long userId, UpdateProductForm form) {
    Product product = productRepository.findByUserIdAndId(userId, form.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    product.setProductName(form.getProductName());

    for(UpdateProductItemForm itemForm : form.getItems()) {
      ProductItem item = product.getProductItems().stream()
          .filter(pi -> pi.getUserId().equals(itemForm.getId()))
          .findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ITEM));
      item.setProductName(itemForm.getProductName());
      item.setPrice(itemForm.getPrice());
      item.setDeliveryFee(itemForm.getDeliveryFee());
    }
    return product;
  }

  @Transactional
  public void deleteProduct(Long userId,Long productId) {
    Product product = productRepository.findByUserIdAndId(userId, productId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

    productRepository.delete(product);
  }
}
