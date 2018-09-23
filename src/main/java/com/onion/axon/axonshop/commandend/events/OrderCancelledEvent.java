package com.onion.axon.axonshop.commandend.events;


import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelledEvent {

    @TargetAggregateIdentifier
    private OrderId orderId;




}
