package com.hackathon.stockservice.repository;

import com.hackathon.stockservice.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockDetail,Long> {

}
