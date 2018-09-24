package com.onion.axon.axonshop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name ="t_order")
@AllArgsConstructor
public class OrderEntity implements Serializable {

    @Id
    private @NotNull String orderId;

    private String username;

    private String payment;

    private String state;

    @OneToMany
    private Map<String,ProductEntity> products;
}
