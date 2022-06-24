package com.nerdket.market.app.service.user;

import java.util.function.Consumer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private String email;
	private String username;
	private String password;
	private String role;

	public boolean isPresent(){
		return username != null;
	}

	public void ifPresent(Consumer<UserDto> action) {
		if (username != null) {
			action.accept(this);
		}
	}
}
