package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.text.MessageFormat;

/**
 * 消息中转配置类，redis实现
 */
@Configuration
public class RedisSubscriberConfig {

    private String redisDbIndex = "0";

    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(
            RedisConnectionFactory sessionConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(sessionConnectionFactory);
        MessageListenerAdapter keyModifyListener = new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            String channelName = new String(message.getChannel());
            //监听到发生变动的key
            String[] split = channelName.split("RATE_LIMITER_REGISTER:");
            String customerKey = split[1];
            System.out.println(customerKey);
        });

        redisMessageListenerContainer.addMessageListener(keyModifyListener,
                new PatternTopic(MessageFormat.format("__keyspace@{0}__:RATE_LIMITER_REGISTER:*", redisDbIndex)));
        return redisMessageListenerContainer;
    }
}