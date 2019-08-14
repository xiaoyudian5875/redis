package com.hzit.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class Redisspringboot {
    @Autowired
    private RedisTemplate redisTemplate; //操作任意类型

}
