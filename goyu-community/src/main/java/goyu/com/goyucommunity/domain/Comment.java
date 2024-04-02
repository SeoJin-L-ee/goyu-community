package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "Comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Comment {

    @Id //전체 댓글 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long commentId;

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

    @CreatedDate
    @Column(name = "commentDate", nullable = false, updatable = false) //댓글 작성일
    private LocalDateTime commentDate;

    @UpdateTimestamp
    @Column(name = "commentUpdate") //댓글 수정일
    private LocalDateTime commentUpdate;

    @Column(name = "commentLike") //좋아요수
    private Long commentLike;

    @Column(name = "commentState") //댓글 상태
    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    @Builder
    public Comment(Long commentId, Article article, Long commentSequence, User user, String commentContent, Long commentLike, CommentStatus commentStatus) {
        this.commentId = commentId;
        this.article = article;
        this.commentSequence = commentSequence;
        this.user = user;
        this.commentContent = commentContent;
        this.commentLike = commentLike;
        this.commentStatus = commentStatus;
    }
}
