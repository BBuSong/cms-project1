package com.example.cmsproject1.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductItemForm {

  private Long id;
  private String productName;
  private int price;
  private int deliveryFee;

}
