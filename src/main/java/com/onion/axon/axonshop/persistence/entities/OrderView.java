package com.onion.axon.axonshop.persistence.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "orders")
@Table(name ="order")
public class OrderView {

    @Id
    @NotNull String id;

}
