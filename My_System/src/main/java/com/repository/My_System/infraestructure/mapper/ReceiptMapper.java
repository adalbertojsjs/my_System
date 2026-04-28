package com.repository.My_System.infraestructure.mapper;

import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.infraestructure.entity.ReceiptEntity;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {


   public Receipt toDomain(ReceiptEntity entity){

       if (entity == null) return null;

       var receipt = Receipt.
               builder().
               codigo(entity.getCodigo()).
               name(entity.getName()).
               amount(entity.getAmount()).
               date(entity.getDate()).
               paymentmethod(entity.getPaymentmethod()).
               build();
        return receipt;
    }


    public ReceiptEntity toEntity(Receipt receipt){

        if (receipt == null) return null;

        var receiptEntity = ReceiptEntity.
                builder().
                codigo(receipt.getCodigo()).
                name(receipt.getName()).
                amount(receipt.getAmount()).
                date(receipt.getDate()).
                paymentmethod(receipt.getPaymentmethod()).
                build();
        return receiptEntity;
    }
}

