package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.model.City;

public interface FindCityInPort {

    City findCity(Long id);
}
