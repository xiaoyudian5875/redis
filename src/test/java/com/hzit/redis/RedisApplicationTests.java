package com.hzit.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RedisApplicationTests {
    //扩大范围
    Jedis jedis = null;

    @Before
    public void init() {
        //IP地址
        jedis = new Jedis("192.168.1.166", 6379);
        //连接密码
        jedis.auth("123");
    }

    /**
     * keys和String
     */
    @Test
    public void text01() {
        //设置value值
        jedis.set("name", "lisi");
        //jedis.set("lisi","5555555555555");
        //拼接value
        jedis.append("name", "___zhangsan");
        //删除
        String str = (jedis.del("name") > 0) ? "删除成功" : "删除失败";
        //查询
        String name = jedis.get("name");
        //替换修改value
        jedis.getSet("name", "zhangsan");
        //设置存在时间
        //jedis.expire("lisi",350);
        //查询剩余时间
        if (jedis.ttl("lisi") < 0) {
            System.err.println("李四已经死了");
        } else {
            System.err.println("李四剩余：" + jedis.ttl("lisi") + "秒");
        }
        //设置多个value值
        jedis.msetnx("diaochan", "456", "wangzhaojun", "888");
        //获取多个值
        List<String> mget = jedis.mget("diaochan", "wangzhaojun");
        //for (Object obj : mget) {}
        System.err.println(str);
        System.err.println(mget);
        System.err.println(name);
    }

    /**
     * list
     */
    @Test
    public void text02() {
        //jedis.expire("list",50);
        //增加
        String a = (jedis.lpush("list", "zhagsan", "wnagwu", "lisi") > 0) ? "成功" : "失败";
        //删除
        String b = (jedis.lrem("list", 0, "zhagsan") > 0) ? "成功" : "失败";
        //修改
       // String c = (jedis.lset("list", 2, "xiao").equals("ok")) ? "成功" : "失败";
        String c = jedis.lset("list", 0, "xiao");
        //查询
        List<String> list = jedis.lrange("list", 0, -1);
        //总数
        Long i = jedis.llen("list");
        //输出结果
        System.err.println("增" + a);
        System.err.println("删" + b);
        System.err.println("改" + c);
        System.err.println("查"+list);
        System.err.println("个数" + i);


    }

    /**
     * set 无序 不能重复
     */
    @Test
    public void text03() {
        //增加
        String a =(jedis.sadd("set","a","b","c")> 0) ? "成功" : "失败";
        jedis.expire("set",50);
        //随机删除b
        String  b= jedis.spop("set");
        //删除
        //String b= (jedis.srem("set","b")> 0) ? "成功" : "失败";
        //修改

        //随机查询
        String set1 = jedis.srandmember("set");
        //查询
        Set<String> set = jedis.smembers("set");
        //总数
        Long i = jedis.scard("set");
        //输出
        System.err.println("增" + a);
        System.err.println("随机卅" + b);
       //System.err.println("删" + b);
        //System.err.println("改" + c);
        System.err.println("查"+set);
        System.err.println("随机查"+set1);
        System.err.println("个数" + i);
    }

    /**
     * list
     */
    @Test
    public void text04() {
        //增加
        //删除
        //修改
        //查询


    }

    public static void main(String[] args) {

    }
}
