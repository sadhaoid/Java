package org.simplerental.myqqjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * 自定义 RedisTemplate<String, Object> 的 Bean
     *
     * @param factory Redis 连接工厂
     * @return 配置好的 RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 1. 创建 RedisTemplate 实例
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 2. 配置 Key 的序列化器 (推荐使用 StringRedisSerializer)
        // 确保 Redis 中 Key 的可读性
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer); // Hash 结构的 Key 也使用 String 序列化

        // 3. 配置 Value 的序列化器 (推荐使用 GenericJackson2JsonRedisSerializer)
        // 可以将 Java 对象序列化为 JSON 字符串，并包含类型信息，方便反序列化
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer); // Hash 结构的 Value 也使用 JSON 序列化

        // 4. 初始化配置
        template.afterPropertiesSet();
        
        return template;
    }
}