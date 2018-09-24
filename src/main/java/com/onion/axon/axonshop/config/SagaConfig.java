package com.onion.axon.axonshop.config;

import com.onion.axon.axonshop.commandend.saga.OrderSaga;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 指定saga的发布对象
 * 以及事物管理
 * 并发线程数目
 * 当然 也可以指定单线程
 * 同时还可以指定TokenStore (处理器在处理sagaevent时的token标记)
 */
@Configuration
public class SagaConfig {
    @Bean
    public SagaConfiguration mySagaConfiguration() {
        SagaConfiguration<OrderSaga> orderSagaSagaConfiguration = SagaConfiguration.subscribingSagaManager(OrderSaga.class);
        orderSagaSagaConfiguration.trackingSagaManager(OrderSaga.class)
//                .configureTrackingProcessor(c->TrackingEventProcessorConfiguration.forParallelProcessing(4));
                .configureTrackingProcessor(c -> TrackingEventProcessorConfiguration.forSingleThreadedProcessing());
        orderSagaSagaConfiguration.configureTokenStore(configuration -> configuration.getComponent(JpaTokenStore.class));
        return orderSagaSagaConfiguration;
    }
}
