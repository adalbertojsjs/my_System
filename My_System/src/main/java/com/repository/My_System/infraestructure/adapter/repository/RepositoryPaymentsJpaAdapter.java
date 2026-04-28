package com.repository.My_System.infraestructure.adapter.repository;

import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.domain.ports.out.RepositoryPayment;
import com.repository.My_System.infraestructure.mapper.PaymentMapper;
import com.repository.My_System.infraestructure.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RepositoryPaymentsJpaAdapter implements RepositoryPayment {

    private  final PaymentJpaRepository jpaRepository;
    private final PaymentMapper mapper;



    @Override
    public Payment findPaymentById(UUID uuid) {

        return  jpaRepository.findById(uuid)
                .map(mapper::toDomain)
                .orElseThrow(()-> new RuntimeException("The payment no found"));

    }

    @Override
    public Payment save(Payment payment) {

        var paymentEntity = jpaRepository.save(mapper.toEntity(payment));

        return mapper.toDomain(paymentEntity);
    }
}