package com.example.cmsproject1.product.controller;

import com.example.cmsproject1.product.AddProductForm;
import com.example.cmsproject1.product.AddProductItemForm;
import com.example.cmsproject1.product.UpdateProductForm;
import com.example.cmsproject1.product.UpdateProductItemForm;
import com.example.cmsproject1.product.dto.ProductDto;
import com.example.cmsproject1.product.dto.ProductItemDto;
import com.example.cmsproject1.product.service.ProductItemService;
import com.example.cmsproject1.product.service.ProductService;
import com.example.cmsproject1.user.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductItemService productItemService;
  private final JwtProvider provider;

  @PostMapping
  public ResponseEntity<ProductDto> addProduct(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody AddProductForm form) {

    return ResponseEntity.ok(ProductDto.from(productService.addProduct(provider.getUserVo(token).getId(),form)));
  }

  @PostMapping("/item")
  public ResponseEntity<ProductDto> addProductItem(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody AddProductItemForm form) {

    return ResponseEntity.ok(ProductDto.from(productItemService.addProductItem(provider.getUserVo(token).getId(),form)));
  }

  @PutMapping
  public ResponseEntity<ProductDto> updateProduct(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody UpdateProductForm form) {

    return ResponseEntity.ok(ProductDto.from(productService.updateProduct(provider.getUserVo(token).getId(),form)));
  }

  @PutMapping("/item")
  public ResponseEntity<ProductItemDto> updateProductItem(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody UpdateProductItemForm form) {

    return ResponseEntity.ok(ProductItemDto.from(productItemService.updateProductItem(provider.getUserVo(token).getId(),form)));
  }

 @DeleteMapping
  public ResponseEntity<Void> deleteProduct(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestParam Long id) {
    productService.deleteProduct(provider.getUserVo(token).getId(), id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/item")
  public ResponseEntity<Void> deleteProductItem(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestParam Long id) {
    productItemService.deleteProductItem(provider.getUserVo(token).getId(),id);
    return ResponseEntity.ok().build();
  }
}
