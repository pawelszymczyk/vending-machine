package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine
import static vendingmachine.domain.Coin.*

class WeightingCoinRecognizerTest extends Specification {

    def "should regonize value of accepted coin"(Coin coin, Money value) {
        
        when:
        WeightingCoinRecognizer recognizer = new WeightingCoinRecognizer()

        then:
        recognizer.recognizeValue(coin) == value
        
        where:
        coin || value
        NICKEL || new Money(0.05)
        DIME    || new Money(0.1)
        QUARTER || new Money(0.25)
    }
    
    def "should throw unregonized coin exception when unknown coin passed"() {
        given:
        WeightingCoinRecognizer recognizer = new WeightingCoinRecognizer()
        
        when:
        recognizer.recognizeValue(Coin.PENNY)

        then:
        thrown(UnrecognizedCoinException)
    }
    
    

}