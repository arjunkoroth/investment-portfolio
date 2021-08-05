package com.hackathon.userservice.repository;

import com.hackathon.userservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Long> {
	
	public List<UserDetail> findByUsername(String username);
	
}
