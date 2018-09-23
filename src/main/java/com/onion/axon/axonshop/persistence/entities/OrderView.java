//package com.onion.axon.axonshop.persistence.entities;
//
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.Map;
//
//@Data
//@Builder
//@Entity(name = "orders")
//@Table(name ="order")
//@NoArgsConstructor
//public class OrderView {
//
//    @Id
//    @GenericGenerator(name = "idGenerator",strategy = "String")
//    @GeneratedValue(generator = "idGenerator")
//    @NotNull String id;
//
//    private String username;
//
//    private String state;
//
//    @OneToMany
//    private Map<String,ProductView> products;
//
//
//
//}
