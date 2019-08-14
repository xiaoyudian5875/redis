package com.hzit.redis.configuration;

import com.hzit.redis.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 替换掉 xml中的 beans容器
 */
@Configuration //表示配置类 类似 xml中的 beans容器
public class RedistConfig {
    @Bean //表示需要添加到容器中的对象
    public User usettemplate() {
        User user = new User();
        user.setAge(12).setPwd("123").setName("zhagsan").setId(1001);
        return user;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        System.out.println("--------------自定义redis模板-------------");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        // 注入数据源
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key-value结构序列化数据结构
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash数据结构序列化方式,必须这样否则存hash 就是基于jdk序列化的
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 启用默认序列化方式
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        /// redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    // <beans>
//  向spring容器添加对象
//  springboot推荐使用java配置,将xml转换成java
//   <bean id="deptTemplate" class="com.hzit.bean.Dept">
//      <proper name="dname" values="恐怖组织" />
//   </bean>
// </beans>

}
