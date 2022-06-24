package com.nerdket.market.app.service.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeDto {
    private String title;
    private String content;
    private String username;
}
