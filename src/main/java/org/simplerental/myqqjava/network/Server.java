package org.simplerental.myqqjava.network;

import org.simplerental.myqqjava.service.ChangeService;
import org.simplerental.myqqjava.service.LoginService;
import org.simplerental.myqqjava.service.RedisService;
import org.simplerental.myqqjava.service.SendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TCP 服务器
 *
 * 监听8081端口，等待客户端连接
 * 每当有客户端连接时，启动一个线程异步处理该客户端的请求
 */
@Component
public class Server {
    public static final Map<String, PrintWriter> USER_MAP = new ConcurrentHashMap<>();
    private final int port;
    private final LoginService loginService;
    private final RedisService redisService;
    private final ChangeService changeService;
    private final SendService sendService;
    private volatile boolean isRunning = true;

    public Server(@Value("${tcp.server.port}") int port, LoginService loginService, RedisService redisService, ChangeService changeService, SendService sendService) {
        this.port = port;
        this.loginService = loginService;
        this.redisService = redisService;
        this.changeService = changeService;
        this.sendService = sendService;
    }

    public void initServer() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)) {

        System.out.println("✅ TCP 服务启动，监听端口: "+port);
        redisService.deleteLoginList();

        while(isRunning)

            try {
                Socket socket = serverSocket.accept();
                System.out.println("来人了，要上班了");

                // 客户端连接上来启动一个线程异步处理
                new Thread(() -> clientHandler(socket)).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * 处理客户端请求
     * @param socket 与客户端通信的 Socket 对象
     */
    private void clientHandler(Socket socket) {
        String loginId = null;
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)
        ){

            loginId = loginService.processLoginRequest(writer,reader,USER_MAP);

            String friendId = changeService.processChangeRequest(loginId,reader,writer);

            sendService.processSendMessage(reader,writer,loginId,friendId, USER_MAP);


        } catch (IOException e) {

            throw new RuntimeException(e);
        } finally {
            if (loginId != null) {
                USER_MAP.remove(loginId);
                redisService.deleteLoginIdFromRedis(loginId);
            }
            System.out.println("客户端已断开连接");
        }

    }
}
