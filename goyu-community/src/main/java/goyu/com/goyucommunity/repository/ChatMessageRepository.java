package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<Article, Long> {
}
