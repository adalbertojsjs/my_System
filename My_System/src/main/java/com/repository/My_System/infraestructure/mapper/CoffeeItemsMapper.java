package com.repository.My_System.infraestructure.mapper;

import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.infraestructure.entity.CoffeeItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoffeeItemsMapper {


    public CoffeeItem toDomain (CoffeeItemEntity entity){
        var coffe = CoffeeItem.builder()
                .id(entity.getId())
                .drink(entity.getDrink())
                .milk(entity.getMilk())
                .size(entity.getSize())
                .quantity(entity.getQuantity())
                .build();
        return coffe;
    }
    public List<CoffeeItem> toDomainList(List<CoffeeItemEntity> entity) {

        return entity.stream().map(this::toDomain).toList();
    }

    public List<CoffeeItemEntity> toEntityList(List<CoffeeItem> coffeeItems){

        return coffeeItems.stream().map(this::toEntity).toList();
    }

    public  CoffeeItemEntity toEntity(CoffeeItem coffeeItem) {
        var entity = new CoffeeItemEntity();
        entity.setId(coffeeItem.getId());
        entity.setDrink(coffeeItem.getDrink());
        entity.setQuantity(coffeeItem.getQuantity());
        entity.setMilk(coffeeItem.getMilk());
        entity.setSize(coffeeItem.getSize());
        return entity;
    }
}

