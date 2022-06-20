package com.nerdket.market.controller;

import static com.nerdket.market.config.jwt.JwtProperties.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdket.market.config.jwt.JwtProperties;
import com.nerdket.market.controller.response.SuccessResponse;
import com.nerdket.market.service.user.UserDto;
import com.nerdket.market.service.user.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("login")
	public SuccessResponse login(HttpServletResponse response, @RequestBody LoginRequest request){
		UserDto userDto = UserDto.builder()
			.username(request.getUsername())
			.password(request.getPassword())
			.build();
		String jwtToken = userService.login(userDto);
d		response.addHeader(HEADER_STRING, jwtToken);
		return new SuccessResponse(new LoginResponse(userDto.getUsername()));
	}

	@PostMapping("signUp")
	public SuccessResponse signUpUser(@RequestBody SignUpUserRequest request) {
		validatePasswordConfirm(request);
		String username = userService.join(request.getUserDto());
		return new SuccessResponse(new SignUpUserResponse(username));
	}

	private void validatePasswordConfirm(SignUpUserRequest request) {
		if (!request.getPassword().equals(request.getPasswordConfirm())){
			throw new IllegalStateException("[ERROR] 비밀번호 확인이 일치하지 않습니다.");
		}
	}

	@Data
	static class LoginRequest {
		@NotEmpty
		private String username;
		@NotEmpty
		private String password;
	}

	@Data
	@AllArgsConstructor
	static class LoginResponse {
		private String username;
	}

	@Data
	public static class SignUpUserRequest {
		private String email;
		private String username;
		private String password;
		private String passwordConfirm;

		public UserDto getUserDto(){
			return UserDto.builder()
				.email(email)
				.username(username)
				.password(password)
				.build();
		}
	}

	@Data
	@AllArgsConstructor
	static class SignUpUserResponse {
		private String username;
	}
}
