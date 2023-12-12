package com.example.cmsproject1.domain.user;

import com.example.cmsproject1.domain.model.User;
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
