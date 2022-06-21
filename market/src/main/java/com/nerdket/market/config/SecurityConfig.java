package com.nerdket.market.config;

import static com.nerdket.market.domain.Role.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nerdket.market.config.jwt.JwtAuthenticationFilter;
import com.nerdket.market.config.jwt.JwtAuthorizationFilter;
import com.nerdket.market.config.jwt.JwtTokenService;
import com.nerdket.market.repository.UserRepository;
import com.nerdket.market.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CorsConfig corsConfig;
	private final JwtTokenService jwtTokenService;
	private final UserService userService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.httpBasic().disable()
			.formLogin().disable()
			.csrf().disable()
			.cors().and()
			.headers().frameOptions().disable().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.apply(new MyCustomDsl()) // 커스텀 필터 등록
			.and()
			.authorizeRequests(auth -> {
				auth.antMatchers("/user/myPage/test").authenticated()
					.antMatchers("/user/myPage").hasAuthority("NORMAL")
					.anyRequest().permitAll();
				}
			)
			.build();
	}

	public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			http.addFilter(corsConfig.corsFilter())
				.addFilter(new JwtAuthenticationFilter(authenticationManager, jwtTokenService))
				.addFilter(new JwtAuthorizationFilter(authenticationManager, jwtTokenService, userService));
		}
	}

}
