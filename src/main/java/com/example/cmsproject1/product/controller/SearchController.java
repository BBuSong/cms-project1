package com.example.cmsproject1.product.controller;

import com.example.cmsproject1.product.dto.ProductDto;
import com.example.cmsproject1.product.service.ProductSearchService;
import com.example.cmsproject1.user.config.JwtProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search/product")
@RequiredArgsConstructor
public class SearchController {

  private final ProductSearchService productSearchService;

  @GetMapping
  public ResponseEntity<List<ProductDto>> searchByName(@RequestParam String productName) {
    return ResponseEntity.ok(
        productSearchService.searchByName(productName).stream()
            .map(ProductDto::withoutItemsFrom).collect(Collectors.toList())
    );
  }

  @GetMapping("/detail")
  public ResponseEntity<ProductDto> getDetail(@RequestParam Long productId) {
    return ResponseEntity.ok(
        ProductDto.from(
            productSearchService.getByProductId(productId)
        )
    );
  }
}
