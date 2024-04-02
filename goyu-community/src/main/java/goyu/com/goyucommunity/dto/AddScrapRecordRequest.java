package goyu.com.goyucommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddScrapRecordRequest {

    private Long userId;
    private Long articleId;

}
