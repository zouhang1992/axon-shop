package com.onion.axon.axonshop.commandend.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onion.axon.axonshop.commandend.commands.CreateProductCommand;
import com.onion.axon.axonshop.commandend.commands.ReserveProductCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackReservationCommand;
import com.onion.axon.axonshop.commandend.events.ProductCreatedEvent;
import com.onion.axon.axonshop.commandend.events.ProductNotEnougShEvent;
import com.onion.axon.axonshop.commandend.events.ProductReservedEvent;
import com.onion.axon.axonshop.commandend.events.ReserveCancelledEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.slf4j.LoggerFactory.getLogger;

@Data
@Builder
@Aggregate
@AllArgsConstructor
public class Product implements Serializable {

    private static final Logger LOGGER = getLogger(Product.class);
    //    @Id
    @AggregateIdentifier
    private String productId;

    private String name;

    private int nums;

    private long price;

    private boolean reserved;

    public Product() {
//        this.productId = IdentifierFactory.getInstance().generateIdentifier();
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        apply(new ProductCreatedEvent(command.getProductId(),command.getName(),command.getPrice(),command.getNums()));
    }

    //订单创建事件
    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        this.productId = event.getProductId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.nums = event.getNums();
    }

    public  void reserver (ReserveProductCommand command){
        if(nums>command.getNums()){
            apply(new ProductReservedEvent(command.getOrderId(),command.getProductId(),command.getNums()));
        }else {
            apply(new ProductNotEnougShEvent(command.getOrderId(),command.getProductId()));
        }
    }

    public void cancellReserve(RollbackReservationCommand command){
        apply(new ProductReservedEvent(command.getOrderId(),command.getProductId(),command.getNums()));
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent event){
        int originalNums = nums;
        nums = nums - event.getNums();
        LOGGER.info("product {} 减少到 nums-----{}",productId,nums);
    }

    @EventSourcingHandler
    public void on(ReserveCancelledEvent event){
        int originalNums = nums;
        nums = nums + event.getNums();
        LOGGER.info("由于撤销的原因 product {} 增加到 nums -----{}",productId,nums);
    }


    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getNums() {
        return nums;
    }

    public long getPrice() {
        return price;
    }
}

