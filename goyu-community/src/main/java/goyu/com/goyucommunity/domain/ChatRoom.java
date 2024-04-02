package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "ChatRoom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatRoom {

    @Id //쪽지 관계 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id", updatable = false)
    private Long relationId;

    @ManyToOne //수신자 유저코드
    @JoinColumn(name = "chatReceiver", nullable = false, updatable = false)
    private User chatReceiver;

    @ManyToOne //발신자 유저코드
    @JoinColumn(name = "chatSender", nullable = false, updatable = false)
    private User chatSender;

    public ChatRoom(Long relationId, User chatReceiver, User chatSender) {
        this.relationId = relationId;
        this.chatReceiver = chatReceiver;
        this.chatSender = chatSender;
    }
}
