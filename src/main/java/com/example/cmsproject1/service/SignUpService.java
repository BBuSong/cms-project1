package com.example.cmsproject1.service;

import com.example.cmsproject1.domain.SignUpForm;
import com.example.cmsproject1.domain.model.User;
import com.example.cmsproject1.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final UserRepository userRepository;
  public User signUp(SignUpForm form) {

    return userRepository.save(User.from(form));

  }
}
