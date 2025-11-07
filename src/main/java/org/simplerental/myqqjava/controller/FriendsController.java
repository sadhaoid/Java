package org.simplerental.myqqjava.controller;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.Friend;
import org.simplerental.myqqjava.service.FriendService;
import org.simplerental.myqqjava.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendsController {

    private final FriendService friendService;
    private final RedisService redisService;

    @GetMapping()
    public List<Friend> getFriends() {
        return friendService.getFriendList();
    }

    @PostMapping()
    public List<Friend> addFriends(@RequestBody List<Friend> friends) {
        friendService.addFriends(friends);
        redisService.insertFriendToRedis(friends);
        return friendService.getFriendList();
    }

    @DeleteMapping()
    public List<Friend> deleteFriends(@RequestBody List<Friend> friends) {
        friendService.deleteFriends(friends);
        redisService.deleteFriendFromRedis(friends);
        return friendService.getFriendList();
    }




}
