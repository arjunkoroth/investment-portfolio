package com.hackathon.customerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "customerId")
    private String customerId;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne
    private UserRole userRole;

    public UserDetail(long id, String customerId, String password, UserRole userRole) {
        this.id = id;
        this.customerId = customerId;
        this.password = password;
        this.userRole = userRole;
    }
}
