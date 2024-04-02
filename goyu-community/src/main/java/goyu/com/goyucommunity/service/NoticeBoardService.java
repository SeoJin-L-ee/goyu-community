package goyu.com.goyucommunity.service;

import goyu.com.goyucommunity.domain.Comment;
import goyu.com.goyucommunity.domain.CommentStatus;
import goyu.com.goyucommunity.domain.NoticeBoard;
import goyu.com.goyucommunity.dto.AddNoticeBoardRequest;
import goyu.com.goyucommunity.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    //게시판 생성
    public NoticeBoard createNoticeBoard(AddNoticeBoardRequest request) {
        NoticeBoard noticeBoard = NoticeBoard.builder()
                .noticeBoard(request.getNoticeBoard())
                .build();

        return noticeBoardRepository.save(noticeBoard);
    }
}
