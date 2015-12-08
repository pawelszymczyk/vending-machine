package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine
import vendingmachine.integration.EmailService

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when powered on"() {
        when:
        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine().build()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
    }

    def "should increase balance when accepted coin inserted"() {
        given:
        WeightingCoinRecognizer recognizer = Stub(WeightingCoinRecognizer)
        recognizer.recognizeValue(_) >> {new Money(1)}

        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine(recognizer: recognizer).build()

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

        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine(recognizer: recognizer, coinBank: coinBank).build()

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

        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine(recognizer: recognizer,coinBank: coinBank).build()
        vendingMachine.insertCoin(Coin.PENNY)

        Clock clock = new Clock(vendingMachine)

        when:
        clock.nextTick()

        then:
        vendingMachine.display == "INSERT A COIN"
    }

    def "should return to BALANCE: state after displaying UNRECOGNIZED COIN if other coins inserted"() {
        given:

        CoinRecognizer recognizer = Stub(CoinRecognizer)
        recognizer.recognizeValue(_ as Coin) >> {return new Money(1)} >> {Coin coin -> throw new UnrecognizedCoinException(coin)}

        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine(recognizer: recognizer).build()
        vendingMachine.insertCoin(Coin.NICKEL)
        vendingMachine.insertCoin(Coin.PENNY)

        Clock clock = new Clock(vendingMachine)

        when:
        clock.nextTick()

        then:
        vendingMachine.display == "BALANCE: 1.00"
    }

    def "should sell product when user inserted enough coins"() {
        given:
        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine()
        .with({Coin.NICKEL}: 10).build()

        Clock clock = new Clock(vendingMachine);

        when:
        vendingMachine.buy(Product.CHIPS)

        then:
        vendingMachine.display == "THANK YOU <3"
    }
    
    def "should send email when product is sold out"(){
        given:
        ProductStorage productStorage = Mock(ProductStorage)
        EmailService emailService = Mock(EmailService)
        VendingMachine vendingMachine = VendingMachineBuilder.vendingMachine(productStorage: productStorage,
            emailService: emailService).build()
                            
        productStorage.hasProduct(_) >> false
         
        when:
        vendingMachine.buy(Product.COLA)
        
        then:
        1*emailService.sendSupplyRequestToVendor(Product.COLA)
        vendingMachine.display == "PRODUCT IS SOLD OUT"
    }
    
    

}
