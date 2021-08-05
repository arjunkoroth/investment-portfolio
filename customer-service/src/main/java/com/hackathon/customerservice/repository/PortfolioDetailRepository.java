package com.hackathon.customerservice.repository;

import com.hackathon.customerservice.entity.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.customerservice.entity.PortfolioDetail;

import java.util.Optional;

/**
 * @author Team-1
 *
 */
@Repository
public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetail, Integer> {

    Optional<PortfolioDetail> findByInvestementAccount(InvestmentAccount investementAccount);
}
