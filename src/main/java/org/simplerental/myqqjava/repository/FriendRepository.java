package org.simplerental.myqqjava.repository;

import org.simplerental.myqqjava.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
    Friend findByUserIdAndFriendId(Long userId, Long friendId);
}