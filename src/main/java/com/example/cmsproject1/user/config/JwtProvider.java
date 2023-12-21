package com.example.cmsproject1.user.config;


import com.example.cmsproject1.user.type.UserVo;
import com.example.cmsproject1.user.type.UserType;
import com.example.cmsproject1.domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Objects;

public class JwtProvider {

  private String secretKey = "secretKey";

  private long tokenvalidTime = 1000L * 60 * 60 * 24;

  public String createToken(String userPK, Long id, UserType userType) {
    Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPK))
                                .setId(Aes256Util.encrypt(id.toString()));
    claims.put("roles", userType);
    Date now = new Date();
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenvalidTime))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public boolean validateToken(String jwtToken) {
    try{
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (Exception e){
      return false;
    }
  }

  public UserVo getUserVo(String token) {
    Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(c.getId()))),
        Aes256Util.decrypt(c.getSubject()));
  }
}
