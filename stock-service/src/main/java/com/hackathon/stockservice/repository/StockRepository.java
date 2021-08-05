package com.hackathon.stockservice.repository;

import com.hackathon.stockservice.entity.StockDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository for stock
 * @author Team-1.
 *
 */
@Repository
public interface StockRepository extends JpaRepository<StockDetail,Long> {

	/**
	 * 
	 * @param stockCode
	 * @return
	 */
	public Optional<StockDetail> findByStockCode(final String stockCode);
}
