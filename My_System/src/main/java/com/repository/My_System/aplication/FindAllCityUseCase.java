package com.repository.My_System.aplication;

import com.repository.My_System.domain.model.City;
import com.repository.My_System.domain.ports.in.FindAllCityInPort;
import com.repository.My_System.domain.ports.out.CityRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FindAllCityUseCase implements FindAllCityInPort {

    private final CityRepositoryOutputPort cityRepositoryOutputPort;

    @Override
    public List<City> findAll() {
        return cityRepositoryOutputPort.findAll();
    }
}
