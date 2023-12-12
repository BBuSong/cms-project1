package com.example.cmsproject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

  @Bean
  public JwtProvider jwtProvider() {
    return new JwtProvider();
  }
}
