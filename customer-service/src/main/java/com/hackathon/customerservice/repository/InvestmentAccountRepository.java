/**
 * 
 */
package com.hackathon.customerservice.repository;

import com.hackathon.customerservice.entity.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.customerservice.entity.InvestmentAccount;

import java.util.List;
import java.util.Optional;

/**
 * @author Team-1
 *
 */
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {

    List<InvestmentAccount> findAllByUserDetail(Optional<UserDetail> userDetail, Pageable pageable);

}
