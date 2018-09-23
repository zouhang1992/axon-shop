package com.onion.axon.axonshop.config;

import com.onion.axon.axonshop.commandend.aggregate.Order;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class OrderConfig {

    @Autowired
    private EventStore eventStore;

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

    //都用到事件溯源机制  我还用得着去持久化对象本身吗？？
    // 没必要啊 直接根据事件去获取（一个事件上的）对象不就okay
    @Bean
    public Repository<Order> orderRepository(){
        EventSourcingRepository<Order> orderEventSourcingRepository = new EventSourcingRepository<>(
                orderAggregateAggregateFactory(), eventStore
        );
        return orderEventSourcingRepository;
    }
}
