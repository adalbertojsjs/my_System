package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.model.Receipt;

import java.util.UUID;

public interface ReadReceiptInPort {

    Receipt readReceipt(UUID orderId);

}
