package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.ScrapRecord;
import goyu.com.goyucommunity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScrapRecordRepository extends JpaRepository<ScrapRecord, Long> {

    //특정 사용자의 스크랩 이력 검색
    List<ScrapRecord> findByUser(User user);

    //특정 게시글의 스크랩 수 출력
    int countByArticle_ArticleId(Long articleId);
}
