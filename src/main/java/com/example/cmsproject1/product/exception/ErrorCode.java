package com.example.cmsproject1.product.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

  NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다."),
  NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST, "아이템을 찾을 수 없습니다."),
  SAME_ITEM_NAME(HttpStatus.BAD_REQUEST, "아이템 명 중복입니다."),

  BASKET_CHANGE_FAIL(HttpStatus.BAD_REQUEST, "장바구니에 추가할 수 없습니다."),

  ORDER_FAIL_CHECK_BASKET(HttpStatus.BAD_REQUEST, "장바구니를 확인해 주세요."),

  NOT_CARD_NUMBER(HttpStatus.BAD_REQUEST, "카드 번호가 일치하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String detail;

}
