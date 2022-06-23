package com.nerdket.market.app.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.app.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
}
