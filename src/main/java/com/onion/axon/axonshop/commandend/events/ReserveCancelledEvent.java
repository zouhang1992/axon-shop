package com.onion.axon.axonshop.commandend.events;

import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveCancelledEvent {
    private OrderId orderId;
    private String productId;
    private int nums;

}
