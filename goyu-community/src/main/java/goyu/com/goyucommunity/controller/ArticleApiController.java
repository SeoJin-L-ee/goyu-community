package goyu.com.goyucommunity.controller;

import goyu.com.goyucommunity.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //HTTP 응답으로 객체 데이터를 JSON 형식으로 반환
public class ArticleApiController {

    private final ArticleService articleService;


}
