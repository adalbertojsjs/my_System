package com.repository.My_System.infraestructure.repository;

import com.repository.My_System.infraestructure.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface   CityRepositoryJpa extends JpaRepository<CityEntity,Long> {
}
