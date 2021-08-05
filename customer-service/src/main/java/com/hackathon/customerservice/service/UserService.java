package com.hackathon.customerservice.service;

import static com.hackathon.customerservice.util.Error.INCORRECT_CREDENTIALS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		Optional<UserDetail> user = repository.findByCustomerId(username);
		if (!user.isPresent()) {
			log.error("Invalid username or password for user {}", username);
			throw new InvalidCredentialsException(INCORRECT_CREDENTIALS.getErrorMessage());
		} else {
			log.info("Login succeeded for user {}", username);
			UserDetail userDetail = user.get();
			List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority(userDetail.getUserRole().getRoleName()));
			return new INGUser(userDetail.getCustomerId(), userDetail.getPassword(), userDetail.getId(), authorityList);
		}
	}

}