package com.nerdket.market.app.service.board;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoticeDetailDto {
    private long id;
    private String title;
    private String content;
    private String userName;
    private long hit;
    private LocalDateTime createdDate;

}
