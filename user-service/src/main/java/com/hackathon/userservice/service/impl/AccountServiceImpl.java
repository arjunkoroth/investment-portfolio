package com.hackathon.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hackathon.userservice.dto.AccountDTO;
import com.hackathon.userservice.service.AccountService;

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
