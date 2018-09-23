//package com.onion.axon.axonshop.config;
//
//import org.axonframework.common.jpa.EntityManagerProvider;
//import org.axonframework.common.transaction.TransactionManager;
//import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
//import org.axonframework.serialization.Serializer;
//import org.axonframework.serialization.json.JacksonSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class MyJpaEventStoreEngine extends JpaEventStorageEngine {
//
//    @Bean
//   public Serializer serializer() {
//       return new JacksonSerializer();
//   }
//    public MyJpaEventStoreEngine(EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
//        super(( entityManagerProvider),transactionManager);
//    }
//
//}
