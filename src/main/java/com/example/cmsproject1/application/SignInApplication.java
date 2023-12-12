package com.example.cmsproject1.application;

import static com.example.cmsproject1.exception.ErrorCode.LOGIN_CHECK_FAIL;

import com.example.cmsproject1.config.JwtProvider;
import com.example.cmsproject1.domain.SignInForm;
import com.example.cmsproject1.domain.common.UserType;
import com.example.cmsproject1.domain.model.User;
import com.example.cmsproject1.exception.UserException;
import com.example.cmsproject1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

  private final UserService userService;
  private final JwtProvider provider;
  public String userLogInToken(SignInForm form) {
    // 1. 로그인 가능 여부
    User u = userService.findValidUser(form.getUserId(), form.getPassword())
        .orElseThrow(() -> new UserException(LOGIN_CHECK_FAIL));
    // 2. 토큰 발행
    // 3. 토큰을 response
    return provider.createToken(u.getUserId(), u.getId(), UserType.USER);
  }
}
