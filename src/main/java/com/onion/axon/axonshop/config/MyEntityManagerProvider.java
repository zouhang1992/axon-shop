package com.onion.axon.axonshop.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class MyEntityManagerProvider implements EntityManagerProvider {

    private  EntityManager entityManager;

    @Autowired
    public MyEntityManagerProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

//    @PersistenceContext(unitName = "myPersistenceUnit")
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
}
