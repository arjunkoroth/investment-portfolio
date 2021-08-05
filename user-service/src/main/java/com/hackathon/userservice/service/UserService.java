package com.hackathon.userservice.service;


import com.hackathon.userservice.client.StockServiceProxy;
import com.hackathon.userservice.entity.UserDetail;
import com.hackathon.userservice.exceptions.InvalidCredentialsException;
import com.hackathon.userservice.repository.UserRepository;
import com.hackathon.userservice.security.INGUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final StockServiceProxy serviceProxy;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<UserDetail> userList = repository.findByUsername(username);
        if (userList.isEmpty()) {
            log.error("Invalid username or password for user {}", username);
            throw new InvalidCredentialsException("Invalid username or password");
        } else {
            UserDetail user = userList.stream().findFirst().get();
            List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new INGUser(user.getCustomerId(), user.getPassword(), user.getId(), authorityList);
        }
    }






}