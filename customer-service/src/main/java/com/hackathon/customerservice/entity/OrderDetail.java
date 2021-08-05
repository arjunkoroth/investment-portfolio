package com.hackathon.customerservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "portfolio_id", referencedColumnName = "portfolio_id")
	private PortfolioDetail portfolioDetail;
	
	@Column(name="stock_code")
	private String stockCode;
	
	@Column(name="stock_price")
	private Double stockPrice;
	
	@Column(name="purchased_on")
	private LocalDate purchasedOn;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="total_price")
	private Double totalPrice;
}
