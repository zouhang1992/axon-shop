package com.onion.axon.axonshop.commandend.events;

import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;


@Value
@AllArgsConstructor
public class OrderConfirmedEvent{

    @TargetAggregateIdentifier
    private OrderId orderId;




}

