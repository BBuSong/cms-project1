package com.example.cmsproject1.user.exception;

import com.example.cmsproject1.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
  private final ErrorCode errorCode;

  public UserException(ErrorCode errorCode) {
    super(errorCode.getDetail());
    this.errorCode = errorCode;
  }
}
