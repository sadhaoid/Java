package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 处理发送消息请求
 */
@Service
@RequiredArgsConstructor
public class SendService {
    private final ClientService clientService;
    private final ChatHistoryService chatHistoryService;
    private final ChangeService changeService;

    /**
     * 处理发送消息请求
     *
     * @param reader   输入流
     * @param writer   输出流
     * @param loginId  登录ID
     * @param friendId 好友ID
     * @param userMap  在线用户映射
     * @throws IOException IO异常
     */
    public void processSendMessage(BufferedReader reader, PrintWriter writer, String loginId, String friendId, Map<String, PrintWriter> userMap) throws IOException {
        String line;
        while((line = reader.readLine()) != null){
            List<String> lineSplit = clientService.lineSplit(line);
            String lineTrim = line.trim();
            if (((lineTrim.equalsIgnoreCase("CHECK"))) ||  ((lineSplit.size() == 2) && (lineSplit.contains("CHANGE")))){ // check change
                String result = changeService.handleChangeReuqest(line,writer,loginId);
                if (result != null){
                    friendId = result;
                }
                continue;
            }

            clientService.sendMessageToFriend(line,loginId,friendId,userMap);

            chatHistoryService.storeChatHistory(line,friendId,loginId);

        }
    }

}



