package com.hackathon.customerservice.service;

import static com.hackathon.customerservice.util.Error.INCORRECT_CREDENTIALS;
import static com.hackathon.customerservice.util.Error.NO_INVESTMENT_ACCOUNT_FOUND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.exceptions.NoInvestmentAccountFoundException;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private InvestmentAccountRepository investmentAccountRepository;

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

    public List<AccountDTO> getAccounts(String customerId, int page) {
        Optional<UserDetail> userDetail = repository.findByCustomerId(customerId);
        Pageable pageWithFiveDetails = PageRequest.of(page, 5);
        List<InvestmentAccount> accounts = investmentAccountRepository.findAllByUserDetail(userDetail, pageWithFiveDetails);
        if (accounts.isEmpty()) {
            throw new NoInvestmentAccountFoundException(NO_INVESTMENT_ACCOUNT_FOUND.getErrorMessage());
        }
        List<AccountDTO> result = new ArrayList<AccountDTO>();
        accounts.forEach(account -> {
            AccountDTO accountDetails = AccountDTO.builder()
                    .accountNumber(account.getAccountNumber())
                    .balance(account.getBalance())
                    .build();
            result.add(accountDetails);

        });
        return result;
    }


}