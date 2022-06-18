package com.nerdket.market.controller.user;

import lombok.Data;

@Data
public class UserDto {
	private String email;
	private String password;
	private String passwordConfirm;
}
