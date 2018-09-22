package com.onion.axon.axonshop.commandend.aggregate.idenditifers;

import org.axonframework.common.Assert;
import org.axonframework.common.IdentifierFactory;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class ProductId implements Serializable {

    private final @NotNull String identifier;

    private final int hashCode;

    public ProductId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
        this.hashCode = identifier.hashCode();
    }

    public ProductId(@NotNull String identifier) {
        Assert.nonNull(identifier,()->"identifer may not null");
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductId)) return false;
        ProductId productId = (ProductId) o;
        return hashCode == productId.hashCode &&
                Objects.equals(identifier, productId.identifier);
    }

    @Override
    public int hashCode() {

        return Objects.hash(identifier, hashCode);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}
