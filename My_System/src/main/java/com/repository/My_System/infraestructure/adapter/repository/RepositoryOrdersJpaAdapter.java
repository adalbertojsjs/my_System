package com.repository.My_System.infraestructure.adapter.repository;

import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import com.repository.My_System.infraestructure.mapper.OrderMapper;
import com.repository.My_System.infraestructure.repository.OrderRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class RepositoryOrdersJpaAdapter implements RepisitoryOrders {

        private final OrderRepositoryJpa repositoryJpa;
        private final OrderMapper mapper;

    @Override
    public Optional<Order> findOrderById(UUID orderId) {
        return repositoryJpa.findById(orderId).map(mapper::toDomain);
    }

    @Override
    public Order save(Order order) {

         var orderEntity = repositoryJpa.save(mapper.toEntity(order));

         return mapper.toDomain(orderEntity);
    }

    @Override
    public void deleteById(UUID orderId) {

        repositoryJpa.deleteById(orderId);
    }
}

