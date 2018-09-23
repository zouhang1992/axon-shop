package com.onion.axon.axonshop.commandend.commands;


import com.onion.axon.axonshop.commandend.aggregate.Product;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

@Value
@AllArgsConstructor
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String username;

    private Map<String,Product> products;






}
