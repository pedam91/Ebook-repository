package com.pdamjanovic.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoggedInUser extends org.springframework.security.core.userdetails.User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5187501969051612408L;

	private Long id;

	public LoggedInUser(Long id, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
