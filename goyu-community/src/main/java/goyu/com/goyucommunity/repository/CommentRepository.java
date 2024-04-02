package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.Article;
import goyu.com.goyucommunity.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //해당 게시글에 달린 댓글들 순서대로 검색(게시글 내 댓글번호 순서)
    List<Comment> findByArticleOrderByCommentSequence(Article article);

    //특정 게시글의 댓글 수 출력
    int countByArticle_ArticleId(Long articleId);
}
