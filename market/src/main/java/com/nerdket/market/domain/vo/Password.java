package com.nerdket.market.domain.vo;

import static com.nerdket.market.domain.vo.PatternRegex.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;

import com.nerdket.market.exception.NoPatternMatchException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

	private String password;

	public static Password of(String password){
		validatePattern(password);
		return new Password(password);
	}

	private Password(String password){
		this.password = password;
	}

	private static void validatePattern(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches()){
			throw new NoPatternMatchException();
		}
	}

	@Override
	public String toString(){
		return password;
	}
}
