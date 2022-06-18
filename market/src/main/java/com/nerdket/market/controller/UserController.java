package com.nerdket.market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdket.market.controller.response.SuccessResponse;
import com.nerdket.market.service.user.UserDto;
import com.nerdket.market.service.user.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("signUp")
	public SuccessResponse signUpUser(@RequestBody SignUpUserRequest request) {
		validatePasswordConfirm(request);
		userService.join(request.getUserDto());
		return new SuccessResponse(request.getUsername());
	}

	private void validatePasswordConfirm(SignUpUserRequest request) {
		if (!request.getPassword().equals(request.getPasswordConfirm())){
			throw new IllegalStateException("[ERROR] 비밀번호 확인이 일치하지 않습니다.");
		}
	}

	@Data
	public static class SignUpUserRequest {
		private String email;
		private String username;
		private String password;
		private String passwordConfirm;

		public UserDto getUserDto(){
			UserDto userDto = new UserDto();
			userDto.setEmail(email);
			userDto.setUsername(username);
			userDto.setPassword(password);
			return new UserDto();
		}
	}
}
