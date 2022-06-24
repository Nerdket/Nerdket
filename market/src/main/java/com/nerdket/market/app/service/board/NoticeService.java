package com.nerdket.market.app.service.board;

import com.nerdket.market.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    void register(NoticeRegisterDto noticeDto);

    Page<Notice> listNotice(Pageable pageable);
}
