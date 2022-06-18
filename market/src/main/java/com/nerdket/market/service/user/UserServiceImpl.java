package com.nerdket.market.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdket.market.domain.User;
import com.nerdket.market.domain.vo.Password;
import com.nerdket.market.exception.DuplicatedUserEmailException;
import com.nerdket.market.exception.DuplicatedUserNameException;
import com.nerdket.market.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public String join(UserDto userDto) {
		User user = User.builder().username(userDto.getUsername())
			.email(userDto.getEmail())
			.password(Password.of(passwordEncoder.encode(userDto.getPassword())))
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
}
