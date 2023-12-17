package com.example.cmsproject1.order.repository;

import com.example.cmsproject1.order.entity.Order;
import com.example.cmsproject1.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<User> findByCardNumber(String cardNumber);
}
