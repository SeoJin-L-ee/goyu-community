package goyu.com.goyucommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentRequest {

    private Long articleId;
    private Long commentSequence;
    private Long userId;
    private String commentContent;

}
