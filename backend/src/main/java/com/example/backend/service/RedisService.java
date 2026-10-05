package com.example.backend.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveInvite(String token, int projectId) {
        String key = "invite:" + token;

        redisTemplate.opsForValue().set(
                key,
                projectId
        );
    }

    public Integer getProjectId(String token) {
        String key = "invite:" + token;

        return (Integer) redisTemplate.opsForValue().get(key);
    }
}
