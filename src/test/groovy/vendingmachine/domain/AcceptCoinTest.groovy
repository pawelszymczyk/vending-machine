package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class AcceptCoinTest extends Specification {

    def 'should accept valid coin'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        vendingMachine.injectCoin(coin)

        then:
        vendingMachine.balance == expectedBalance
        vendingMachine.display == expectedMessage
        vendingMachine.coinReturnTray.isEmpty()

        where:
        coin         || expectedBalance    | expectedMessage
        Coin.NICKEL  || Coin.NICKEL.money  | Coin.NICKEL.value.toString()
        Coin.QUARTER || Coin.QUARTER.money | Coin.QUARTER.value.toString()
        Coin.DIME    || Coin.DIME.money    | Coin.DIME.value.toString()
    }

    def 'should reject invalid coin'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        vendingMachine.injectCoin(coin)

        then:
        vendingMachine.balance == expectedBalance
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.getCoinReturnTray().contains(coin)

        where:
        coin       | expectedBalance
        Coin.PENNY | Money.money(0)
    }

    def 'should accept multiple coins'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        coins.each({ it -> vendingMachine.injectCoin(it) })

        then:
        vendingMachine.balance == expectedBalance
        vendingMachine.display == expectedMessage

        where:
        coins                        || expectedBalance | expectedMessage
        [Coin.QUARTER, Coin.QUARTER] || new Money(0.5)  | '0.50'
        [Coin.PENNY, Coin.QUARTER]   || new Money(0.25) | '0.25'
        [Coin.PENNY, Coin.PENNY]     || Money.zero()    | StandardDisplay.NO_COIN_MESSAGE
    }


    def 'should return to tray pennies'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        [Coin.PENNY, Coin.PENNY].each({ it -> vendingMachine.injectCoin(it) })

        then:
        vendingMachine.balance == Money.zero()
        vendingMachine.coinReturnTray == [Coin.PENNY, Coin.PENNY]
    }

    def 'should display thank you when enough coins were inserted'() {
        given:
        def vendingMachine = new VendingMachine()
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER].each({ it -> vendingMachine.injectCoin(it) })

        when:
        vendingMachine.selectProduct(Product.COLA)

        then:
        vendingMachine.display == StandardDisplay.CONFIRM_MESSAGE
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.balance == Money.zero()
    }

    def 'should display thank you when more coins were inserted'() {
        given:
        def vendingMachine = new VendingMachine()
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER].each({ it -> vendingMachine.injectCoin(it) })

        when:
        vendingMachine.selectProduct(Product.COLA)

        then:
        vendingMachine.display == StandardDisplay.CONFIRM_MESSAGE
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.balance == Money.zero()
    }

    def 'should display missing amount when less coins were inserted'() {
        given:
        def vendingMachine = new VendingMachine()
        coins.each({ it -> vendingMachine.injectCoin(it) })

        when:
        vendingMachine.selectProduct(product)

        then:
        vendingMachine.display == product.price.toString()
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.balance == balance

        where:
        coins                                      || product      | balance
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER] || Product.COLA | new Money(0.75)
        []                                         || Product.COLA | Money.zero()
    }

    def 'should reset balance for #product'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        coins.each({ it -> vendingMachine.injectCoin(it) })
        vendingMachine.selectProduct(product)

        then:
        vendingMachine.balance == Money.zero()

        where:
        coins                                                                                              || product
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER] || Product.COLA
        []                                                                                                 || Product.COLA
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]                                           || Product.CANDY
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]                                           || Product.CHIPS
    }

    def 'full process for Cola'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER].each({ it -> vendingMachine.injectCoin(it) })
        vendingMachine.selectProduct(Product.COLA)

        then:
        vendingMachine.balance == new Money(0.75)
        vendingMachine.display == Product.COLA.price.toString()
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE

        when:
        vendingMachine.injectCoin(Coin.QUARTER)
        vendingMachine.selectProduct(Product.COLA)

        then:
        vendingMachine.display == StandardDisplay.CONFIRM_MESSAGE
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.balance == Money.zero()
    }
}