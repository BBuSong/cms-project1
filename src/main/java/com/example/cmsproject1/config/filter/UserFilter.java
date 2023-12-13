package com.example.cmsproject1.config.filter;

import com.example.cmsproject1.config.JwtProvider;
import com.example.cmsproject1.user.type.UserVo;
import com.example.cmsproject1.user.service.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@WebFilter(urlPatterns = "/user/*")
@RequiredArgsConstructor
public class UserFilter implements Filter {

  private final JwtProvider jwtProvider;
  private final UserService userService;


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader("X-AUTH-TOKEN");
    if(!jwtProvider.validateToken(token)) {
      throw new ServletException("Invalid Access");
    }
    UserVo vo = jwtProvider.getUserVo(token);
    userService.findByIdAndUserId(vo.getId(), vo.getUserId())
        .orElseThrow(() -> new ServletException("invalid access"));

    chain.doFilter(request, response);
  }
}
