package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
}
