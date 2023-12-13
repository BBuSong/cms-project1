package com.example.cmsproject1.basket.controller;

import com.example.cmsproject1.basket.AddBasketForm;
import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.basket.application.BasketApplication;
import com.example.cmsproject1.basket.service.BasketService;
import com.example.cmsproject1.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/basket")
@RequiredArgsConstructor
public class BasketController {

  private final BasketApplication basketApplication;
  private final JwtProvider provider;

  @PostMapping
  public ResponseEntity<Basket> addBasket(
      @RequestHeader(name = "X_AUTH_TOKEN") String token,
      @RequestBody AddBasketForm form) {
    return ResponseEntity.ok(basketApplication.addBasket(provider.getUserVo(token).getId(),form));
  }
}
