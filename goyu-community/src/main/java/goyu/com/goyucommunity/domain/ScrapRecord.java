package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ScrapRecord", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "article_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ScrapRecord {

    @Id //스크랩 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrapRecord_id", updatable = false)
    private Long scrapRecordId;

    @ManyToOne //스크랩 유저코드
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne //스크랩된 게시글 번호
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Builder
    public ScrapRecord(Long scrapRecordId, User user, Article article) {
        this.scrapRecordId = scrapRecordId;
        this.user = user;
        this.article = article;
    }
}
