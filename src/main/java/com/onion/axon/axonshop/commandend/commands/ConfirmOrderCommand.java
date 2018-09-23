package com.onion.axon.axonshop.commandend.commands;


import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private OrderId orderId;




}
