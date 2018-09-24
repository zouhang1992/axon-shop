package com.onion.axon.axonshop.persistence.repository;

import com.onion.axon.axonshop.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource( path = "orders")
public interface OrderEntityRepository extends JpaRepository<OrderEntity,String> ,
        CrudRepository<OrderEntity,String> {
}
