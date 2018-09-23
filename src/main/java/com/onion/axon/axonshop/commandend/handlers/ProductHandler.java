package com.onion.axon.axonshop.commandend.handlers;


import com.onion.axon.axonshop.commandend.aggregate.Order;
import com.onion.axon.axonshop.commandend.aggregate.Product;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductHandler {

    @Autowired
    EventBus eventBus;

    @Autowired
    private Repository<Product> productRepository;

    @Autowired
    private Repository<Order> repository;

    


}
