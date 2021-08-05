package com.hackathon.customerservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Override
		public List<AccountDTO> getAccounts(String customerId) {
			log.info("Getting accounts.");
			return null;
		}
	
}
