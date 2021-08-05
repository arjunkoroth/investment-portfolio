package com.hackathon.customerservice.service;

import static com.hackathon.customerservice.util.Error.INCORRECT_CREDENTIALS;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hackathon.customerservice.client.StockServiceProxy;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;
import com.hackathon.customerservice.repository.UserRepository;
import com.hackathon.customerservice.security.INGUser;

import lombok.extern.slf4j.Slf4j;

@Service(value = "userService")
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private StockServiceProxy serviceProxy;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Processing login request");
		List<UserDetail> userList = repository.findByCustomerId(username);
		if (userList.isEmpty()) {
			log.error("Invalid username or password for user {}", username);
			throw new InvalidCredentialsException(INCORRECT_CREDENTIALS.getErrorMessage());
		} else {
			log.info("Login succeeded for user {}", username);
			UserDetail user = userList.stream().findFirst().get();
			List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getRoleName()));
			return new INGUser(user.getCustomerId(), user.getPassword(), user.getId(), authorityList);
		}
	}

}