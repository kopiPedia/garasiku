package com.koped.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class AuthenticationConfigurer {


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((requests) -> requests

                .requestMatchers("/", "/home", "/product/products", "/productImages/**", "/register", "/css/**", "/js/**", "/img/**", "/scss/**", "/fonts/**", "/importimages/**").permitAll()
                .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                    .loginPage("/login").permitAll()
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                    .logoutSuccessHandler(logoutSuccessHandler))
                .build();
    }


}
