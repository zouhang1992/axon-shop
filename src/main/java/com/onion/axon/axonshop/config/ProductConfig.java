package com.onion.axon.axonshop.config;

import com.onion.axon.axonshop.commandend.aggregate.Product;
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
public class ProductConfig {

    @Autowired
    private EventStorageEngine eventStorageEngine;

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

    @Bean
    public AggregateFactory<Product> productAggregateAggregateFactory() {
        SpringPrototypeAggregateFactory<Product> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("product");
        return aggregateFactory;
    }

    @Bean
    public EventSourcingRepository<Product> productRepository() {
        EventSourcingRepository<Product> productEventSourcingRepository = new EventSourcingRepository<>(
                productAggregateAggregateFactory(), eventStore
        );
        return productEventSourcingRepository;
    }
}
