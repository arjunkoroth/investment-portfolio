package com.hackathon.customerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "investment_account")
public class InvestmentAccount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "balance")
	private Double balance;

	@ManyToOne()
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private UserDetail userDetail;
}
