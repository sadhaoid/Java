package org.simplerental.myqqjava.controller;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.Users;
import org.simplerental.myqqjava.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @GetMapping
    public List<Users> findUsers() {
        return userService.getUserList();
    }
}
