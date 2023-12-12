package com.example.cmsproject1.controller;

import static com.example.cmsproject1.exception.ErrorCode.NOT_FOUND_USER;

import com.example.cmsproject1.config.JwtProvider;
import com.example.cmsproject1.domain.common.UserVo;
import com.example.cmsproject1.domain.model.User;
import com.example.cmsproject1.domain.repository.UserRepository;
import com.example.cmsproject1.domain.user.UserDto;
import com.example.cmsproject1.exception.UserException;
import com.example.cmsproject1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final JwtProvider provider;
  private final UserService userService;
  @GetMapping("/getInfo")
  public ResponseEntity<UserDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
    UserVo vo = provider.getUserVo(token);
    User u = userService.findByIdAndUserId(vo.getId(), vo.getUserId())
        .orElseThrow(() -> new UserException(NOT_FOUND_USER));

    return ResponseEntity.ok(UserDto.from(u));
  }
}
