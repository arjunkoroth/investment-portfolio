/**
 * 
 */
package com.hackathon.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.customerservice.entity.OrderDetail;

/**
 * @author Team-1
 *
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
