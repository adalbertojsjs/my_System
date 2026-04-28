package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.model.Payment;

import java.util.UUID;

public interface PayOrderInPort {

    Payment payOrder(UUID orderId, Payment payment);

}
