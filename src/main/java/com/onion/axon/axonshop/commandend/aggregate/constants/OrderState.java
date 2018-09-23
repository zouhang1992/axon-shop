package com.onion.axon.axonshop.commandend.aggregate.constants;

import lombok.Getter;

@Getter
public enum OrderState {

    PROCESSING("PROCESSING", 1),
    CONFIRMED("CONFIRMED",2),
    CANCELLED("CANCELLED",3),
    ;

    private final String state;
    private final int stateNum;

    OrderState(String state, int stateNum) {
        this.state = state;
        this.stateNum = stateNum;
    }

    public String getStateByNum(int num) {
        OrderState[] values = OrderState.values();
        String state = null;
        for (OrderState value : values) {
            if (value.getStateNum() == num) {
                state = value.getState();
            }
        }
        return state;
    }

}
