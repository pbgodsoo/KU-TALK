package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void testRedisConnection() {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set("testKey", "testValue");
            String value = valueOps.get("testKey");
            System.out.println("Redis 연결 성공: " + value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Redis 연결 실패");
        }
    }
}