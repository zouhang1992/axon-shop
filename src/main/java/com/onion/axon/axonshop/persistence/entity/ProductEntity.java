package com.onion.axon.axonshop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "t_product")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity implements Serializable {

    @Id
    private @NotNull String productId;

    private String name;

    private int nums;

    private long price;

//    private boolean reserved;
}
