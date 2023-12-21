package com.example.cmsproject1.product.exception;

import com.example.cmsproject1.product.exception.CustomException.CustomExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<CustomException.CustomExceptionResponse> exceptionHandler(
      HttpServletRequest req, final CustomException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(CustomException.CustomExceptionResponse.builder()
            .message(e.getMessage())
            .code(e.getErrorCode().name())
            .status(e.getStatus())
            .build());
  }
}
