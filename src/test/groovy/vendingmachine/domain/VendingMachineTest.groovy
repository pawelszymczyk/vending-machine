package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [] as Set
    }

    def "should update balance when coin #coin inserted"() {
        when:
        def vendingMachine = new VendingMachine()
        vendingMachine.insert(coin)

        then:
        vendingMachine.balance.value == coin.money.value
        vendingMachine.display == vendingMachine.balanceAsString

        where:
        coin << [Coin.DIME, Coin.NICKEL, Coin.QUARTER]
    }

    def "should reject penny when inserted"() {
        when:
        def vendingMachine = new VendingMachine()
        vendingMachine.insert(Coin.PENNY)

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [Coin.PENNY] as Set
    }
}