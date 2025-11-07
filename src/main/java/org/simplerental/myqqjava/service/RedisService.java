package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.Friend;
import org.simplerental.myqqjava.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RedisService {
    private static final String REDIS_KEY_PREFIX_USER = "user:";
    private static final String REDIS_KEY_USER_LIST = REDIS_KEY_PREFIX_USER + "list";
    private static final String REDIS_KEY_LOGIN_LIST =  REDIS_KEY_PREFIX_USER + "login_list";
    private static final String REDIS_KEY_PREFIX_FRIEND_LIST = "friend_list:";
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;
    private final FriendService friendService;



    public void loadAllUserIdsToRedis() {
        List<Long> longSet = userRepository.findAllUserId();
        redisTemplate.opsForSet().add(REDIS_KEY_USER_LIST, longSet.toArray());

    }

    public static boolean isPositiveInteger(String str) {
        return str != null && str.matches("[0-9]\\d*");
    }

    public boolean isUsers(String line) {
        if(isPositiveInteger(line)){
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(REDIS_KEY_USER_LIST, Long.valueOf(line)));
        }
        return false;
    }

    public boolean isFriends(String loginId, String friendId) {
         return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(REDIS_KEY_PREFIX_FRIEND_LIST + loginId, Long.valueOf(friendId)));


    }

    public boolean isLogin(String loginId){
        if(isPositiveInteger(loginId)==false){
            return redisTemplate.opsForSet().isMember(REDIS_KEY_LOGIN_LIST, Long.valueOf(loginId));
        }
        return false;
    }

    public void insertLoginIdToRedis(String loginID) {
        redisTemplate.opsForSet().add(REDIS_KEY_LOGIN_LIST,  Long.valueOf(loginID));
    }
    public void deleteLoginIdFromRedis(String loginID){
        redisTemplate.opsForSet().remove(REDIS_KEY_LOGIN_LIST,loginID);
    }

    public void saveFriendSetToRedis() {
        List<Friend> friendList = friendService.getFriendList();
        addFriendsToRedis(friendList);
    }

    public void insertFriendToRedis(List<Friend> friends){
        addFriendsToRedis(friends);

    }

    public void deleteFriendFromRedis(List<Friend> friends){
        removeFriendsFromRedis(friends);

    }


    public void addFriendsToRedis(List<Friend> friends) {
        List<Friend> friendList = friendService.twoWayList(friends);
        Map<Long, Set<Long>> friendMap = friendList.stream()
                .collect(Collectors.groupingBy(
                        Friend::getUserId,
                        Collectors.mapping(
                                Friend::getFriendId,
                                Collectors.toSet()
                        )
                ));

        for (var entry : friendMap.entrySet()) {
            Long userId = entry.getKey();
            Set<Long> friendIds = entry.getValue();

            // 执行添加操作
            redisTemplate.opsForSet().add(REDIS_KEY_PREFIX_FRIEND_LIST + userId, friendIds.toArray());
        }
    }

    private void removeFriendsFromRedis(List<Friend> friends) {
        List<Friend> friendList = friendService.twoWayList(friends);
        Map<Long, Set<Long>> friendMap = friendList.stream()
                .collect(Collectors.groupingBy(
                        Friend::getUserId,
                        Collectors.mapping(
                                Friend::getFriendId,
                                Collectors.toSet()
                        )
                ));

        for (var entry : friendMap.entrySet()) {
            Long userId = entry.getKey();
            Set<Long> friendIds = entry.getValue();

            // 执行移除操作
            redisTemplate.opsForSet().remove(REDIS_KEY_PREFIX_FRIEND_LIST + userId, friendIds.toArray());
        }
    }

    public void deleteLoginList() {
        redisTemplate.delete(REDIS_KEY_LOGIN_LIST);

    }
    public Set<Object> checkOnlineFriends(String loginId) {
       return redisTemplate.opsForSet().intersect(REDIS_KEY_LOGIN_LIST, REDIS_KEY_PREFIX_FRIEND_LIST + Long.valueOf(loginId));
    }







}
