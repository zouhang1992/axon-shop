//package com.onion.axon.axonshop.commandend.eventhandlers;
//
//import com.onion.axon.axonshop.commandend.aggregate.constants.OrderState;
//import com.onion.axon.axonshop.commandend.events.OrderCancelledEvent;
//import com.onion.axon.axonshop.commandend.events.OrderConfirmedEvent;
//import com.onion.axon.axonshop.commandend.events.OrderCreatedEvent;
//import com.onion.axon.axonshop.persistence.entity.OrderEntity;
//import com.onion.axon.axonshop.persistence.entity.ProductEntity;
//import com.onion.axon.axonshop.persistence.repository.OrderRepository;
//import org.axonframework.eventhandling.EventHandler;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.slf4j.LoggerFactory.getLogger;
//
//@Component
//public class OrderEventHandler {
//
//    private static final Logger LOGGER = getLogger(OrderEventHandler.class);
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @EventHandler
//    public void on(OrderCreatedEvent event) {
//        Map<String, ProductEntity> productEntities = new HashMap<>();
//        event.getProducts().forEach((id, product) -> {
//            productEntities.put(id, ProductEntity.builder()
//                    .productId(product.getProductId())
//                    .name(product.getName())
//                    .price(product.getPrice())
//                    .nums(product.getNums())
//                    .build()
//            );
//        });
//        OrderEntity orderEntity = OrderEntity.builder()
//                .orderId(event.getOrderId().toString())
//                .username(event.getUsername())
//                .products(productEntities)
//                .build();
//        orderRepository.save(orderEntity);
//    }
//
//    @EventHandler
//    public void on(OrderConfirmedEvent event) {
//        OrderEntity orderEntity = orderRepository.getOne(event.getOrderId().getIdentifier());
//        if (orderEntity == null) {
//            LOGGER.error("{}---订单不存在", orderEntity.getOrderId());
//            return;
//        }
//        orderEntity.setState(OrderState.CONFIRMED.getState());
//        orderRepository.save(orderEntity);
//    }
//
//
//    @EventHandler
//    public void on(OrderCancelledEvent event) {
//        OrderEntity orderEntity = orderRepository.getOne(event.getOrderId().getIdentifier());
//        if (orderEntity == null) {
//            LOGGER.error("{}---订单不存在", orderEntity.getOrderId());
//            return;
//        }
//        orderEntity.setState(OrderState.CANCELLED.getState());
//        orderRepository.save(orderEntity);
//    }
//
//
//}
