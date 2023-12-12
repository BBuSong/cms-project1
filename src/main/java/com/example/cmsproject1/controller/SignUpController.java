package com.example.cmsproject1.controller;

import com.example.cmsproject1.application.SignUpApplication;
import com.example.cmsproject1.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpApplication signUpApplication;



  @PostMapping
  public ResponseEntity<String> userSignUp(@RequestBody SignUpForm form) {
    return ResponseEntity.ok(signUpApplication.userSignUp(form));
  }
}
