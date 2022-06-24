package com.nerdket.market.app.service.board;

import com.nerdket.market.domain.Notice;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeListDto {
    private long id;
    private String title;
    private String userName;
    private long hit;
    private LocalDateTime createdDate;

    public NoticeListDto(Notice notice) {
        id = notice.getId();
        title = notice.getTitle();
        userName = notice.getUser().getUsername();
        hit = notice.getHit();
        createdDate = notice.getCreatedDate();
    }
}
