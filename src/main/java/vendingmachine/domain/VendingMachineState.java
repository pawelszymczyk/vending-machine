package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
enum VendingMachineState {

    IDLE,
    COINS_INSERTED,
    UNRECOGNIZED_COIN_INSERTED,
    NOT_ENOUGH_MONEY,
    PRODUCT_SOLD,
    RETURN_CHANGE,
    PRODUCT_SOLD_OUT;

}
