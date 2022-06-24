package com.nerdket.market.app.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.app.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
}
