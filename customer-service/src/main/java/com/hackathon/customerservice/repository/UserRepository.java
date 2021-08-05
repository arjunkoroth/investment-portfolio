package com.hackathon.customerservice.repository;

import com.hackathon.customerservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Long> {
	
	public Optional<UserDetail> findByCustomerId(String customerId);
	
}
