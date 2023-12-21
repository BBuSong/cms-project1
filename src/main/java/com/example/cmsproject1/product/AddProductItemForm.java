package com.example.cmsproject1.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductItemForm {

  private Long productId;
  private String productName;
  private int price;
  private int deliveryFee;
}
