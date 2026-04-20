package com.repository.My_System.aplication;


import com.repository.My_System.domain.model.City;
import com.repository.My_System.domain.ports.in.CrearCityInPort;
import com.repository.My_System.domain.ports.out.CityRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CrearCityUseCase implements CrearCityInPort {

    private final CityRepositoryOutputPort cityRepositoryOutputPort;
    private final Validate validate;

    @Override
    public City createCity(City city) {

        validate.nameNoNull(city.getName());
        validate.continentsNoNull(city.getContinents());
        validate.contriesNoNull(city.getCountries());

        var cityCreated = City.builder()
                .name(city.getName())
                .continents(city.getContinents())
                .countries(city.getCountries())
                .build();
        return cityRepositoryOutputPort.saveCity(cityCreated);
    }
}
