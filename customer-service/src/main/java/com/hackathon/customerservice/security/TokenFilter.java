package com.hackathon.customerservice.security;


import com.hackathon.customerservice.service.UserService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class TokenFilter extends OncePerRequestFilter {

	private JwtTokenUtil tokenUtil;

	private UserService loginService;

	public TokenFilter(JwtTokenUtil tokenUtil, UserService loginService) {
		this.tokenUtil = tokenUtil;
		this.loginService = loginService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String token = header.split(" ")[1].trim();
        if (!tokenUtil.validate(token)) {
        	filterChain.doFilter(request, response);
        	return;
        }
        
        UserDetails userDetails = loginService.loadUserByUsername(tokenUtil.getUsername(token));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
	}

}
