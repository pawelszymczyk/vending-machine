package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
enum VendingMachineState {

    IDLE(true),
    COINS_INSERTED(true),
    UNRECOGNIZED_COIN_INSERTED(false);

    private final boolean persistent;

    private VendingMachineState(boolean persistent) {
        this.persistent = persistent;
    }

    public boolean isPersistent() {
        return persistent;
    }
}
