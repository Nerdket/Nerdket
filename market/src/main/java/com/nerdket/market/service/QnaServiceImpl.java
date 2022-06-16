package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
}
