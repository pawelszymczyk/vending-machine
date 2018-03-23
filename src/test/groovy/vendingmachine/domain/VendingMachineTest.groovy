package vendingmachine.domain

import spock.lang.Specification

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.rejectedCoins() == []
    }

    def "should display how much money was inserted"() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        coins.collect({ vendingMachine.insert(it) })

        then:
        vendingMachine.display == displayedText
        vendingMachine.balance == new Money(balance)

        where:
        coins                       || balance | displayedText
        [Coin.DIME, Coin.NICKEL]    || 0.15    | "0.15"
        [Coin.QUARTER, Coin.NICKEL] || 0.30    | "0.30"
        [Coin.QUARTER, Coin.PENNY]  || 0.25    | "0.25"
    }

    def "should reject pennies"() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        vendingMachine.insert(Coin.PENNY)

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.rejectedCoins() == [Coin.PENNY]
        vendingMachine.getBalance() == new Money(0)
    }

}