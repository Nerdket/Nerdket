package com.nerdket.market.domain.vo;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

	private String password;

	public Password(String password){
		this.password = password;
	}

	@Override
	public String toString(){
		return password;
	}
}
