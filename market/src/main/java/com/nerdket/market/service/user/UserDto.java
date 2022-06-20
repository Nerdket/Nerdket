package com.nerdket.market.service.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private String email;
	private String username;
	private String password;
}
