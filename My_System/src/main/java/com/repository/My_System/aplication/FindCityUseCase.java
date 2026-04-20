package com.repository.My_System.aplication;

import com.repository.My_System.domain.model.City;
import com.repository.My_System.domain.ports.in.FindCityInPort;
import com.repository.My_System.domain.ports.out.CityRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FindCityUseCase implements FindCityInPort {

    private final CityRepositoryOutputPort cityRepositoryOutputPort;

    private final Validate validate;

    @Override
    public City findCity(Long id) {
        validate.validateId(id);
        return cityRepositoryOutputPort.findCity(id);
    }

}
