package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.config.ResponseMessage;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;

/**
 * 客户端服务
 */
@Service
@RequiredArgsConstructor
public class ClientService {
    public static final String ONLINEFRIENDSMESSAGE = "无好友登录";
    public final RedisService redisService;

    public List<String> lineSplit(String line) {
        String[] split = line.toUpperCase().split("\\s+");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 解析登录ID
     *
     * @param line 输入行
     * @return 登录ID
     */
    public String parseLoginId(String line) {
        List<String> strings = lineSplit(line);
        if ((strings.size() == 2) && strings.contains("LOGIN") && redisService.isUsers(strings.get(1))) {
            return strings.get(1);
        }
        else {
            return "";
        }
    }

    /**
     * 处理在线好友请求
     *
     * @param loginId 登录ID
     * @param writer  输出流
     */
    public void handleOnlineFriends(String loginId, PrintWriter writer) {
        Set<Object> onlineFriendsSet =  redisService.checkOnlineFriends(loginId);
        if (onlineFriendsSet.isEmpty()) {
            writer.println(ONLINEFRIENDSMESSAGE);
        } else {
            writer.println(onlineFriendsSet);
        }
    }

    /**
     * 验证聊天目标
     *
     * @param line    输入行
     * @param loginId 登录ID
     * @return 响应消息
     */
    public ResponseMessage validateChatTarget(String line, String loginId) {
        List<String> lineList = lineSplit(line);
        if (lineList.size() != 2) {
            return ResponseMessage.UseCorrectCommand;
        }
        if (!lineList.getFirst().equals("CHANGE") ) {
            return ResponseMessage.UseCorrectCommand;
        }
        if (!redisService.isUsers(lineList.get(1))) {
            return ResponseMessage.UserNotRegistered;
        }
        if (!redisService.isFriends(loginId,lineList.get(1))){
            return ResponseMessage.UserNotFriend;
        }
        if(!redisService.isLogin(lineList.get(1))){
            return ResponseMessage.UserNotOnline;
        }
        return ResponseMessage.Successful;
    }



    /**
     * 处理响应消息
     *
     * @param line    输入行
     * @param loginId 登录ID
     * @return 响应消息字符串
     */
    public String handleResponseMessage(String line, String loginId) {
        ResponseMessage responseMessage = validateChatTarget(line,loginId);
        switch (responseMessage) {
            case Successful, UseCorrectCommand, UserNotRegistered, UserNotFriend, UserNotOnline -> {
                return responseMessage.getMessage();
            }
            default -> {
                return ResponseMessage.UnknownError.getMessage();
            }
        }
    }

    /**
     * 发送消息给好友
     *
     * @param line     输入行
     * @param loginId  登录ID
     * @param friendId 好友ID
     * @param userMap  在线用户映射
     */
    public void sendMessageToFriend(String line, String loginId, String friendId, Map<String, PrintWriter> userMap) {
        String message = "From " + loginId + " MSG" + ": " + line;
        PrintWriter writer = userMap.get(friendId);
        writer.println(message);

    }


}
