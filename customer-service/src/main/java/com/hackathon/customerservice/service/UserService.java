package com.hackathon.customerservice.service;

import com.hackathon.customerservice.client.StockServiceProxy;
import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;
import com.hackathon.customerservice.exceptions.NoInvestmentAccountFoundException;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.UserRepository;
import com.hackathon.customerservice.security.INGUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hackathon.customerservice.util.Error.NO_INVESTMENT_ACCOUNT_FOUND;

@Service(value = "userService")
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final InvestmentAccountRepository investmentAccountRepository;

    @Autowired
    private final StockServiceProxy serviceProxy;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<UserDetail> userList = repository.findByCustomerId(username);
        if (userList.isEmpty()) {
            log.error("Invalid username or password for user {}", username);
            throw new InvalidCredentialsException("Invalid username or password");
        } else {
            UserDetail user = userList.stream().findFirst().get();
            List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new INGUser(user.getCustomerId(), user.getPassword(), user.getId(), authorityList);
        }
    }

    public List<AccountDTO> getAccounts(String customerId,int page) {
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