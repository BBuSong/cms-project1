package com.example.cmsproject1.user.application;

import com.example.cmsproject1.user.SignUpForm;
import com.example.cmsproject1.user.entity.User;
import com.example.cmsproject1.user.exception.ErrorCode;
import com.example.cmsproject1.user.exception.UserException;
import com.example.cmsproject1.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

  private SignUpService signUpService;

  public String userSignUp(SignUpForm form) {
    if(signUpService.isUserIdExist(form.getUserId())) {
      throw new UserException(ErrorCode.ALREADY_REGISTER_ID);
    }
    else {
      User u = signUpService.signUp(form);
      return "회원 가입에 성공하였습니다.";
    }
  }

}
