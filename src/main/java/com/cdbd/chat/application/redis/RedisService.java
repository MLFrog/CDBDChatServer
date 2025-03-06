package com.cdbd.chat.application.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveValue(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }
}
