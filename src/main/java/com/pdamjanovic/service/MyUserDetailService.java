package com.pdamjanovic.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pdamjanovic.entities.User;
import com.pdamjanovic.entities.UserRoles;
import com.pdamjanovic.util.LoggedInUser;

@Service("myUserDetailsService")
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, LockedException {

		User user = userService.findByEmail(email);

		if(user == null 
//				|| !user.isActive()	// TODO
				) {
			throw new UsernameNotFoundException("Invalid email");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		switch(user.getType()) {
		case UserRoles.ROLE_ADMIN : 
			grantedAuthorities.add(new SimpleGrantedAuthority(UserRoles.ROLE_ADMIN));
			break;
		case UserRoles.ROLE_USER :
			grantedAuthorities.add(new SimpleGrantedAuthority(UserRoles.ROLE_USER));
			break;
		}

		return new LoggedInUser(user.getId(), user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities);		

	}

}
