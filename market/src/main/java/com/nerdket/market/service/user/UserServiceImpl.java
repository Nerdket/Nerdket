package com.nerdket.market.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerdket.market.config.jwt.JwtTokenService;
import com.nerdket.market.domain.User;
import com.nerdket.market.domain.vo.Password;
import com.nerdket.market.exception.badrequest.DuplicatedUserEmailException;
import com.nerdket.market.exception.badrequest.DuplicatedUserNameException;
import com.nerdket.market.exception.badrequest.UserNotFoundException;
import com.nerdket.market.exception.badrequest.WrongPasswordException;
import com.nerdket.market.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User findOne(String username) {
		return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
	}

	@Override
	@Transactional
	public String join(UserDto userDto) {
		User user = User.builder().username(userDto.getUsername())
			.email(userDto.getEmail())
			.password(Password.of(userDto.getPassword(), passwordEncoder))
			.build();

		validateDuplicated(user);
		userRepository.save(user);
		return user.getUsername();
	}

	private void validateDuplicated(User user) {
		userRepository.findByUsername(user.getUsername()).ifPresent(findUser -> {
			throw new DuplicatedUserNameException();
		});
		userRepository.findByUsername(user.getUsername()).ifPresent(findUser -> {
			throw new DuplicatedUserEmailException();
		});
	}

	@Override
	public String login(UserDto userDto) {
		User findUser = findOne(userDto.getUsername());
		validatePassword(findUser, userDto.getPassword());
		String jwtToken = jwtTokenService.createJwtToken(findUser.getUsername());
		return jwtToken;
	}

	private void validatePassword(User findUser, String password) {
		if (!passwordEncoder.matches(password, findUser.getPassword())){
			throw new WrongPasswordException();
		}
	}
}
