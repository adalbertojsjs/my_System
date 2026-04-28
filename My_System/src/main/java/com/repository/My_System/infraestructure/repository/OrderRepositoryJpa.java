package com.repository.My_System.infraestructure.repository;

import com.repository.My_System.infraestructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderEntity, UUID> {
}
