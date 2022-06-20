package com.nerdket.market.config.jwt;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public interface JwtTokenService {

	/**
	 * 토큰 생성
	 */
	String createJwtToken(String username);

	/**
	 * 토큰으로 유저 찾기
	 */
	Optional<String> parseTokenFromRequest(HttpServletRequest request);
}
