package com.example.cmsproject1.order.service;

import com.example.cmsproject1.order.OrderForm;
import com.example.cmsproject1.order.entity.Order;
import com.example.cmsproject1.order.repository.OrderRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public Order purchase(OrderForm form) {
    return orderRepository.save(Order.from(form));
  }

  public boolean isCardNumberExist(String cardNumber) {
    return orderRepository.findByCardNumber(cardNumber.toLowerCase(Locale.ROOT))
        .isPresent();
  }
}
