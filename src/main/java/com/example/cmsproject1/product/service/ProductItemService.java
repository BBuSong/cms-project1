package com.example.cmsproject1.product.service;

import static com.example.cmsproject1.product.exception.ErrorCode.NOT_FOUND_ITEM;
import static com.example.cmsproject1.product.exception.ErrorCode.NOT_FOUND_PRODUCT;
import static com.example.cmsproject1.product.exception.ErrorCode.SAME_ITEM_NAME;

import com.example.cmsproject1.product.AddProductItemForm;
import com.example.cmsproject1.product.UpdateProductItemForm;
import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.entity.ProductItem;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.repository.ProductItemRepository;
import com.example.cmsproject1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductItemService {

  private final ProductRepository productRepository;
  private final ProductItemRepository productItemRepository;

  @Transactional
  public Product addProductItem(Long userId, AddProductItemForm form) {
    Product product = productRepository.findByUserIdAndId(userId, form.getProductId())
        .orElseThrow(() -> new CustomException(NOT_FOUND_PRODUCT));
    if(product.getProductItems().stream()
        .anyMatch(item -> item.getProductName().equals(form.getProductName()))) {
      throw new CustomException(SAME_ITEM_NAME);
    }

    ProductItem productItem = ProductItem.of(userId, form);
    product.getProductItems().add(productItem);

    return product;

  }

  @Transactional
  public ProductItem updateProductItem(Long userId, UpdateProductItemForm form) {
    ProductItem productItem = productItemRepository.findById(form.getId())
        .filter(pi->pi.getUserId().equals(userId))
        .orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
    productItem.setProductName(form.getProductName());
    productItem.setPrice(form.getPrice());
    productItem.setDeliveryFee(form.getDeliveryFee());

    return productItem;
  }

  @Transactional
  public void deleteProductItem(Long userId, Long productItemId) {
    ProductItem productItem = productItemRepository.findById(productItemId)
        .filter(pi->pi.getUserId().equals(userId))
        .orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
    productItemRepository.delete(productItem);
  }
}
