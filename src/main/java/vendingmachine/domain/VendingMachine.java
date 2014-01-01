package vendingmachine.domain;

import static vendingmachine.domain.MachineState.READY;

public class VendingMachine {
    private MachineState state;

    public VendingMachine() {
        state = READY;
    }

    public String getDisplay() {
        if (state == READY) {
            return "INSERT A COIN";
        }

        return "";
    }
}
