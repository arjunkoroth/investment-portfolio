package com.hackathon.customerservice.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.entity.UserRole;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.UserRepository;
import com.hackathon.customerservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController userController;
    @Mock
    private UserService userService;


    @Autowired
    WebApplicationContext webApplicationContext;


    protected MockMvc mvc;

    @MockBean
    private InvestmentAccountRepository investmentAccountRepository;

    @MockBean
    private UserRepository userRepository;

    List<InvestmentAccount> accountList = new ArrayList<>();
    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }



    @Test
    public void testGetInvestmentAccounts() throws Exception {
        int status = 0;
        accountList.add(new InvestmentAccount(1, "Monesh123", 8000.0,
                new UserDetail(2,"hackethoncustomer","Monesh",new UserRole(1,"ROLE_ADMIN"))));
//        accountList.add(new InvestmentAccount(2, "venkul", 4000.0),);
        Optional<UserDetail> userDetail = Optional.of(accountList.get(0).getUserDetail());
        Pageable pageWithFiveDetails = PageRequest.of(0, 5);
        String url = "http://localhost:8084/api/v1/customers/accounts?page=0";
        Mockito.when((userRepository.findByCustomerId(accountList.get(0).getUserDetail().getCustomerId()))).thenReturn(Optional.of(new UserDetail(2,"hackethoncustomer","Monesh",new UserRole(1,"ROLE_ADMIN"))));
        Mockito.when(investmentAccountRepository.findAllByUserDetail(userDetail,pageWithFiveDetails)).thenReturn(accountList);
        String inst = new ObjectMapper().writeValueAsString(accountList);
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inst))
                .andReturn();
        status = mvcResult.getResponse().getStatus();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(200, status);
    }
}

