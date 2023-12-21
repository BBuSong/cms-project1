package com.example.cmsproject1.service;

import com.example.cmsproject1.user.SignUpForm;
import com.example.cmsproject1.user.service.SignUpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SignUpServiceTest {

  @Autowired
  private SignUpService service;
  @Test
  void signUp() {
    SignUpForm form = SignUpForm.builder()
        .name("name")
        .password("1")
        .cardNumber("12312313")
        .build();

    Assert.notNull(service.signUp(form).getId(), "null이 아니다.");
  }
}