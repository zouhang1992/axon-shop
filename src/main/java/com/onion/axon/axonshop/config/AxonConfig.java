package com.onion.axon.axonshop.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.common.transaction.NoTransactionManager;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.NoOpEventUpcaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//@Import({
//        OrderConfig.class,
//        ProductConfig.class,
//        SagaConfig.class
//        MyEntityManagerProvider.class,
//        MyJpaEventStoreEngine.class
//})
public class AxonConfig {
    @Bean
    public Serializer serializer() {
        return new JacksonSerializer();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntityManagerProvider myEntityManagerProvider;

    @Bean
    public TokenStore jpaTokenStore(){
        return new JpaTokenStore(myEntityManagerProvider,serializer());
    }

    //主要配置jpa 的 EntityManager
    @Bean
    public EventStorageEngine eventStorageEngine(DataSource dataSource) throws SQLException {

        EntityManagerProvider entityManagerProvider = new SimpleEntityManagerProvider(entityManager);
        return new JpaEventStorageEngine(serializer(), NoOpEventUpcaster.INSTANCE, dataSource, entityManagerProvider, NoTransactionManager.INSTANCE);
    }

}