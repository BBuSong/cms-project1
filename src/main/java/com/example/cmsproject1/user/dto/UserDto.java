package com.example.cmsproject1.user.dto;

import com.example.cmsproject1.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String userId;
  public static UserDto from(User user) {
    return new UserDto(user.getId(), user.getUserId());
  }

}
