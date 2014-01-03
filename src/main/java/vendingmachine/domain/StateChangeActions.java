package vendingmachine.domain;

import org.smartparam.engine.annotated.annotations.JavaPlugin;

/**
 *
 * @author Adam Dubiel
 */
public class StateChangeActions {

    @JavaPlugin("nextState.unrecognized")
    public VendingMachineState nextStateOnUnrecognizedCoinInserted(VendingMachine vendingMachine) {
        if (vendingMachine.getBalance().isZero()) {
            return VendingMachineState.IDLE;
        }
        return VendingMachineState.COINS_INSERTED;

    }

    @JavaPlugin("nextState.sold")
    public VendingMachineState nextStateOnProductSold(VendingMachine vendingMachine) {
        if (vendingMachine.getBalance().isZero()) {
            return VendingMachineState.IDLE;
        }
        return VendingMachineState.RETURN_CHANGE;

    }

}
