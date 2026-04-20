package com.repository.My_System.aplication;

import com.repository.My_System.domain.ports.in.DeleteCityInPort;
import com.repository.My_System.domain.ports.out.CityRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DeleteCityUseCase implements DeleteCityInPort {

    private final CityRepositoryOutputPort cityRepositoryOutputPort;

    private final Validate validate;

    @Override
    public void deleteCity(Long id) {
       validate.validateId(id);
        cityRepositoryOutputPort.deleteCity(id);
    }

}
