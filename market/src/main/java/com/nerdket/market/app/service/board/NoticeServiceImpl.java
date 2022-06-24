package com.nerdket.market.app.service.board;

import com.nerdket.market.app.repository.NoticeRepository;
import com.nerdket.market.app.repository.UserRepository;
import com.nerdket.market.domain.Notice;
import com.nerdket.market.domain.User;
import com.nerdket.market.exception.badrequest.BadRequestException;
import com.nerdket.market.exception.badrequest.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public void register(NoticeRegisterDto noticeDto) {
        User user = userRepository.findByUsername(noticeDto.getUsername()).orElseThrow(UserNotFoundException::new);
        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .hit(0L)
                .user(user)
                .build();

        noticeRepository.save(notice);
    }

    @Override
    public Page<Notice> listNotice(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    @Override
    public NoticeDetailDto getNotice(long boardId) {
        Notice notice =  noticeRepository.findById(boardId).orElseThrow(BadRequestException::new);
        NoticeDetailDto noticeDetail = NoticeDetailDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .hit(notice.getHit())
                .userName(notice.getUser().getUsername())
                .build();
        return noticeDetail;
    }
}
