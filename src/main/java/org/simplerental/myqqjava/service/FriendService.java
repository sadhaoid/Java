package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.Friend;
import org.simplerental.myqqjava.repository.FriendRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public List<Friend> getFriendList() {
        return friendRepository.findAll();
    }

    public void addFriends(List<Friend> friends) {
        List<Friend> toSave = twoWayList(friends);

        List<Friend> newToSave = toSave.stream()
                .filter(friend -> !isFriendExists(friend))
                .toList();

        friendRepository.saveAll(newToSave);

    }

    public boolean isFriendExists(Friend friend) {
        return friendRepository.existsByUserIdAndFriendId(friend.getUserId(), friend.getFriendId());
    }

    public void deleteFriends(List<Friend> friends) {
        for (Friend friend: friends){
            Friend reverse = new Friend();
            reverse.setUserId(friend.getFriendId());
            reverse.setFriendId(friend.getUserId());
            if (isFriendExists(friend)) {
                Friend toDelete = friendRepository.findByUserIdAndFriendId(friend.getUserId(), friend.getFriendId());
                friendRepository.deleteById(toDelete.getId());
            }

            if (isFriendExists(reverse)){
                Friend toDelete_reverse = friendRepository.findByUserIdAndFriendId(reverse.getUserId(), reverse.getFriendId());
                friendRepository.deleteById(toDelete_reverse.getId());}
            }

        }

    public List<Friend> twoWayList(List<Friend> friends) {
        List<Friend> twoWayList = new ArrayList<>(friends);

        for (Friend friend : friends) {
            Friend reverseFriend = new Friend();

            reverseFriend.setUserId(friend.getFriendId());
            reverseFriend.setFriendId(friend.getUserId());

            twoWayList.add(reverseFriend);
        }
        return twoWayList;
    }


    }

