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

    def "should return unrecongized coins back to user and display UNRECONGIZED COIN message"() {
        given:
        CoinBank coinBank = Mock(CoinBank)
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_) >> {Coin coin -> throw new UnrecognizedCoinException(coin)}

        VendingMachine vendingMachine = new VendingMachine(coinBank, recognizer)

        when:
        vendingMachine.insertCoin(Coin.PENNY)

        then:
        vendingMachine.display == "UNRECOGNIZED COIN"
        vendingMachine.balance.value == 0
        1 * coinBank.returnCoin(_)
    }

    def "should return to IDLE state after displaying UNRECOGNIZED COIN if no coins inserted"() {
        given:
        CoinBank coinBank = Mock(CoinBank)
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_) >> {Coin coin -> throw new UnrecognizedCoinException(coin)}

        VendingMachine vendingMachine = new VendingMachine(coinBank, recognizer)
        vendingMachine.insertCoin(Coin.PENNY)

        Clock clock = new Clock(vendingMachine)

        when:
        clock.nextTick()

        then:
        vendingMachine.display == "INSERT A COIN"
    }

    def "should return to BALANCE: state after displaying UNRECOGNIZED COIN if other coins inserted"() {
        given:
        CoinBank coinBank = Mock(CoinBank)
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_ as Coin) >> new Money(1) >> { Coin coin -> throw new UnrecognizedCoinException(coin)}
        recognizer.recognizeValue(_) >> new Money(1) >> {Coin coin -> throw new UnrecognizedCoinException(coin)}

        VendingMachine vendingMachine = new VendingMachine(coinBank, recognizer)
        vendingMachine.insertCoin(Coin.NICKEL)
        vendingMachine.insertCoin(Coin.PENNY)

        Clock clock = new Clock(vendingMachine)

        when:
        clock.nextTick()

        then:
        vendingMachine.display == "BALANCE: 1.00"
    }
}