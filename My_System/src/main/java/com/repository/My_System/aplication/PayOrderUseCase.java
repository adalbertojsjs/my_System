package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.domain.ports.in.PayOrderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import com.repository.My_System.domain.ports.out.RepositoryPayment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PayOrderUseCase implements PayOrderInPort {

    private final RepisitoryOrders repisitoryOrders;
    private final RepositoryPayment repositoryPayment;
//    private final Validate validate;

    @Override
    public Payment payOrder(UUID orderId, Payment payment) {

//        validate.validateId(orderId);
        if (orderId == null){
            throw  new OrderNotFoudExceptions("Order invalid");
        }
        var order = repisitoryOrders.findOrderById(orderId);

        if (order.getStatus() == EnumStatus.PAID) {
            throw new InvalidPaymentExceptions("the order has already been paid for");
        }
        if(order.getStatus() != EnumStatus.READY ){
            throw  new InvalidPaymentExceptions("Order no ready");
        }

        if(payment.getNumCard() == null || payment.getNumCard().length() < 3 || payment.getNumCard().length() > 20){

            throw  new InvalidPaymentExceptions("invalid card number");
        }

        if (payment.getCardHoldernName() == null){
            throw  new InvalidPaymentExceptions("The name invalid");

        }
        if (payment.getPaymentmethod() == null){
            throw  new InvalidPaymentExceptions("Invalid methodoPayment");
        }

        var costoFinal = getCost(order.getItems());

        var maskedCard = numCard(payment);
        var paymentR = Payment.builder().
                costoFinal(costoFinal).
                orderId(orderId).
                cardHoldernName(payment.getCardHoldernName()).
                numCard(maskedCard).
                paymentmethod(payment.getPaymentmethod()).
                date(LocalDate.now()).
                build();

        var paymentSved = repositoryPayment.save(paymentR);
        order.setStatus(EnumStatus.PAID);
        repisitoryOrders.save(order);

        return paymentSved;
    }

    private BigDecimal getCost(List<CoffeeItem> items) {
        BigDecimal total = BigDecimal.ZERO;

        for (CoffeeItem item : items) {
            BigDecimal price = BigDecimal.valueOf(4.0);

            if (item.getSize() == EnumSize.LARGE) {
                price = BigDecimal.valueOf(5.0);
            }

            total = total.add(price.multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    private String numCard(Payment payment ){
        var numCard = payment.getNumCard();

        String maskedCard = "****" + numCard.substring(numCard.length() - 4);

        return  maskedCard;
    }
}
