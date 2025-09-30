package com.saymonproject.dsdeliver.services;

import com.saymonproject.dsdeliver.dto.OrderDTO;
import com.saymonproject.dsdeliver.dto.ProductDTO;
import com.saymonproject.dsdeliver.entities.Order;
import com.saymonproject.dsdeliver.entities.Product;
import com.saymonproject.dsdeliver.entities.enuns.OrderStatus;
import com.saymonproject.dsdeliver.repositories.OrderRepository;
import com.saymonproject.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional (readOnly = true)
    public List<OrderDTO> findAll(){
    List<Order> list = repository.findOrderWithProducts();
    return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude()
        ,dto.getLongitude(), Instant.now(), OrderStatus.PENDING);

        for(ProductDTO p : dto.getProducts()){
                Product product = productRepository.getOne(p.getId());
                order.getProducts().add(product);
        }

        order = repository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id){
        Order order = repository.getOne(id);

        order.setStatus(OrderStatus.DELIVERED);
        order = repository.save(order);

        return new OrderDTO(order);
    }



}
