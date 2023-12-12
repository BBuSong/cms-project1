package com.example.cmsproject1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  ALREADY_REGISTER_ID(HttpStatus.BAD_REQUEST, "이미 가입된 아이디입니다.");

  private final HttpStatus httpStatus;
  private final String detail;
}
