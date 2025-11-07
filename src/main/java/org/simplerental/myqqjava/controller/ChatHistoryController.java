package org.simplerental.myqqjava.controller;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.ChatHistory;
import org.simplerental.myqqjava.service.ChatHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class ChatHistoryController {
    private final ChatHistoryService chatHistoryService;

    @GetMapping
    public List<ChatHistory> getChatHistory() {
        return chatHistoryService.getChatHistory();
    }

}
