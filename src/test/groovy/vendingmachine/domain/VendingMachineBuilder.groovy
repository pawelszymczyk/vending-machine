
package vendingmachine.domain

import spock.lang.Specification

/**
 *
 * @author wioleta.gozdzik
 */
class VendingMachineBuilder  {

    static final VendingMachine build(Map values = [:]) {
        def defaultValues = [
            coinBank: new FakeCoinBank(),
            recognizer: new WeightingCoinRecognizer()
        ]
        def usedValues = defaultValues << values;

        return new VendingMachine(usedValues.coinBank, usedValues.recognizer)
    }
}

