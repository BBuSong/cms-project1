package com.example.cmsproject1.application;

import com.example.cmsproject1.domain.SignUpForm;
import com.example.cmsproject1.domain.model.User;
import com.example.cmsproject1.exception.ErrorCode;
import com.example.cmsproject1.exception.UserException;
import com.example.cmsproject1.service.SignUpService;
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
