package com.hackathon.customerservice.controllers;

import com.hackathon.customerservice.dto.AuthToken;
import com.hackathon.customerservice.dto.LoginDto;
import com.hackathon.customerservice.security.INGUser;
import com.hackathon.customerservice.security.JwtTokenUtil;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Member/Admin login API", tags = {"Member/Admin login API"})
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@PostMapping("/login")
	public AuthToken login(@Valid @RequestBody LoginDto loginDto) {
		AuthToken authToken = null;
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getCustomerId(), loginDto.getPassword()));
		INGUser loggedInUser = (INGUser) authentication.getPrincipal();
		if(loggedInUser != null) {
			String token = "Bearer "+ tokenUtil.getApiKey(loggedInUser);
			authToken = new AuthToken(token, loggedInUser.getUsername());
		}
		return authToken;
	}
}
