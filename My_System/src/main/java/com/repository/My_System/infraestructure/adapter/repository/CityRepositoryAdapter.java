package com.repository.My_System.infraestructure.adapter.repository;

import com.repository.My_System.domain.model.City;
import com.repository.My_System.domain.ports.out.CityRepositoryOutputPort;
import com.repository.My_System.infraestructure.entity.CityEntity;
import com.repository.My_System.infraestructure.mapper.CityMapper;
import com.repository.My_System.infraestructure.repository.CityRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class CityRepositoryAdapter implements CityRepositoryOutputPort {

        private CityMapper cityMapper;
        private CityRepositoryJpa repositoryJpa;


    @Override
    public List<City> findAll() {
        var city = repositoryJpa.findAll();

        return city.stream().map(cityMapper::toDomain).toList();
    }

    @Override
    public City findCity(Long id) {
        var city = repositoryJpa.findById(id).orElseThrow( () -> new RuntimeException("id not found"));

       return cityMapper.toDomain(city);
    }

    @Override
    public void deleteCity(Long id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public City saveCity(City city) {
        CityEntity cityEntity = repositoryJpa.save(cityMapper.toEntity(city));
        return cityMapper.toDomain(cityEntity);

    }
}
