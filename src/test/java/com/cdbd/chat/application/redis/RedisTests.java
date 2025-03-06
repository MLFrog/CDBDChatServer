package com.cdbd.chat.application.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
public class RedisTests {
	
	@Autowired
	private RedisService service;
	
	@Test
	public void 레디스_테스트() {
		this.service.saveValue("Test", 12);
	}
}
