package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {
    private static final String LoginMessage = "请使用login ID登录即可";
    private static final String LoginedMessage = "该用户已登录，请更换ID";
    private static final String LoginSucceed = "登录成功";
    public final ClientService clientService;
    public final RedisService redisService;


    public String processLoginRequest( PrintWriter writer, BufferedReader reader, Map<String, PrintWriter> userMap) throws IOException {

        String line;
        while ((line = reader.readLine()) != null) {
            String loginId = clientService.parseLoginId(line);

            if (loginId.isEmpty()) {
                writer.println(LoginMessage);
            } else if (redisService.isLogin(loginId)) {
                writer.println(LoginedMessage);

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
