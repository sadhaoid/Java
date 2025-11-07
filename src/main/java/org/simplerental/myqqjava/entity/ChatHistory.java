package org.simplerental.myqqjava.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat_history")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "from_id")
    private Long fromId;
    @Column(name = "to_id")
    private Long toId;
    @Column(name = "message")
    private String message;
    @Column(insertable = false)
    private LocalDateTime sendTime;
}
