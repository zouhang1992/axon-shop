package com.onion.axon.axonshop.commandend.aggregate;

import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;

@Aggregate
@NoArgsConstructor
public class Order {

    @AggregateIdentifier
    private OrderId orderId;

    private String username;

    private double payment;

    private String state;

    @AggregateMember
    private Map<String,Product> products;




}
