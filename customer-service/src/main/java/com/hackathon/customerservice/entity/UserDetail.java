package com.hackathon.customerservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_detail")
public class UserDetail implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5655218856742392702L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "customer_id")
    private String customerId;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne
    private UserRole userRole;

}
