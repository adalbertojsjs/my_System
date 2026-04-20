package com.repository.My_System.domain.ports.out;

import com.repository.My_System.domain.model.City;

import java.util.List;


public interface CityRepositoryOutputPort {

    List<City> findAll();

    City findCity(Long id);

    void deleteCity(Long id);

    City saveCity(City city);
}
