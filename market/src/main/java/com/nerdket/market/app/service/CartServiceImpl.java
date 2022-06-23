package com.nerdket.market.app.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.app.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
}
