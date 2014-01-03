package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when powered on"() {
        when:
        CoinBank coinBank = Mock(CoinBank)
        def vendingMachine = new VendingMachine(coinBank, new WeightingCoinRecognizer())

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [] as Set
    }
    
    def "should increase balance when accepted coin inserted"() {
        given:
        CoinBank coinBank = Mock(CoinBank)
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_) >> {new Money(1)}
        
        VendingMachine vendingMachine = new VendingMachine(coinBank, recognizer)
        
        when:
        vendingMachine.insertCoin(Coin.NICKEL)
        
        then:
        vendingMachine.balance.value == 1
    }
    
    def "should return unrecongized coins back to user"() {
        given:
        CoinBank coinBank = Mock(CoinBank)
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_) >> {Coin coin -> throw new UnrecognizedCoinException(coin)}
        
        VendingMachine vendingMachine = new VendingMachine(coinBank, recognizer)
        
        when:
        vendingMachine.insertCoin(Coin.PENNY)
        
        then:
        vendingMachine.balance.value == 0  
        1 * coinBank.returnCoin(_)
    }
}