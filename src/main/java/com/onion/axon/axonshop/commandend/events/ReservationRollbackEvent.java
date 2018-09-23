package com.onion.axon.axonshop.commandend.events;


import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class ReservationRollbackEvent {

    @TargetAggregateIdentifier
    private OrderId orderId;

    private String productId;

    private int nums;

}
