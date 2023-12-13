package com.example.cmsproject1.basket;

import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Basket {

  @Id
  private Long userId;
  private List<Product> products = new ArrayList<>();
  private List<String> messages = new ArrayList<>();

  public void addMessage(String message) {
    messages.add(message);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Product{
    private Long id;
    private Long userId;
    private String productName;
    private List<ProductItem> items = new ArrayList<>();

    public static Product from(AddBasketForm form) {
      return Product.builder()
          .id(form.getId())
          .userId(form.getUserId())
          .productName(form.getProductName())
          .items(form.getItems().stream().map(ProductItem::from).collect(Collectors.toList()))
          .build();
    }
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ProductItem{
    private Long id;
    private String productName;
    private Integer price;
    private Integer deliveryFee;

    public static ProductItem from(AddBasketForm.ProductItem form) {
      return ProductItem.builder()
          .id(form.getId())
          .productName(form.getProductName())
          .price(form.getPrice())
          .deliveryFee(form.getDeliveryFee())
          .build();
    }
  }

}
