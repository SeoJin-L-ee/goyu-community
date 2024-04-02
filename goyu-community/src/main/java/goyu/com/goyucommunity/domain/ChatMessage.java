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
    @Column(name = "chatId")
    private Long chatId;

    @Column(name = "chatContent", nullable = false, updatable = false) //메세지 내용
    private String chatContent;

    @Column(name = "chatDate", nullable = false, updatable = false) //메세지 발신 시각
    private LocalDateTime chatDate;

    public ChatMessage(Long chatId, String chatContent, LocalDateTime chatDate) {
        this.chatId = chatId;
        this.chatContent = chatContent;
        this.chatDate = chatDate;
    }
}
