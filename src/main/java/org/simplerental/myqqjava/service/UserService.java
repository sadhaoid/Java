package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.Users;
import org.simplerental.myqqjava.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<Users> getUserList() {
        return userRepository.findAll();
    }


}
