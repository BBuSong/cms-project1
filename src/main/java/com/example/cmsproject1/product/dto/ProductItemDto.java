package com.example.cmsproject1.product.dto;

import com.example.cmsproject1.product.entity.ProductItem;
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
public class ProductItemDto {

  private Long id;
  private String productName;
  private Integer price;
  private Integer deliveryFee;

  public static ProductItemDto from(ProductItem item) {
    return ProductItemDto.builder()
        .id(item.getId())
        .productName(item.getProductName())
        .price(item.getPrice())
        .deliveryFee(item.getDeliveryFee())
        .build();
  }
}
