package com.example.cmsproject1.basket.application;

import static com.example.cmsproject1.product.exception.ErrorCode.NOT_FOUND_PRODUCT;

import com.example.cmsproject1.basket.AddBasketForm;
import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.basket.service.BasketService;
import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.entity.ProductItem;
import com.example.cmsproject1.product.exception.CustomException;
import com.example.cmsproject1.product.service.ProductSearchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

@Service
@RequiredArgsConstructor
public class BasketApplication {

  private final ProductSearchService productSearchService;
  private final BasketService basketService;

  public Basket addBasket(Long userId, AddBasketForm form) {
    Product product = productSearchService.getByProductId(form.getId());
    if (product == null) {
      throw new CustomException(NOT_FOUND_PRODUCT);
    }

    Basket basket = basketService.getBasket(userId);

    return basketService.addBasket(userId, form);
  }

  public Basket updateBasket(Long userId, Basket basket) {
    basketService.putBasket(userId, basket);
    return getBasket(userId);
  }
  public Basket getBasket(Long userId) {
    Basket basket = refreshBasket(basketService.getBasket(userId));
    Basket returnBasket = new Basket();
    returnBasket.setUserId(userId);
    returnBasket.setProducts(basket.getProducts());
    returnBasket.setMessages(basket.getMessages());
    basket.setMessages(new ArrayList<>());

    basketService.putBasket(userId, basket);

    return returnBasket;

  }

  public void clearBasket(Long userId) {
    basketService.putBasket(userId, null);
  }

  private Basket refreshBasket(Basket basket) {

    Map<Long, Product> productMap = productSearchService.getListByProductIds(
        basket.getProducts().stream().map(Basket.Product::getId).collect(Collectors.toList()))
        .stream()
        .collect(Collectors.toMap(Product::getId, product -> product));

    for(int i =0; i < basket.getProducts().size(); i++) {

      Basket.Product basketProduct = basket.getProducts().get(i);
      Product p = productMap.get(basketProduct.getId());

      if(p ==null) {
        basket.getProducts().remove(basketProduct);
        i--;
        basket.addMessage(basketProduct.getProductName() + "상품이 삭제되었습니다.");
        continue;
      }
      Map<Long, ProductItem> productItemMap = p.getProductItems().stream()
          .collect(Collectors.toMap(ProductItem::getId, productItem -> productItem));

      List<String> tmpMessages = new ArrayList<>();

      for(int j = 0; j < basketProduct.getItems().size(); j++) {

        Basket.ProductItem basketProductItem = basketProduct.getItems().get(j);
        ProductItem pi = productItemMap.get(basketProductItem.getId());
        if (pi == null) {
          basketProduct.getItems().remove(basketProductItem);
          j--;
          tmpMessages.add(basketProductItem.getProductName() + "옵션이 삭제되었습니다.");
        }

        boolean isPriceChanged = false, isDeliveryFeeChanged = false;

        if (!basketProductItem.getPrice().equals(productItemMap
            .get(basketProductItem.getId()).getPrice())) {
          isPriceChanged = true;
          basketProductItem.setPrice(pi.getPrice());
        }
        if (basketProductItem.getDeliveryFee().equals(productItemMap
            .get(basketProductItem.getId()).getDeliveryFee())) {
          isDeliveryFeeChanged = true;
          basketProductItem.setDeliveryFee(pi.getDeliveryFee());
        }
        if (isPriceChanged && isDeliveryFeeChanged) {
          //message1
          tmpMessages.add(basketProduct.getProductName() + "가격과 배송비가 변경되었습니다.");
        } else if (isPriceChanged) {
          // message2
          tmpMessages.add(basketProduct.getProductName() + "가격이 변경되었습니다.");
        } else if (isDeliveryFeeChanged) {
          //message3
          tmpMessages.add(basketProduct.getProductName() + "배송비가 변경되었습니다.");
        }
      }
      if (basketProduct.getItems().size() == 0) {
        basket.getProducts().remove(basketProduct);
        i--;
        basket.addMessage(basketProduct.getProductName() + "옵션이 모두 삭제되었습니다.");
      }
      else if(tmpMessages.size() > 0) {
        StringBuilder builder = new StringBuilder();
        builder.append(basketProduct.getProductName() + "상품의 변동 사항 : ");
        for( String message : tmpMessages) {
          builder.append(message);
          builder.append(", ");
        }
        basket.addMessage(builder.toString());
      }
    }
    basketService.putBasket(basket.getUserId(), basket);
    return basket;
  }
}
