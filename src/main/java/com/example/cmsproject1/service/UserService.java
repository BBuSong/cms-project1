package com.example.cmsproject1.service;

import com.example.cmsproject1.domain.model.User;
import com.example.cmsproject1.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Optional<User> findByIdAndUserId(Long id, String userId) {
    return userRepository.findById(id)
        .stream().filter(user -> user.getUserId().equals(userId))
        .findFirst();
  }

  public Optional<User> findValidUser(String userId, String password) {
    return userRepository.findByUserId(userId)
        .stream()
        .filter(
        user -> user.getPassword().equals(password))
        .findFirst();
  }
}
