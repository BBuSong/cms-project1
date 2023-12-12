package com.example.cmsproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

  @ExceptionHandler({UserException.class})
  public ResponseEntity<ExceptionResponse> userRequestException(final UserException u) {
    log.warn("api Exception : {}", u.getErrorCode());
    return ResponseEntity.badRequest().body(new ExceptionResponse(u.getMessage(), u.getErrorCode()));
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class ExceptionResponse {

    private String message;
    private ErrorCode errorCode;

  }

}
