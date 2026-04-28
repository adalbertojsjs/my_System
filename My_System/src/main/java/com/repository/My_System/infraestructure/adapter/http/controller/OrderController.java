package com.repository.My_System.infraestructure.adapter.http.controller;

import com.repository.My_System.aplication.CreatedOderUseCase;
import com.repository.My_System.aplication.TakeOrderUseCase;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.domain.ports.in.CancelOrderInPort;
import com.repository.My_System.domain.ports.in.PayOrderInPort;
import com.repository.My_System.domain.ports.in.ReadReceiptInPort;
import com.repository.My_System.domain.ports.in.Update0rderInPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coffee")
public class OrderController {

//    private final OrderMapper mapper;
    private final CancelOrderInPort cancelOrderInPort;
    private final CreatedOderUseCase orderUseCase;
    private final ReadReceiptInPort readReceiptInPort;
    private final TakeOrderUseCase takeOrderUseCase;
    private final Update0rderInPort update0rderInPort;
    private final PayOrderInPort payOrderInPort;


    @GetMapping("/{id}")
    public ResponseEntity<Receipt> readReicep(@PathVariable UUID id){
        return ResponseEntity.ok(readReceiptInPort.readReceipt(id));
    }

    @PostMapping
    public ResponseEntity<Order> createdOrder(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderUseCase.createdOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> pay(@PathVariable UUID id,
                       @RequestBody Payment payment ){

        return ResponseEntity.status(HttpStatus.OK).body(payOrderInPort.payOrder(id,payment));
    }

    @PutMapping
    public  ResponseEntity<Order> updateOrder(@RequestBody Order order){

        return ResponseEntity.status(HttpStatus.OK).
                body(update0rderInPort.updateOrder(
                        order.getId(),
                        order.getEnumLocation(),
                        order.getItems()));
    }

    @GetMapping("/{id}/take")
    public ResponseEntity<Order> takeOrder(@PathVariable UUID id){

        return ResponseEntity.status(HttpStatus.OK)
                .body(takeOrderUseCase.takeOrder(id));

    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID id){

        cancelOrderInPort.cancelOrder(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
