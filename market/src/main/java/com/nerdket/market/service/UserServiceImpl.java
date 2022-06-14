package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
}
