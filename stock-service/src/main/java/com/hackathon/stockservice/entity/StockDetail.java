package com.hackathon.stockservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@Entity
@Table
public class StockDetail {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stock_code")
    private String stockCode;

	public StockDetail(long id, String stockCode) {
		super();
		this.id = id;
		this.stockCode = stockCode;
	}
}