package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumContinents;
import com.repository.My_System.domain.exceptions.CityNotFoudExceptions;
import com.repository.My_System.domain.exceptions.InvalidCoutrieExceptions;
import com.repository.My_System.domain.exceptions.InvalidEnumContinentsExceptions;
import com.repository.My_System.domain.exceptions.InvalidNameExceptions;
import org.springframework.stereotype.Component;

@Component
public class Validate {


    public void validateId(Long id){
        if(id == null || id < 0){
            throw new CityNotFoudExceptions("The city no found");
        }
    }

    public void nameNoNull(String name){
        if(name == null){
            throw new InvalidNameExceptions("The name cannot be null");
        }
    }

    public void contriesNoNull(String contries){
        if(contries == null){
            throw new InvalidCoutrieExceptions("The contries cannot be null");
        }
    }

    public void continentsNoNull(EnumContinents continents){
        if(continents == null){
            throw new InvalidEnumContinentsExceptions("The continents cannot be null");
        }
    }



}
