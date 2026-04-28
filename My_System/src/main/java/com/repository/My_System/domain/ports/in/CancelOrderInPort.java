package com.repository.My_System.domain.ports.in;

import java.util.UUID;

public interface CancelOrderInPort {

    void cancelOrder(UUID orderId);
}
