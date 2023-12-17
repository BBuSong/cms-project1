package com.example.cmsproject1.order.application;

import static com.example.cmsproject1.product.exception.ErrorCode.ORDER_FAIL_CHECK_BASKET;

import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.basket.application.BasketApplication;
import com.example.cmsproject1.order.OrderForm;
import com.example.cmsproject1.order.entity.Order;
import com.example.cmsproject1.order.service.OrderService;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApplication {

  private OrderService orderService;

  public String purchase(OrderForm form) {
    if (orderService.isCardNumberExist(form.getCardNumber())) {
      throw new CustomException(ErrorCode.NOT_CARD_NUMBER);
    }
    else {
      Order o = orderService.purchase(form);
      return "구매 성공하였습니다.";
    }
  }
}
