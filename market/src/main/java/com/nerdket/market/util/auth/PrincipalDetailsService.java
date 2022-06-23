package com.nerdket.market.util.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nerdket.market.domain.User;
import com.nerdket.market.exception.badrequest.UserNotFoundException;
import com.nerdket.market.app.repository.UserRepository;
import com.nerdket.market.app.service.user.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
		UserDto userDto = UserDto.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.role(user.getRole().toString())
			.build();
		return new PrincipalDetails(userDto);
	}
}