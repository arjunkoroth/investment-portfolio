package com.hackathon.customerservice.entity;

import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity
@Table(name="order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="portfolio_id")
	@ManyToOne
	private PortfolioDetail portfolioId;
	
	@Column(name="stock_code")
	private String stockCode;
	
	@Column(name="stock_price")
	private Double stockPrice;
	
	@Column(name="purccased_on")
	private Date purchasedOn;
	
	private Integer quanitity;
	
	private Double totalPrice;
}
