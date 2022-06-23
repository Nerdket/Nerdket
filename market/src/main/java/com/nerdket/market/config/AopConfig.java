package com.nerdket.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nerdket.market.util.log.LogTrace;
import com.nerdket.market.util.log.ThreadLocalLogTrace;

@Configuration
public class AopConfig {

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
