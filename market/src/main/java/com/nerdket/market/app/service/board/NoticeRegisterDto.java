package com.nerdket.market.app.service.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeRegisterDto {
    private String title;
    private String content;
    private String username;
}
