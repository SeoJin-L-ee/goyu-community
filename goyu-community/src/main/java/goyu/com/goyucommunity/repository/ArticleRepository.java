package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.Article;
import goyu.com.goyucommunity.domain.NoticeBoard;
import goyu.com.goyucommunity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    //게시글

    //전체 게시판의 게시글 검색(Paged)
    Page<Article> findAll(Pageable pageable);

    // 주어진 게시글 Id들을 바탕으로 게시글을 찾아서 페이징
    Page<Article> findByArticleIdIn(List<Long> ids, Pageable pageable);

    //특정 게시판의 게시글 검색(Paged)
    Page<Article> findByNoticeBoard(NoticeBoard noticeBoard, Pageable pageable);

    //특정 사용자의 모든 게시글 검색(Paged)
    Page<Article> findByUser(User user, Pageable pageable);

    //전체 게시판에서 특정 키워드를 제목에 포함하는 글 검색(Paged)
    Page<Article> findByArticleTitleContaining(String articleTitle, Pageable pageable);

    //특정 게시판에서 특정 키워드를 제목에 포함하는 글 검색(Paged)
    Page<Article> findByNoticeBoardAndArticleTitleContaining(NoticeBoard noticeBoard, String articleTitle, Pageable pageable);

    //전체 게시글의 좋아요 수 top10 검색
    List<Article> findTop10ByOrderByArticleLikeDesc(); //항상 전체 게시글의 좋아요 수 top10을 검사하려면 비효율적. 성능개선 고려필요

    //특정 게시판의 좋아요 수 top10 검색
    List<Article> findTop10ByNoticeBoardOrderByArticleLikeDesc(NoticeBoard noticeBoard);

    //근 한 주 동안 생성된 전체 게시글의 좋아요 수 top10 검색
    @Query("SELECT a FROM Article a WHERE a.articleDate >= :oneWeekAgo ORDER BY a.articleLike DESC")
    Page<Article> findTop10ArticlesOfLastWeek(LocalDateTime oneWeekAgo, Pageable pageable);

    //근 한 주 동안 생성된 특정 게시판의 좋아요 수 top10 검색
    @Query("SELECT a FROM Article a WHERE a.noticeBoard = :noticeBoard " +
            "AND a.articleDate >= :oneWeekAgo ORDER BY a.articleLike DESC")
    Page<Article> findEachTop10ArticlesOfLastWeek(LocalDateTime oneWeekAgo, NoticeBoard noticeBoard, Pageable pageable);
}
