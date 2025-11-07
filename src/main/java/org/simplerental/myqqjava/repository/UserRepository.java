package org.simplerental.myqqjava.repository;


import org.simplerental.myqqjava.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query(value = """
    SELECT u.user_id FROM "user" AS u
    """, nativeQuery = true)
    List<Long> findAllUserId();
}
