package com.nerdket.market.app.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.app.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
}
