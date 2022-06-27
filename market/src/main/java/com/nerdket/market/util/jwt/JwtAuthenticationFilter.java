package com.nerdket.market.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerdket.market.app.service.user.UserDto;
import com.nerdket.market.app.service.user.UserService;
import com.nerdket.market.domain.User;
import com.nerdket.market.util.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenService;
	private final UserService userService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
			AuthenticationException {
		UserDto userDto = getUserDto(request).orElseThrow(IllegalStateException::new);
		return makeAuthentication(userDto);
	}

	private Optional<UserDto> getUserDto(HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserDto userDto = null;
		try {
			userDto = objectMapper.readValue(request.getInputStream(), UserDto.class);
		} catch (IOException e) {
			log.error("[ERROR] I/O 오류");
		}
		return Optional.ofNullable(userDto);
	}

	private Authentication makeAuthentication(UserDto userDto) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDto.getEmail(), userDto.getPassword());
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		User findUser = userService.findOne(principalDetails.getUsername());

		String token = jwtTokenService.createJwtToken(findUser);
		response.addHeader(JwtProperties.HEADER_STRING, token);
		log.info("token 생성 성공={}", token);
	}
}