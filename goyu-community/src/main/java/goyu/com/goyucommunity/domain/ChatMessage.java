package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "ChatMessage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatMessage {

    @Id //쪽지 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chat_id;

    @Column(name = "chatContent", nullable = false, updatable = false) //메세지 내용
    private String chatContent;

    @Column(name = "chatDate", nullable = false, updatable = false) //메세지 발신 시각
    private LocalDateTime chatDate;

    public ChatMessage(Long chat_id, String chatContent, LocalDateTime chatDate) {
        this.chat_id = chat_id;
        this.chatContent = chatContent;
        this.chatDate = chatDate;
    }
}
