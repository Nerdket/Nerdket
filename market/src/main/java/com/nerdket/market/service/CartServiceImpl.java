package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
}
