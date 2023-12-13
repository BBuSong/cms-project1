package com.example.cmsproject1.basket.client;

import static com.example.cmsproject1.product.exception.ErrorCode.BASKET_CHANGE_FAIL;

import com.example.cmsproject1.basket.Basket;
import com.example.cmsproject1.product.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisClient {

  private final RedisTemplate<String,Object> redisTemplate;
  private static final ObjectMapper mapper = new ObjectMapper();

  public <T> T get(Long key, Class<T> classType) {
    return get(key.toString(), classType);
  }

  public <T> T get(String key,Class<T> classType) {
    String redisValue = (String) redisTemplate.opsForValue().get(key);
    if(ObjectUtils.isEmpty(redisValue)) {
      return null;
    } else {
      try {
        return mapper.readValue(redisValue, classType);
      } catch (JsonProcessingException e) {
        log.error("Parsing error", e);
        return null;
      }
    }
  }
  public void put(Long key, Basket basket) {
    put(key.toString(), basket);
  }
  public void put(String key, Basket basket) {
    try {
      redisTemplate.opsForValue().set(key, mapper.writeValueAsString(basket));
    } catch (JsonProcessingException e) {
      throw new CustomException(BASKET_CHANGE_FAIL);
    }
  }
}
