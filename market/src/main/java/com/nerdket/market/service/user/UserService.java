package com.nerdket.market.service.user;

import org.springframework.security.core.Authentication;

import com.nerdket.market.domain.User;

public interface UserService {

	/**
	 * 회원 조회
	 */
	User findOne(String username);

	/**
	 * 회원가입
	 */
	String join(UserDto userDto);

	/**
	 * 로그인
	 */
	String login(UserDto userDto);
}
