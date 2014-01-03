package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
enum VendingMachineState {

    IDLE,
    COINS_INSERTED {
                @Override
                public VendingMachineState nextState(VendingMachine vendingMachine) {
                    return COINS_INSERTED;
                }

            },
    UNRECOGNIZED_COIN_INSERTED {
                @Override
                public VendingMachineState nextState(VendingMachine vendingMachine) {
                    if (vendingMachine.getBalance().isZero()) {
                        return IDLE;
                    }
                    return COINS_INSERTED;
                }
            },
    NOT_ENOUGH_MONEY {
                @Override
                public VendingMachineState nextState(VendingMachine vendingMachine) {
                    return COINS_INSERTED;
                }
            },
    PRODUCT_SOLD {
                @Override
                public VendingMachineState nextState(VendingMachine vendingMachine) {
                    if (vendingMachine.getBalance().isZero()) {
                        return IDLE;
                    }
                    return RETURN_CHANGE;
                }
            },
    RETURN_CHANGE, 
    
    PRODUCT_SOLD_OUT{
                @Override
                public VendingMachineState nextState(VendingMachine vendingMachine) {
                    if (vendingMachine.getBalance().isZero()) {
                        return IDLE;
                    }
                    return COINS_INSERTED;
                }
            };

    public VendingMachineState nextState(VendingMachine vendingMachine) {
        return VendingMachineState.IDLE;
    }
}
