package vendingmachine.domain

import spock.lang.Specification

class AcceptCoinTest extends Specification {

    def 'should accept valid coin'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        def isAccepted = vendingMachine.injectCoin(coin)

        then:
        isAccepted
        vendingMachine.balance == expectedBalance
        vendingMachine.display == 'zonk'
        vendingMachine.getCoinReturnTray().isEmpty()

        where:
        coin         | expectedBalance
        Coin.NICKEL  | Coin.NICKEL.money
        Coin.QUARTER | Coin.QUARTER.money
        Coin.DIME    | Coin.DIME.money
    }

    def 'should reject invalid coin'() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        def isAccepted = vendingMachine.injectCoin(coin)

        then:
        !isAccepted
        vendingMachine.balance == expectedBalance
        vendingMachine.display == StandardDisplay.NO_COIN_MESSAGE
        vendingMachine.getCoinReturnTray().contains(coin)

        where:
        coin       | expectedBalance
        Coin.PENNY | Money.money(0)
    }

}
