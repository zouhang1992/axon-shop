package com.onion.axon.axonshop.commandend.aggregate;

import com.onion.axon.axonshop.commandend.aggregate.idenditifers.ProductId;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Product {

    @AggregateIdentifier
    private ProductId productId;


}
