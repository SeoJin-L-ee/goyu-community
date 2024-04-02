package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "NoticeBoard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class NoticeBoard {

    @Id //게시판 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", updatable = false)
    private Long noticeId;

    @Column(name = "noticeBoard", nullable = false, updatable = false, unique = true) //게시판 구분
    private String noticeBoard;

    @Builder
    public NoticeBoard(Long noticeId, String noticeBoard) {
        this.noticeId = noticeId;
        this.noticeBoard = noticeBoard;
    }
}
