package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "Article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Article {

    @Id //게시글 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", updatable = false)
    private Long articleId;

    @ManyToOne //게시판 번호
    @JoinColumn(name = "notice_id", nullable = false, updatable = false)
    private NoticeBoard noticeBoard;

    @Column(name = "articleTitle", nullable = false) //게시글 제목
    private String articleTitle;

    @Column(name = "articleContent", nullable = false) //게시글 내용
    private String articleContent;

    @ManyToOne //게시글 작성자
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @CreatedDate
    @Column(name = "articleDate", nullable = false) //게시글 작성일
    private LocalDateTime articleDate;

    @UpdateTimestamp //엔티티가 수정될 때마다 자동으로 현재시간으로 업데이트 됨
    @Column(name = "articleUpdate") //게시글 최종수정일
    private LocalDateTime articleUpdate;

    @Column(name = "articleLike") //좋아요수
    private int articleLike;

    @Column(name = "articleState", nullable = false) //게시글 상태 (정상, 삭제)
    private ArticleStatus articleStatus;

    @Column(name = "articleViews") //게시글 조회수
    private long articleViews;

    @Builder
    public Article(Long articleId, NoticeBoard noticeBoard, String articleTitle, String articleContent, User user, int articleLike, ArticleStatus articleStatus, long articleViews) {
        this.articleId = articleId;
        this.noticeBoard = noticeBoard;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.user = user;
        this.articleLike = articleLike;
        this.articleStatus = articleStatus;
        this.articleViews = articleViews;
    }
}
