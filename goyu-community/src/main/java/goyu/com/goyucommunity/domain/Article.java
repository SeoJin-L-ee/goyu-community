package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Article {

    @Id //게시글 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", updatable = false)
    private Long article_id;

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

    @Column(name = "articleDate", nullable = false) //게시글 작성일
    private LocalDateTime articleDate;

    @Column(name = "articleUpdate") //게시글 최종수정일
    private LocalDateTime articleUpdate;

    @Column(name = "articleLike") //좋아요수
    private Long articleLike;

    @Column(name = "articleState", nullable = false) //게시글 상태 (정상, 삭제)
    private String articleState;

    @Column(name = "articleViews") //게시글 조회수
    private Long articleViews;

    @Builder
    public Article(Long article_id, NoticeBoard noticeBoard, String articleTitle, String articleContent, User user, LocalDateTime articleDate, LocalDateTime articleUpdate, Long articleLike, String articleState, Long articleViews) {
        this.article_id = article_id;
        this.noticeBoard = noticeBoard;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.user = user;
        this.articleDate = articleDate;
        this.articleUpdate = articleUpdate;
        this.articleLike = articleLike;
        this.articleState = articleState;
        this.articleViews = articleViews;
    }

    //게시글 수정 ('제목과 내용 동시에', '제목만', '내용만' 수정 가능)
    public void update(String articleTitle, String articleContent) {
        if (articleTitle != null && !articleTitle.isEmpty()) {this.articleTitle = articleTitle;}
        if (articleContent != null && !articleContent.isEmpty()) {this.articleContent = articleContent;}
    }

}
