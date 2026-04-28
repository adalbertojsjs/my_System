package com.repository.My_System.domain.ports.out;

import com.repository.My_System.domain.model.Payment;

import java.util.UUID;

public interface RepositoryPayment {


    Payment findPaymentById(UUID uuid);
    Payment save(Payment payment);
}
