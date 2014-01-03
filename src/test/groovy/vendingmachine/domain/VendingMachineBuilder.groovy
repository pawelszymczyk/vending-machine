
package vendingmachine.domain

/**
 *
 * @author wioleta.gozdzik
 */
class VendingMachineBuilder {
	
    static VendingMachine build(
        CoinBank coinBank = Mock(CoinBank),
        CoinRecognizer recognizer = new WeightingCoinRecognizer()){
        
        return new VendingMachine();
    }
}

