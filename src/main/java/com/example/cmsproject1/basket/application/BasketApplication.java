package com.example.cmsproject1.basket.application;

import static com.example.cmsproject1.product.exception.ErrorCode.NOT_FOUND_PRODUCT;

import com.example.cmsproject1.basket.AddBasketForm;
import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.basket.service.BasketService;
import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.service.ProductSearchService;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketApplication {

  private final ProductSearchService productSearchService;
  private final BasketService basketService;

  public Basket addBasket(Long userId, AddBasketForm form) {
    Product product = productSearchService.getByProductId(form.getId());
    if(product == null) {
      throw new CustomException(NOT_FOUND_PRODUCT);
    }

    Basket basket = basketService.getBasket(userId);

    return basketService.addBasket(userId,form);

  }
}
