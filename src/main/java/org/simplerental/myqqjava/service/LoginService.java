package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 处理登录请求
 */
@Service
@RequiredArgsConstructor
public class LoginService {
    private static final String LoginHint = "请使用login ID登录即可";
    private static final String LoginMessage = "该用户已登录，请更换ID";
    private static final String LoginSucceed = "登录成功";
    public final ClientService clientService;
    public final RedisService redisService;

    /**
     * 处理登录请求
     *
     * @param writer  输出流
     * @param reader  输入流
     * @param userMap 在线用户映射
     * @return 登录ID
     * @throws IOException IO异常
     */

    public String processLoginRequest( PrintWriter writer, BufferedReader reader, Map<String, PrintWriter> userMap) throws IOException {

        String line;
        while ((line = reader.readLine()) != null) {
            String loginId = clientService.parseLoginId(line);

            if (loginId.isEmpty()) {
                writer.println(LoginHint);
            } else if (redisService.isLogin(loginId)) {
                writer.println(LoginMessage);
            } else {
                writer.println(LoginSucceed);
                userMap.put(loginId, writer);
                redisService.insertLoginIdToRedis(loginId);
                System.out.println(userMap);
                return loginId;
            }
        }
        return null;
    }
}
