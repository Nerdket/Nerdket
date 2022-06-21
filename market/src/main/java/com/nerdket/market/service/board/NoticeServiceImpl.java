package com.nerdket.market.service.board;

import com.nerdket.market.domain.Notice;
import com.nerdket.market.domain.User;
import com.nerdket.market.repository.UserRepository;
import com.nerdket.market.service.board.NoticeService;
import org.springframework.stereotype.Service;

import com.nerdket.market.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
	private final UserRepository userRepository;

	@Override
	public LocalDateTime register(NoticeDto noticeDto) {
		User user = userRepository.getReferenceById(noticeDto.getUserId());
		Notice notice = Notice.builder().title(noticeDto.getTitle()).content(noticeDto.getContent()).user(user).build();
		noticeRepository.save(notice);
		return notice.getCreatedDate();
	}
}
