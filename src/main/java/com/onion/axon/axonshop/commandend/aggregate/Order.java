package com.onion.axon.axonshop.commandend.aggregate;

import com.onion.axon.axonshop.commandend.aggregate.constants.OrderState;
import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import com.onion.axon.axonshop.commandend.commands.ConfirmOrderCommand;
import com.onion.axon.axonshop.commandend.events.OrderCancelledEvent;
import com.onion.axon.axonshop.commandend.events.OrderConfirmedEvent;
import com.onion.axon.axonshop.commandend.events.OrderCreatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

/**
 * 案 在使用axonframework时
 * 有些表隐藏起来了 比如关联表  需要自己想法创建
 * 有些表 因为字段的限制 即所有主键的字节大小不超过10000 会导致创建失败
 * 我用的是mysql
 */
@Data
@Aggregate
public class Order implements Serializable {

    @AggregateIdentifier
    private OrderId orderId;

    private String username;

    private String payment;

    private String state;


    //这需要一个中间表 t_order_products 不会自己生成
    /**
     * `order_entity_order_id` varchar(100) NOT NULL,
     *   `products_key` varchar(100) NOT NULL,
     *   `products_product_id` varchar(255) NOT NULL,
     *   PRIMARY KEY (`order_entity_order_id`,`products_key`),
     */
    //三个字段 order product的主键  以及 string的值
    //简便方法就是写一个空的（不含字段只含有主键）实体 然后给他取名为t_order_products
    //然后就有创建相应表的sql 然后只需要改一下sql 自己创建即可 自动生成的sql 如下
    /**
     *  create table t_order_products (
     *        id varchar(255) not null,
     *         order_entity_order_id varchar(255) not null,
     *         products_product_id varchar(255) not null,
     *         products_key varchar(255) not null,
     *         primary key (order_entity_order_id, products_key)
     *     ) engine=MyISAM
      */
    //将id删除
    @AggregateMember
    private Map<String,Product> products;

    public Order() {
    }

    //    @CommandHandler
    public Order(OrderId orderId,String username, Map<String, Product> products) {
        apply(new OrderCreatedEvent(orderId,username,products));
    }

    //添加商品
    public void addProduct(Product product){
        this.products.put(product.getProductId(),product);
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
    //@EventSourcingHandler收集事件处理字段的逻辑（修改值）
    // 在 @EventHandler 就行对象的转化（根对象（Aggregate）与 entity 不同 ） 再进行持久化
    //aggregate 与 entity的不同 可以参考 ddd
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
