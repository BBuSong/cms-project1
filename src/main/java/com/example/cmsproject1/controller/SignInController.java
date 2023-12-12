package com.example.cmsproject1.controller;

import com.example.cmsproject1.application.SignInApplication;
import com.example.cmsproject1.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/signIn")
public class SignInController {

  private final SignInApplication signInApplication;

  @PostMapping("/user")
  public ResponseEntity<String> signIn(@RequestBody SignInForm form) {
    return ResponseEntity.ok(signInApplication.userLogInToken(form));
  }

}
