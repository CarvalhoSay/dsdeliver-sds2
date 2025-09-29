package com.saymonproject.dsdeliver.repositories;

import com.saymonproject.dsdeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
