package com.hackathon.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.entity.UserRole;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;
import com.hackathon.customerservice.repository.UserRepository;


@SpringBootTest
class UserServiceTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testLoginUsingCustomerId() {
    	UserRole userRole = UserRole.builder().id(1).roleName("ROLE_CUSTOMER").build();
    	UserDetail userDetail = UserDetail.builder()
    			.id(1).customerId("mockcustomerid").password("Hackethon@123#").userRole(userRole)
    			.build();
    	List<UserDetail> list = Arrays.asList(userDetail);
    	when(userRepository.findByCustomerId("mockcustomerid")).thenReturn(list);
    	UserDetails expectedUser = userService.loadUserByUsername("mockcustomerid");
    	assertEquals(expectedUser.getUsername(), list.stream().map(UserDetail::getCustomerId).findFirst().get());
    }
    
    @Test
    void testLoginUsingCustomerId_Exception() {
    	List<UserDetail> list = new ArrayList<>();
    	when(userRepository.findByCustomerId("mockcustomerid")).thenReturn(list);
    	assertThrows(InvalidCredentialsException.class, ()-> userService.loadUserByUsername("mockcustomerid"));
    }
}
