package com.example.cmsproject1.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  // signup
  ALREADY_REGISTER_ID(HttpStatus.BAD_REQUEST, "이미 가입된 아이디입니다."),

  // login
  LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디 혹은 비밀번호를 확인해주세요."),
  NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "해당 이용자를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String detail;
}
