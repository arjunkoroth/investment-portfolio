package com.hackathon.userservice.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class INGUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final long userId;

	public INGUser(String username, String password, long userId, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}
}
