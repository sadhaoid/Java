package org.simplerental.myqqjava.config;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    Successful("选择成功请开始聊天"),
    UseCorrectCommand("使用change 好友ID即可"),
    UserNotRegistered("该用户还没有注册"),
    UserNotFriend("该用户还不是你的好友"),
    UserNotOnline("该用户还没有登录"),
    UnknownError("这都有我没有想到的漏洞");

    private final String message;


    ResponseMessage(String message) {
        this.message = message;
    }


}
