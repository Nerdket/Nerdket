package com.nerdket.market.util.jwt;

import javax.servlet.http.HttpServletRequest;

import com.nerdket.market.domain.User;
import com.nerdket.market.app.service.user.UserDto;

public interface JwtTokenService {

	/**
	 * 토큰 생성
	 */
	String createJwtToken(User user);

	/**
	 * 토큰으로 유저 찾기
	 * @return
	 */
	UserDto parseTokenFromRequest(HttpServletRequest request);

}
