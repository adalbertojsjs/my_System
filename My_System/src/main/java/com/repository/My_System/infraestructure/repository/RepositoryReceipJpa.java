package com.repository.My_System.infraestructure.repository;

import com.repository.My_System.infraestructure.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryReceipJpa extends JpaRepository<ReceiptEntity, UUID> {
}
