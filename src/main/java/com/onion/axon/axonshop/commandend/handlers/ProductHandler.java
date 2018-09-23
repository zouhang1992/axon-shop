package com.onion.axon.axonshop.commandend.handlers;


import com.onion.axon.axonshop.commandend.aggregate.Order;
import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.commands.ReserveProductCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackReservationCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ProductHandler {

    private static final Logger LOGGER = getLogger(ProductHandler.class);

    @Autowired
    private Repository<Product> productRepository;

    @CommandHandler
    public void on(ReserveProductCommand command){
        Aggregate<Product> aggregate = productRepository.load(command.getProductId());
        aggregate.execute(a->a.reserver(command));
    }

    @CommandHandler
    public void on(RollbackReservationCommand command){
        Aggregate<Product> aggregate = productRepository.load(command.getProductId());
        aggregate.execute(a->a.cancellReserve(command));
    }


}
