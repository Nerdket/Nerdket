package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
}
