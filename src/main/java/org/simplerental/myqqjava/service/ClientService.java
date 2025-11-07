package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.config.ResponseMessage;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientService {
    public static final String ONLINEFRIENDSMESSAGE = "无好友登录";
    public final RedisService redisService;

    public List<String> lineSplit(String line) {
        String[] split = line.toUpperCase().split("\\s+");
        return new ArrayList<>(Arrays.asList(split));
    }

    public String parseLoginID(String line) {
        List<String> strings = lineSplit(line);
        if ((strings.size() == 2) && strings.contains("LOGIN") && redisService.isUsers(strings.get(1))) {
            return strings.get(1);
        }
        else {
            return "";
        }
    }

    public void handleOnlineFriends(String loginId, PrintWriter writer) {
        Set<Object> onlineFriendsSet =  redisService.checkOnlineFriends(loginId);
        if (onlineFriendsSet.isEmpty()) {
            writer.println(ONLINEFRIENDSMESSAGE);
        } else {
            writer.println(onlineFriendsSet);
        }
    }

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

    public void sendMessageToFriend(String line, String loginId, String friendId, Map<String, PrintWriter> userMap) {
        String message = "From " + loginId + " MSG" + ": " + line;
        PrintWriter writer = userMap.get(friendId);
        writer.println(message);

    }


}
