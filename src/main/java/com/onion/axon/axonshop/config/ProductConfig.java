package com.onion.axon.axonshop.config;

import com.onion.axon.axonshop.commandend.aggregate.Product;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProductConfig {


    @Bean
    @Scope("prototype")
    public Product product(){
        return new Product();
    }

    @Bean
    public AggregateFactory<Product> productAggregateAggregateFactory(){
        SpringPrototypeAggregateFactory<Product> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("product");
        return aggregateFactory;
    }

}
