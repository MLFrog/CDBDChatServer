package com.cdbd.chat.infrastructure.redis;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisFunction {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * String 데이터 저장
     * @param key
     * @param value
     */
    public void saveStringValue(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    
    /**
     * String 데이터 가져오기
     * @param key
     * @return
     */
    public Object getStringValue(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }
    
    /**
     * String 데이터 삭제
     * @param key
     */
    public void deleteStringValue(String key) {
    	this.redisTemplate.delete(key);
    }
    
    /**
     * Set 데이터 저장
     * @param key
     * @param value
     */
    public void saveSetValue(String key, Object value) {
        this.redisTemplate.opsForSet().add(key, value);
    }

    /**
     * Set 데이터 가져오기
     * @param key
     * @return
     */
    public Set<Object> getSetValues(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }
    

    /**
     * 리스트 데이터 저장
     * @param key
     * @param value
     */
    public void addValueToList(String key, String value) {
        this.redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 리스트 데이터 가져오기
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> getValuesFromList(String key, long start, long end) {
        return this.redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 리스트 길이 조회
     * @param key
     * @return
     */
    public Long getListSize(String key) {
        return this.redisTemplate.opsForList().size(key);
    }
}
