package com.repository.My_System.aplication;

import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.domain.ports.in.ReadReceiptInPort;
import com.repository.My_System.domain.ports.out.RepositoryPayment;
import com.repository.My_System.domain.ports.out.RepositoryReceip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ReadReceiptUseCase implements ReadReceiptInPort {

    private final RepositoryPayment repositoryPayment;

    private final RepositoryReceip repositoryReceip;

    @Override
    public Receipt readReceipt(UUID paymentId){

        validateId(paymentId);
        var payment = repositoryPayment.findPaymentById(paymentId);

        var receipt = Receipt.
                builder().
                name(payment.getCardHoldernName()).
                amount(payment.getCostoFinal()).
                date(LocalDate.now()).
                paymentmethod(payment.getPaymentmethod()).
                build();

        repositoryReceip.save(receipt);
       return receipt;
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new InvalidPaymentExceptions("The payment no found");
        }
    }
}
