package com.example.cmsproject1.basket;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBasketForm {

  private Long id;
  private Long userId;
  private String productName;
  private List<ProductItem> items;

  @Getter
  public static class ProductItem{
    private Long id;
    private String productName;
    private Integer price;
    private Integer deliveryFee;
  }
}
