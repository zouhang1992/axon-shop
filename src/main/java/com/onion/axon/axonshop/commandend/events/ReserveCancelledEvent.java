package com.onion.axon.axonshop.commandend.events;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ReserveCancelledEvent {
    private String orderId;
    private String productId;
    private int nums;

}
