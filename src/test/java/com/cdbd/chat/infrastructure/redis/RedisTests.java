package com.cdbd.chat.infrastructure.redis;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
public class RedisTests {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private RedisFunction service;
	
	@Test
	public void 레디스_실제작동_테스트() {
		this.redisTemplate.execute((RedisCallback<Void>)(redisConnection) -> {
			redisConnection.multi();
			
			try {
				this.service.saveStringValue("Test", 12);
				throw new Exception("예외 발생");
				
//				this.redisTemplate.exec();
			} catch (Exception e) {
				log.error("예외 발생했으니 트랜잭션 취소");
				redisConnection.discard();
			}
			
			return null;
		});
	}
	
	@Test
	public void 레디스_문자열_삭제() {
		Object data = Optional.ofNullable(this.service.getStringValue("Test")).orElse(null);
		
		if (!Objects.isNull(data)) {
			log.info("데이터 : " + data.toString());
			this.service.deleteStringValue("Test");
		} else {
			log.info("널입니다.");
		}
		
	}
}
