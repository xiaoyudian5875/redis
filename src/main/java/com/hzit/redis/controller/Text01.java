package com.hzit.redis.controller;

import com.hzit.redis.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


@RestController
public class Text01 {
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/redis/user")
    public  String insertuser (@RequestBody User user){
        System.out.println("----->>>>>>>>>>>>>>>>>>>--------------->");
        System.out.println(user);
        redisTemplate.opsForValue().set(user.getName(),user);
        return "OK";
    }

    @GetMapping("/redis/user01")
    @ResponseBody
    public Object get(String name){
        System.out.println(name);
        //保存数据  key: id   value：对象
        redisTemplate.opsForValue().set("name",name);
        redisTemplate.expire("name",20000, MILLISECONDS);

        Object obj = redisTemplate.opsForValue().get("名字");
        System.out.println(obj);
        return obj;

    }


}
