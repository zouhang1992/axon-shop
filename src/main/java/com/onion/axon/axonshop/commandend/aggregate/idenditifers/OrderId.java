//package com.onion.axon.axonshop.commandend.aggregate.idenditifers;
//
//import org.axonframework.commandhandling.model.AggregateIdentifier;
//import org.axonframework.common.Assert;
//import org.axonframework.common.IdentifierFactory;
//
//import javax.persistence.Id;
//import javax.persistence.Transient;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class OrderId  implements Serializable {
//
//    @Id
//    @AggregateIdentifier
//    private final String identifier;
//
//    @Transient
//    private final int hashCode;
//
//    public OrderId() {
//        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
//        this.hashCode =identifier.hashCode();
//    }
//
//
//    public OrderId(String identifier) {
//        Assert.nonNull(identifier,()->"Identifier may not be null");
//        this.identifier = identifier;
//        this.hashCode= identifier.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OrderId orderId = (OrderId) o;
//        return hashCode == orderId.hashCode &&
//                Objects.equals(identifier, orderId.identifier);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(identifier, hashCode);
//    }
//
//    public String getIdentifier() {
//        return identifier;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderId{" +
//                "identifier='" + identifier + '\'' +
//                '}';
//    }
//}
