package com.onion.axon.axonshop.commandend.events;

import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ReserveCancelledEvent {
    private OrderId orderId;
    private String productId;
    private int nums;

}
