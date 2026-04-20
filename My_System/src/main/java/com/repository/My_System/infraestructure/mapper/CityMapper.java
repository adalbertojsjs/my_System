package com.repository.My_System.infraestructure.mapper;

import com.repository.My_System.domain.model.City;
import com.repository.My_System.infraestructure.dto.http.request.CityRequest;
import com.repository.My_System.infraestructure.dto.http.response.CityResponse;
import com.repository.My_System.infraestructure.entity.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public City toDomain(CityEntity entity){

        var city =  City.builder().id(entity.getId())
                .continents(entity.getContinents())
                .name(entity.getName())
                .countries(entity.getCountries())
                .id(entity.getId())
                .build();

        return city;

    }

    public CityEntity toEntity(City city){
        var cityEntity = new CityEntity(
                city.getId(),
                city.getName(),
                city.getCountries(),
                city.getContinents());

        return cityEntity;
    }


    public  City requestToDomain(CityRequest request){

        var city = City
                .builder()
                .name(request.getName())
                .continents(request.getContinents())
                .countries(request.getContries())
                .build();

        return city;
    }

    public CityResponse domainToResponse(City city){

         var response = CityResponse
                 .builder()
                 .id(city.getId())
                 .name(city.getName())
                 .countries(city.getCountries())
                 .continents(city.getContinents())
                 .build();

         return response;
    }

}
