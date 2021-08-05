/**
 * 
 */
package com.hackathon.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.userservice.entity.OrderDetail;

/**
 * @author Team-1
 *
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
