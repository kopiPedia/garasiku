package com.koped.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AuthenticationConfigurer {
	
	@SuppressWarnings({ "deprecation", "removal" })
	protected void configure(HttpSecurity http)throws Exception {
        http
                .authorizeRequests(requests -> {
					try {
						requests
						        .requestMatchers("/").permitAll() // This will be your home screen URL
						        .requestMatchers("/css/**").permitAll()
						        .requestMatchers("/js/**").permitAll()
						        .anyRequest().authenticated()
						        .and()
						        .formLogin()
						        .defaultSuccessUrl("/auth/home") //configure screen after login success
						        .loginPage("/auth/login")
						        .permitAll()
						        .and()
						        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
    }

}
