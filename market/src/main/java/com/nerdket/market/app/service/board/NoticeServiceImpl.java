package com.nerdket.market.app.service.board;

import com.nerdket.market.app.repository.NoticeRepository;
import com.nerdket.market.app.repository.UserRepository;
import com.nerdket.market.domain.Notice;
import com.nerdket.market.domain.User;
import com.nerdket.market.exception.badrequest.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public void register(NoticeDto noticeDto) {
        User user = userRepository.findByUsername(noticeDto.getUsername()).orElseThrow(UserNotFoundException::new);
        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .user(user)
                .build();

        noticeRepository.save(notice);
    }
}
