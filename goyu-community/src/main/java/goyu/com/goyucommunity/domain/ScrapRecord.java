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
    private Long scrapRecord_id;

    @ManyToOne //스크랩 유저코드
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne //스크랩된 게시글 번호
    @JoinColumn(name = "article_id", nullable = false)
    private Article article_id;

    @Builder
    public ScrapRecord(Long scrapRecord_id, User user_id, Article article_id) {
        this.scrapRecord_id = scrapRecord_id;
        this.user_id = user_id;
        this.article_id = article_id;
    }
}
