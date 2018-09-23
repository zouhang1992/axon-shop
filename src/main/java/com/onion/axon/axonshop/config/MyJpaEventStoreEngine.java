package com.onion.axon.axonshop.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyJpaEventStoreEngine extends JpaEventStorageEngine {
    public MyJpaEventStoreEngine(EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
        super(((MyEntityManagerProvider) entityManagerProvider),transactionManager);
    }
}
