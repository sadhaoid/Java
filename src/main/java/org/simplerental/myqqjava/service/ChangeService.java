package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.config.ResponseMessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理切换请求
 */

@Service
@RequiredArgsConstructor
public class ChangeService {
    public final ClientService clientService;

    /**
     * 处理切换请求
     *
     * @param loginId 登录ID
     * @param reader  输入流
     * @param writer  输出流
     * @return 新的登录ID
     * @throws IOException IO异常
     */
    public String processChangeRequest(String loginId, BufferedReader reader, PrintWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String result = handleChangeReuqest(line,writer,loginId);
            if (result != null){
                return result;
            }


        }
        return null;
    }

    public String handleChangeReuqest(String line, PrintWriter writer, String loginId){
        String lineTrim = line.trim();
        if ((lineTrim.equalsIgnoreCase("CHECK"))) {
            clientService.handleOnlineFriends(loginId,writer);
            return null;
        } else {
            ResponseMessage responseMessage = clientService.handleResponseMessage(line,loginId);
            writer.println(responseMessage.getMessage());
             if (responseMessage == ResponseMessage.Successful){
                return clientService.lineSplit(line).get(1);
            }
        }
        return null;
    }

}
