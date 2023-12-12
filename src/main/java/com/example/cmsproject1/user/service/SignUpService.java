package com.example.cmsproject1.user.service;

import com.example.cmsproject1.user.SignUpForm;
import com.example.cmsproject1.user.entity.User;
import com.example.cmsproject1.user.repository.UserRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final UserRepository userRepository;
  public User signUp(SignUpForm form) {

    return userRepository.save(User.from(form));

  }

  public boolean isUserIdExist(String userId) {
    return userRepository.findByUserId(userId.toLowerCase(Locale.ROOT))
        .isPresent();
  }


}
