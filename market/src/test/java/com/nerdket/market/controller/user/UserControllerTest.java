package com.nerdket.market.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.nerdket.market.controller.UserController;
import com.nerdket.market.controller.UserController.SignUpUserRequest;
import com.nerdket.market.service.user.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private MockMvc mockMvc;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void 회원가입_기본_테스트() throws Exception {
		SignUpUserRequest signUpRequest = getRightSignUpRequest();
		ResultActions resultActions = mockMvc.perform(post("/user/signUp")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(signUpRequest)));

	}

	@Test
	void 비밀번호_확인이_틀린_회원가입_요청() throws Exception {
		SignUpUserRequest signUpRequest = getWrongSignUpRequest();
		ResultActions resultActions = mockMvc.perform(post("/user/signUp")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(signUpRequest)));
	}

	private SignUpUserRequest getRightSignUpRequest() {
		SignUpUserRequest signUpUserRequest = new SignUpUserRequest();
		signUpUserRequest.setEmail("test@test.com");
		signUpUserRequest.setUsername("test");
		signUpUserRequest.setPassword("password");
		signUpUserRequest.setPasswordConfirm("password");
		return signUpUserRequest;
	}


	private SignUpUserRequest getWrongSignUpRequest() {
		SignUpUserRequest signUpUserRequest = new SignUpUserRequest();
		signUpUserRequest.setEmail("test@test.com");
		signUpUserRequest.setUsername("test");
		signUpUserRequest.setPassword("password");
		signUpUserRequest.setPasswordConfirm("password2");
		return signUpUserRequest;
	}
}