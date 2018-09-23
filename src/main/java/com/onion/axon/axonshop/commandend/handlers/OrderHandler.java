package com.onion.axon.axonshop.commandend.handlers;

import com.onion.axon.axonshop.commandend.aggregate.Order;
import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.commands.ConfirmOrderCommand;
import com.onion.axon.axonshop.commandend.commands.CreateOrderCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackOrderCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<Order> repository;

    @Autowired
    private Repository<Product> productRepository;


    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        HashMap<String, Product> products = new HashMap<>();
        command.getProducts().forEach((productId, numbers) -> {
            LOGGER.debug("根据产品id获取产品: {}", productId);
            Aggregate<Product> aggregate = productRepository.load(productId);//主要去获取商品的价格 与名字
            products.put(productId, Product.builder()
                    .productId(productId)
                    .name(aggregate.invoke(a -> a.getName()))
                    .price(aggregate.invoke(a -> a.getPrice()))
                    .nums(numbers)
                    .build());
            try {
                repository.newInstance(() -> new Order(command.getOrderId(), command.getUsername(), products));
            } catch (Exception e) {
                LOGGER.debug("创建订单失败 {}", command);
                e.printStackTrace();
            }
        });
    }

    @CommandHandler
    public void handle(RollbackOrderCommand command) {
        Aggregate<Order> orderAggregate = repository.load(command.getOrderId().getIdentifier());
        orderAggregate.execute(a->a.cancle());
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command){
        Aggregate<Order> aggregate = repository.load(command.getOrderId().getIdentifier());
        aggregate.execute(a->a.confirm());
    }


}
