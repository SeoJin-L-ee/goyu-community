package goyu.com.goyucommunity.service;

import goyu.com.goyucommunity.domain.*;
import goyu.com.goyucommunity.dto.AddScrapRecordRequest;
import goyu.com.goyucommunity.exception.ResourceNotFoundException;
import goyu.com.goyucommunity.repository.ArticleRepository;
import goyu.com.goyucommunity.repository.ScrapRecordRepository;
import goyu.com.goyucommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapRecordService {

    private final ScrapRecordRepository scrapRecordRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    //스크랩 이력 생성
    public ScrapRecord createScrapRecord(AddScrapRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                //dto로 넘어온 '스크랩 한 유저 id'가 존재하지 않을 경우
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId()));
        Article article = articleRepository.findById(request.getArticleId())
                //dto로 넘어온 '스크랩 된 게시글 id'가 존재하지 않을 경우
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + request.getArticleId()));

        ScrapRecord scrapRecord = ScrapRecord.builder()
                .user(user)
                .article(article)
                .build();

        return scrapRecordRepository.save(scrapRecord);
    }

    //스크랩 이력 삭제
    public void deleteScrapRecord(Long scrapRecord_id) {
        scrapRecordRepository.deleteById(scrapRecord_id);
    }

    //특정 게시글의 스크랩 수 출력
    public int countScraps(Long articleId) {
        return scrapRecordRepository.countByArticle_ArticleId(articleId);
    }
}
