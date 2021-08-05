/**
 * 
 */
package com.hackathon.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.PortfolioDetail;

/**
 * @author Team-1
 *
 */
@Repository
public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetail, Integer> {

	/**
	 * 
	 * @param account
	 * @return
	 */
	public List<PortfolioDetail> findByInvestementAccount(InvestmentAccount account);
}
