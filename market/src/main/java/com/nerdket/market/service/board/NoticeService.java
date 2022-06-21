package com.nerdket.market.service.board;

import java.time.LocalDateTime;

public interface NoticeService {
    LocalDateTime register(NoticeDto noticeDto);
}
