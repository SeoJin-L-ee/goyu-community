package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {

    @Id //전체 댓글 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long comment_id;

    @ManyToOne //게시글 번호
    @JoinColumn(name = "article_id", nullable = false, updatable = false)
    private Article article;

    @Column(name = "commentSequence", nullable = false, updatable = false) //게시글 내 댓글 번호
    private Long commentSequence;

    @ManyToOne //댓글 작성자
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "commentContent", nullable = false) //댓글 내용
    private String commentContent;

    @Column(name = "commentDate", nullable = false, updatable = false) //댓글 작성일
    private LocalDateTime commentDate;

    @Column(name = "commentUpdate") //댓글 수정일
    private LocalDateTime commentUpdate;

    @Column(name = "commentLike") //좋아요수
    private Long commentLike;

    @Column(name = "commentState") //댓글 상태
    private String commentState;

    public Comment(Long comment_id, Article article, Long commentSequence, User user, String commentContent, LocalDateTime commentDate, LocalDateTime commentUpdate, Long commentLike, String commentState) {
        this.comment_id = comment_id;
        this.article = article;
        this.commentSequence = commentSequence;
        this.user = user;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.commentUpdate = commentUpdate;
        this.commentLike = commentLike;
        this.commentState = commentState;
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
    }

}
