package goyu.com.goyucommunity.service;

import goyu.com.goyucommunity.domain.*;
import goyu.com.goyucommunity.dto.AddArticleRequest;
import goyu.com.goyucommunity.dto.AddCommentRequest;
import goyu.com.goyucommunity.dto.UpdateCommentRequest;
import goyu.com.goyucommunity.exception.ResourceNotFoundException;
import goyu.com.goyucommunity.repository.ArticleRepository;
import goyu.com.goyucommunity.repository.CommentRepository;
import goyu.com.goyucommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    //댓글 생성
    public Comment createComment(AddCommentRequest request) {
        Article article = articleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + request.getArticleId()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId()));

        Comment comment = Comment.builder()
                .article(article)
                .commentSequence(request.getCommentSequence())
                .user(user)
                .commentContent(request.getCommentContent())
                .commentStatus(CommentStatus.CREATED)
                .build();

        return commentRepository.save(comment);
    }

    //댓글 삭제
    public void deleteComment(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + comment_id));
        comment.setCommentStatus(CommentStatus.DELETED); // 상태를 '삭제'로 변경
        commentRepository.save(comment);
    }

    //댓글 수정
    @Transactional // : DB에서 데이터를 바꾸기 위한 작업 단위임을 의미하는 어노테이션
    public boolean updateComment(Long comment_id, UpdateCommentRequest request) {
        Comment currentComment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + comment_id));

        String updatedContent = request.getCommentContent();

        //수정될 댓글 내용이 null이거나 빈 경우
        if (updatedContent == null || updatedContent.trim().isEmpty()) {return false;}
        //수정될 댓글 내용이, 원본 내용과 같은 경우
        if (currentComment.getCommentContent().equals(updatedContent)) {return false;}

        //댓글 내용 수정 && 댓글 상태 변경(UPDATED)
        currentComment.setCommentContent(updatedContent);
        currentComment.setCommentStatus(CommentStatus.UPDATED);

        return true;
    }

    //해당 게시글에 달린 댓글들 순서대로 검색(게시글 내 댓글번호 순서)
    public List<Comment> CommentByArticleOrderByCommentSequence(Article article) {
        return commentRepository.findByArticleOrderByCommentSequence(article);
    }

    //특정 게시글의 댓글 수 출력
    public int countComments(Long articleId) {
        return commentRepository.countByArticle_ArticleId(articleId);
    }
}
