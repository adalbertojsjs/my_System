package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.model.City;

import java.util.List;

public interface FindAllCityInPort {

    List<City> findAll();
}
