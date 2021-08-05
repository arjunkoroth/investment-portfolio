package com.hackathon.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "portfolio_detail")
public class PortfolioDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "stock_code")
	private String stockCode;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "account_number")
	private InvestmentAccount investementAccount;
	
}
