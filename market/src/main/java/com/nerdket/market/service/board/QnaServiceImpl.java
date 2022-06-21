package com.nerdket.market.service.board;

import com.nerdket.market.service.board.QnaService;
import org.springframework.stereotype.Service;

import com.nerdket.market.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
}
