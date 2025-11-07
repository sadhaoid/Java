package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.entity.ChatHistory;
import org.simplerental.myqqjava.repository.ChatHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {
    private final ChatHistoryRepository chatHistoryRepository;


    public List<ChatHistory> getChatHistory() {
        return chatHistoryRepository.findAll();
    }

    public void storeChatHistory(String line, String friendId, String loginId) {

        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setFromId(Long.valueOf(loginId));
        chatHistory.setToId(Long.valueOf(friendId));
        chatHistory.setMessage(line);

        chatHistoryRepository.save(chatHistory);
    }

    }
