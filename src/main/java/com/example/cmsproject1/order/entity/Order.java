package com.example.cmsproject1.order.entity;

import com.example.cmsproject1.order.OrderForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer orderNumber;
  private Integer allPrice;
  private String cardNumber;
  private String password;
  private String userId;
  private Long productId;

  public static Order from(OrderForm form) {
    return Order.builder()
        .cardNumber(form.getCardNumber())
        .password(form.getPassword())
        .build();
  }
}
