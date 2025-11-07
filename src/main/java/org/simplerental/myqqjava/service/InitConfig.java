package org.simplerental.myqqjava.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.network.Server;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 初始化配置服务
 *
 * 启动服务器并加载Redis数据
 */
@Service
@RequiredArgsConstructor
public class InitConfig {
    private final Server server;
    private final RedisService redisService;
    @PostConstruct
    public void initConfig() {
        new Thread(() -> {
            try {
                server.initServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();


        redisService.loadAllUserIdsToRedis();
        redisService.saveFriendSetToRedis();


    }
}
