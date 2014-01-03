
package vendingmachine.domain

import spock.lang.Specification

/**
 *
 * @author wioleta.gozdzik
 */
class VendingMachineBuilder  {

    private VendingMachine vendingMachine;

    private VendingMachineBuilder(Map values = [:]) {
        def defaultValues = [
            coinBank: new FakeCoinBank(),
            recognizer: new WeightingCoinRecognizer()
        ]
        def usedValues = defaultValues << values;

        this.vendingMachine = new VendingMachine(usedValues.coinBank, usedValues.recognizer)
    }

    static final VendingMachineBuilder vendingMachine(Map values = [:]) {
        return new VendingMachineBuilder(values);
    }

    VendingMachine build() {
        return vendingMachine
    }

    VendingMachineBuilder with(Map coins = [:]) {
        coins.each({coinCount ->
                [1..coinCount.value].each({vendingMachine.insertCoin(coinCount.key())})
            })
        return this
    }

}

