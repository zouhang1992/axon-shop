package com.onion.axon.axonshop.config;

import com.onion.axon.axonshop.commandend.aggregate.Order;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class OrderConfig {

    @Autowired
    EventStorageEngine eventStorageEngine;



    @Bean
    @Scope("prototype")
    public Order order(){
        return new Order();
    }

    @Bean
    public AggregateFactory<Order> orderAggregateAggregateFactory(){
        SpringPrototypeAggregateFactory<Order> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("order");
        return aggregateFactory;
    }
}
