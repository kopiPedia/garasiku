package com.koped.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AuthenticationConfigurer {


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests((requests) -> requests.requestMatchers("/").permitAll()
				.requestMatchers("/home").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/css/**")
				.permitAll().requestMatchers("/js/**")
				.permitAll().requestMatchers("/img/**")
				.permitAll().requestMatchers("/scss/**")
				.permitAll().requestMatchers("/fonts/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(httpSecurityFormLoginConfigurer -> {
					httpSecurityFormLoginConfigurer.loginPage("/login").permitAll()
					.defaultSuccessUrl("/", true)
					.failureUrl("/login?error=true");
				}).build();
	}


}
