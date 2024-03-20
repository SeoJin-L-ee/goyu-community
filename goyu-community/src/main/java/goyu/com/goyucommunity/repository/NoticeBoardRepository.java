package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
}
