package com.saymonproject.dsdeliver.services;

import com.saymonproject.dsdeliver.dto.OrderDTO;
import com.saymonproject.dsdeliver.dto.ProductDTO;
import com.saymonproject.dsdeliver.entities.Order;
import com.saymonproject.dsdeliver.entities.Product;
import com.saymonproject.dsdeliver.repositories.OrderRepository;
import com.saymonproject.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional (readOnly = true)
    public List<OrderDTO> findAll(){
    List<Order> list = repository.findOrderWithProducts();
    return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

}
