package com.repository.My_System.infraestructure.mapper;

import com.repository.My_System.domain.model.Order;
import com.repository.My_System.infraestructure.dto.http.request.OrderRequest;
import com.repository.My_System.infraestructure.dto.http.response.OrderResponse;
import com.repository.My_System.infraestructure.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {


    private final CoffeeItemsMapper mapper;

    public Order toDomain(OrderEntity entity) {

        var order = Order.builder().
                id(entity.getId()).
                enumLocation(entity.getLocation()).
                items(mapper.toDomainList(entity.getItems())).
//                quantity(entity.getQuantity()).
                status(entity.getStatus()).
                build();

        return order;
    }

    public OrderEntity toEntity(Order order) {
        var entity = new OrderEntity();
        if (order.getId() != null) {
            entity.setId(order.getId());
        }
        entity.setLocation(order.getEnumLocation());
        entity.setStatus(order.getStatus());
//        entity.setQuantity(order.getQuantity());

        var items = mapper.toEntityList(order.getItems());
        entity.setItems(items);
        items.forEach(item -> item.setOrder(entity));

        return entity;
    }
    
    public Order requestToDomain(OrderRequest request){
        var order = Order
                .builder()
                .enumLocation(request
                .getEnumLocation())
                .items(request.getCoffeeItem())
                .build();
        return order;
    }

    public OrderResponse domainToResponse(Order order){
        
        var orderResponse = OrderResponse.
                builder().
                id(order.getId()).
                enumLocation(order.getEnumLocation()).
                items(order.getItems()).
                status(order.getStatus()).
                build();

        return orderResponse;
    }
}
