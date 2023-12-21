package com.example.cmsproject1.product.dto;

import com.example.cmsproject1.product.entity.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Long id;
  private String productName;
  private List<ProductItemDto> items;

  public static ProductDto from(Product product) {
    List<ProductItemDto> items = product.getProductItems()
        .stream().map(ProductItemDto::from).collect(Collectors.toList());

    return ProductDto.builder()
        .id(product.getId())
        .productName(product.getProductName())
        .items(items)
        .build();
  }
}
