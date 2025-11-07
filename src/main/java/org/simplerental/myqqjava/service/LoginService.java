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
    private static final String LoginSucceed = "登录成功";
    public final ClientService clientService;
    public final RedisService redisService;


    public String processLoginRequest( PrintWriter writer, BufferedReader reader, Map<String, PrintWriter> userMap) throws IOException {

        String line;
        while ((line = reader.readLine()) != null) {
            String loginID = clientService.parseLoginID(line);

            if (loginID.isEmpty()) {
                writer.println(LoginMessage);
            }else {
                writer.println(LoginSucceed);
                userMap.put(loginID, writer);
                redisService.insertLoginIdToRedis(loginID);
                System.out.println(userMap);
                return loginID;
            }
        }


        return null;
    }
}
