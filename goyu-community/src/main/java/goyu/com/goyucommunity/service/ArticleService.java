package goyu.com.goyucommunity.service;

import goyu.com.goyucommunity.domain.*;
import goyu.com.goyucommunity.dto.AddArticleRequest;
import goyu.com.goyucommunity.dto.UpdateArticleRequest;
import goyu.com.goyucommunity.exception.ResourceNotFoundException;
import goyu.com.goyucommunity.repository.ArticleRepository;
import goyu.com.goyucommunity.repository.NoticeBoardRepository;
import goyu.com.goyucommunity.repository.ScrapRecordRepository;
import goyu.com.goyucommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final NoticeBoardRepository noticeBoardRepository;
    private final UserRepository userRepository;
    private final ScrapRecordRepository scrapRecordRepository;

    //게시글 객체 생성
    public Article createArticle(AddArticleRequest request) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(request.getNoticeBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("NoticeBoard not found with id " + request.getNoticeBoardId()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId()));

        Article article = Article.builder()
                .noticeBoard(noticeBoard)
                .articleTitle(request.getArticleTitle())
                .articleContent(request.getArticleContent())
                .user(user)
                .articleStatus(ArticleStatus.CREATED)
                .build();

        return articleRepository.save(article);
    }

    //게시글 삭제
    public void delete(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId));
        article.setArticleStatus(ArticleStatus.DELETED); // 상태를 '삭제'로 변경
        articleRepository.save(article);
    }

    //게시글 수정
    @Transactional // : DB에서 데이터를 바꾸기 위한 작업 단위임을 의미하는 어노테이션
    public boolean update(Long articleId, UpdateArticleRequest request) {
        Article currentArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId));

        String updatedTitle = request.getArticleTitle();
        String updatedContent = request.getArticleContent();

        //수정될 글 제목과 내용이 null이거나 빈 경우
        if ((updatedTitle == null || updatedContent == null) ||
                (updatedTitle.trim().isEmpty() || updatedContent.trim().isEmpty())) {return false;}
        //수정될 글 제목과 내용이 모두 바뀌지 않은 경우
        if ((currentArticle.getArticleTitle().equals(updatedTitle)) &&
                currentArticle.getArticleContent().equals(updatedContent)) {return false;}

        //글 내용 수정 && 글 상태 변경(UPDATED)
        currentArticle.setArticleTitle(updatedTitle);
        currentArticle.setArticleContent(updatedContent);
        currentArticle.setArticleStatus(ArticleStatus.UPDATED);

        return true;
    }

    //전체 게시판의 게시글 검색(Paged)
    public Page<Article> getAllArticles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return articleRepository.findAll(pageable);
    }

    //특정 게시판의 게시글 검색(Paged)
    public Page<Article> findByNoticeBoard(NoticeBoard noticeBoard, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return articleRepository.findByNoticeBoard(noticeBoard, pageable);
    }

    //특정 사용자의 모든 게시글 검색(Paged)
    public Page<Article> findByUser(User user, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return articleRepository.findByUser(user, pageable);
    }

    //전체 게시판에서 특정 키워드를 제목에 포함하는 글 검색(Paged)
    public Page<Article> findByArticleTitleContaining(String articleTitle, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return articleRepository.findByArticleTitleContaining(articleTitle, pageable);
    }

    //특정 게시판에서 특정 키워드를 제목에 포함하는 글 검색(Paged)
    public Page<Article> findByNoticeBoardAndArticleTitleContaining(NoticeBoard noticeBoard, String articleTitle, @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return articleRepository.findByNoticeBoardAndArticleTitleContaining(noticeBoard, articleTitle, pageable);
    }

    //전체 게시글의 좋아요 수 top10 검색
    public List<Article> findTop10ByOrderByArticleLikeDesc() {
        return articleRepository.findTop10ByOrderByArticleLikeDesc();
    }

    //특정 게시판의 좋아요 수 top10 검색
    public List<Article> findTop10ByNoticeBoardOrderByArticleLikeDesc(NoticeBoard noticeBoard) {
        return articleRepository.findTop10ByNoticeBoardOrderByArticleLikeDesc(noticeBoard);
    }

    //근 한 주 동안 생성된 전체 게시글의 좋아요 수 top10 검색
    public List<Article> findTop10ArticlesOfLastWeek(@RequestParam(defaultValue = "10") int pageSize) {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        Page<Article> topTen = articleRepository.findTop10ArticlesOfLastWeek(oneWeekAgo,
                (Pageable) PageRequest.of(0, pageSize));
        return topTen.getContent();
    }

    //근 한 주 동안 생성된 특정 게시판의 좋아요 수 top10 검색
    public List<Article> findEachTop10ArticlesOfLastWeek(NoticeBoard noticeBoard, @RequestParam(defaultValue = "10") int pageSize) {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        Page<Article> topTen = articleRepository.findEachTop10ArticlesOfLastWeek(oneWeekAgo,
                noticeBoard, (Pageable) PageRequest.of(0, pageSize));
        return topTen.getContent();
    }

    //특정 사용자의 스크랩 이력을 바탕으로, 특정 사용자가 스크랩한 게시글 출력 (User user가 스크랩한 게시글 중 page번째 페이지 불러오기)
/*
    대략적인 flow -> 파라미터로 받아온 user 정보를 사용하여, findByUser로 해당 사용자가 스크랩한 게시글 목록을 List<Article>로 받아옴.
                    받아온 게시글에서 map을 통해 각 게시글에 해당하는 게시글 Id를 받아옴.
                    받은 Id를 통해 findByIdIn를 사용해서 페이징된 게시글 조회
*/
    public Page<Article> findArticleByScrapRecord(User user, @RequestParam(defaultValue = "0") int page) {
        List<ScrapRecord> scrapRecords = scrapRecordRepository.findByUser(user);

        // 스크랩된 게시글 ID 목록 추출
        List<Long> articleIds = scrapRecords.stream()
                .map(scrapRecord -> scrapRecord.getArticle().getArticleId())
                .collect(Collectors.toList());

        // 게시글 ID 목록을 기반으로 게시글 조회 후 리턴
        return articleRepository.findByArticleIdIn(articleIds, (Pageable) PageRequest.of(page, 20));
    }

    //특정 게시글의 좋아요 수 출력
    public int countLikes(Long articleId) {
        return articleRepository.findById(articleId)
                .map(Article::getArticleLike)
                .orElse(0); // 게시글이 존재하지 않을 경우 0 반환
    }
}