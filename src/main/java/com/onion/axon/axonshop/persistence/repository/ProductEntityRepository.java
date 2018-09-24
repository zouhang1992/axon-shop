package com.onion.axon.axonshop.persistence.repository;

import com.onion.axon.axonshop.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductEntityRepository extends JpaRepository<ProductEntity,String>,
        CrudRepository<ProductEntity,String> {
}
