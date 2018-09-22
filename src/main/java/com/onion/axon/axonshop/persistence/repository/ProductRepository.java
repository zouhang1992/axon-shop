package com.onion.axon.axonshop.persistence.repository;

import com.onion.axon.axonshop.persistence.entities.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductView,String> {
}
