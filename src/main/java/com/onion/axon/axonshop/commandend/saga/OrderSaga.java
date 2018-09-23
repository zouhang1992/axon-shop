package com.onion.axon.axonshop.commandend.saga;

import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import com.onion.axon.axonshop.commandend.commands.ReserveProductCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackOrderCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackReservationCommand;
import com.onion.axon.axonshop.commandend.events.OrderCancelledEvent;
import com.onion.axon.axonshop.commandend.events.OrderCreatedEvent;
import com.onion.axon.axonshop.commandend.events.ProductNotEnougShEvent;
import com.onion.axon.axonshop.commandend.events.ReserveCancelledEvent;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Saga
public class OrderSaga {
    private static final Logger LOGGER = getLogger(OrderSaga.class);

    @TargetAggregateIdentifier
    private OrderId orderId;

    private Map<String, Product> toResever;

    private Map<String, Product> toRollback;

    private int toReserveNums;

    private boolean needRollback;

    @Autowired
    private CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.toResever = event.getProducts();
        toRollback = new HashMap<>();
        toReserveNums = toResever.size();
        event.getProducts().forEach(
                (productId, product) -> {
                    ReserveProductCommand command = new ReserveProductCommand(orderId, productId, toReserveNums);
                    commandGateway.send(command);
                }
        );
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductNotEnougShEvent event){
        LOGGER.info("商品数量不足");
        toReserveNums--;
        needRollback = true;
        if(toReserveNums==0)
            tryFinish();

    }

    private void tryFinish() {
        if(needRollback){
            toResever.forEach((productId,product)->{
                if(!product.isReserved()){
                    return;
                }else{
                    toRollback.put(productId,product);
                    commandGateway.send(new RollbackReservationCommand(orderId,productId,product.getNums()));
                }
            });
            if(toRollback.isEmpty()){
                commandGateway.send(new RollbackOrderCommand(orderId));
            }else {
                return;
            }
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ReserveCancelledEvent event){
        toRollback.remove(event.getProductId());
        if(toRollback.isEmpty()){
            commandGateway.send(new RollbackOrderCommand(event.getOrderId()));
        }

    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event){
        LOGGER.info("订单 {} 已经取消", event.getOrderId());

    }
}
