package com.example.cmsproject1.order.dto;

import com.example.cmsproject1.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

  private Long id;

  private Integer allPrice;
  private Long productId;

  public static OrderDto from(Order order) {
    return new OrderDto(order.getId(), order.getAllPrice(), order.getProductId());
  }
}
