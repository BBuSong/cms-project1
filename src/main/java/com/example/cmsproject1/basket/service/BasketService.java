package com.example.cmsproject1.basket.service;

import com.example.cmsproject1.basket.AddBasketForm;
import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.basket.client.RedisClient;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketService {

  private final RedisClient redisClient;

  public Basket getBasket(Long userId) {
    return redisClient.get(userId, Basket.class);
  }

  public Basket addBasket(Long userId, AddBasketForm form){

    Basket basket = redisClient.get(userId, Basket.class);
    if(basket == null) {
      basket = new Basket();
      basket.setUserId(userId);
    }

    //이전에 같은 상품이 있는지 검증
    Optional<Basket.Product> productOptional = basket.getProducts().stream()
        .filter(product1 -> product1.getId().equals(form.getId()))
        .findFirst();

    if(productOptional.isPresent()) {
      Basket.Product redisProduct = productOptional.get();
      List<Basket.ProductItem> items = form.getItems().stream().map(Basket.ProductItem::from).
          collect(Collectors.toList());
      Map<Long, Basket.ProductItem> redisItemMap = redisProduct.getItems().stream()
          .collect(Collectors.toMap(Basket.ProductItem::getId, it -> it));

      if(!redisProduct.getProductName().equals(form.getProductName())){
        basket.addMessage(redisProduct.getProductName()+ "의 정보가 변경되었습니다.");
      }
      for(Basket.ProductItem item : items) {
        Basket.ProductItem redisItem = redisItemMap.get(item.getId());

        if(redisItem == null) {
          redisProduct.getItems().add(item);
        } else {
          if (!redisItem.getPrice().equals(item.getPrice())) {
            basket.addMessage(redisProduct.getProductName() + item.getProductName()
             + "의 가격이 변경되었습니다.");
          }
          redisItem.setDeliveryFee(redisItem.getDeliveryFee() + item.getDeliveryFee());
        }
      }
    } else {
      Basket.Product product = Basket.Product.from(form);
      basket.getProducts().add(product);
    }
    redisClient.put(userId, basket);
    return basket;
  }

}
