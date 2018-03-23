package vendingmachine.domain

import spock.lang.Specification

import static vendingmachine.domain.Coin.DIME
import static vendingmachine.domain.Coin.NICKEL
import static vendingmachine.domain.Coin.PENNY
import static vendingmachine.domain.Coin.QUARTER

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [] as Set
    }

    def "should reject pennies"() {
        when:
        def vendingMachine = new VendingMachine()
        vendingMachine.put(Coin.PENNY)

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [Coin.PENNY] as Set
    }

    def "should #validCoin be accepted"(Coin validCoin) {
        when:
        def Coin insertedCoin = validCoin
        def vendingMachine = new VendingMachine()
        vendingMachine.put(insertedCoin)

        then:
        vendingMachine.balance.value == insertedCoin.money.value
        vendingMachine.display == insertedCoin.money.toString()
        vendingMachine.coinReturnTray == [] as Set

        where:
        validCoin << [NICKEL, DIME, QUARTER]
    }
}