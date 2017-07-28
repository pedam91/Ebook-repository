package com.pdamjanovic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myUserDetailsService")
	UserDetailsService userDetailsService;

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.authorizeRequests()
		.antMatchers("/book/**", "/user/**")
		//.access("hasRole('ROLE_ADMIN')")
		.authenticated()
		.and().formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/", false)
		.failureUrl("/login?error")
		.usernameParameter("email")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		;
		//@formatter:on
	}

}
