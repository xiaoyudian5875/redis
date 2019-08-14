package com.hzit.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {

    Jedis jedis = null;
    @Before
    public void init(){
         jedis = new Jedis("192.168.1.196",6379);
        jedis.auth("123"); //密码
    }

    @Test
    public void test01(){
        jedis.set("name","张毅");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.append("name", " is my name;");
        System.out.println("拼接后:" + jedis.get("name"));
        // 删除某个键值对
        jedis.del("name");
        System.out.println("删除后:" + jedis.get("name"));
        // s设置多个键值对
        jedis.mset("name", "chenhaoxiang", "age", "20", "email", "chxpostbox@outlook.com");
        jedis.incr("age");// 用于将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。
        // 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。此操作限于64位有符号整数。
        jedis.incrBy("age",20);
        System.out.println(jedis.get("name") + " " + jedis.get("age") + " " + jedis.get("email"));
    }

    @Test
    public void test02(){
        jedis.set("vip","true");
        jedis.expire("vip",20);
        Long vip = jedis.ttl("vip");
        for (long i=vip;vip>0;i--)
        {
            vip = jedis.ttl("vip");
            System.out.println("你的生命:"+vip);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test03(){
        Long hset = jedis.hset("class1", "cwy", "60");

        /*Map<String, String> map = new HashMap<>();
        map.put("liubei","50");
        map.put("zhagnfei","30");
        Long hset1 = jedis.hset("class1",map);*/
        System.out.println(hset+"--");
        String hget = jedis.hget("class1", "liubei");
        System.out.println(hget);
        Set<String> class1 = jedis.hkeys("class1");
        List<String> class11 = jedis.hvals("class1");
        Map<String, String> class12 = jedis.hgetAll("class1");
        System.out.println(class1);
        System.out.println(class11);
        System.out.println(class12);
    }

    @Test
    public void test04(){
        Long rpush = jedis.rpush("list1", "a", "b", "c");
        System.out.println(rpush);

        List<String> list1 = jedis.lrange("list1", 0, 1);
        System.out.println(list1);



    }





}
