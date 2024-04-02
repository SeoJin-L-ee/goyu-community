package goyu.com.goyucommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
//게시글 수정할 때 전달하기 위한 dto
public class UpdateArticleRequest {

    private String articleTitle;
    private String articleContent;

}
