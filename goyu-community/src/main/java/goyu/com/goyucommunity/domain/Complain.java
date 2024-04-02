package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Complain")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Complain {

    @Id //신고 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_id", updatable = false)
    private Long complainId;

    @ManyToOne //신고자 유저코드
    @JoinColumn(name = "complainSender", nullable = false, updatable = false)
    private User complainSender;

    @ManyToOne //피신고자 유저코드
    @JoinColumn(name = "complainReceiver", nullable = false, updatable = false)
    private User complainReceiver;

    @ManyToOne //신고 유형 번호
    @JoinColumn(name = "type", nullable = false, updatable = false)
    private ComplainType complainType;

    @Column(name = "content", nullable = false, updatable = false) //신고 내용
    private String content;

    @Builder
    public Complain(Long complainId, User complainSender, User complainReceiver, ComplainType complainType, String content) {
        this.complainId = complainId;
        this.complainSender = complainSender;
        this.complainReceiver = complainReceiver;
        this.complainType = complainType;
        this.content = content;
    }

}
