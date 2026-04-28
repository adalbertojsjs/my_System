package com.repository.My_System.infraestructure.dto.http.response;

import com.repository.My_System.domain.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public class OrderResponse extends Order {


}
