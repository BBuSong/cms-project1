package com.example.cmsproject1.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

  private String userId;
  private String name;
  private String password;
  private String cardNumber;
}