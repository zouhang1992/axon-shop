package com.onion.axon.axonshop.commandend.aggregate;

import com.onion.axon.axonshop.commandend.aggregate.constants.OrderState;
import com.onion.axon.axonshop.commandend.commands.ConfirmOrderCommand;
import com.onion.axon.axonshop.commandend.commands.CreateOrderCommand;
import com.onion.axon.axonshop.commandend.events.OrderCancelledEvent;
import com.onion.axon.axonshop.commandend.events.OrderConfirmedEvent;
import com.onion.axon.axonshop.commandend.events.OrderCreatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Data
@Entity
@Aggregate
@Table(name = "order")
public class Order {

    @Id
//    @AggregateIdentifier
    private  String orderId;

    private String username;

    private String payment;

    private String state;


//    @OneToMany
    @Transient
    @AggregateMember
    private Map<String,Product> products;


    public Order() {
        this.orderId = IdentifierFactory.getInstance().generateIdentifier();
    }

    //    @CommandHandler
    public Order(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(),command.getUsername(),command.getProducts()));
    }

    //添加商品
    public void addProduct(Product product){
        this.products.put(product.getProductId().toString(),product);
        payment += product.getPrice()*product.getNums();
    }

    //移除商品
    public void removeProduct(String productId){
        Product product = this.products.remove(productId);
//        payment = payment -= product.getPrice()*product.getNums();
    }

    //取消订单
    public void cancle(){
        apply(new OrderCancelledEvent(orderId));
    }

    //确认订单
    public void confirm(){
        apply(new ConfirmOrderCommand(orderId));
    }


    //订单创建事件
    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        this.orderId = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
        computePrice();
    }

    private void computePrice() {
        products.forEach((id,product)->{
            payment += product.getPrice()*product.getNums();
        });
    }

    //订单确认事件
    @EventSourcingHandler
    public void on(OrderConfirmedEvent event){
        this.state = OrderState.CONFIRMED.getState();
    }
    //订单取消事件
    @EventSourcingHandler
    public void on(OrderCancelledEvent event){
        this.state = OrderState.CANCELLED.getState();
        /**
         * Marks this aggregate as deleted, instructing a repository to remove that aggregate at an appropriate time.
         * <p/>
         * Note that different repository implementations may react differently to aggregates marked for deletion.
         * Typically, Event Sourced Repositories will ignore the marking and expect deletion to be provided as part of Event
         * information.
         */
        markDeleted();//删除
    }



}
