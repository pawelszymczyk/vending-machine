package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine

import static vendingmachine.domain.Coin.DIME
import static vendingmachine.domain.Coin.PENNY

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.coinReturnTray == [] as Set
    }

    def "should display current amount when valid coin inserted"() {
        given:
        VendingMachine vendingMachine = new VendingMachine();

        when:
        vendingMachine.insert(2);

        then:
        vendingMachine.display == "0.10\$";
        vendingMachine.coinReturnTray == [] as Set
    }

    def "should add current amount when next coin inserted"() {
        given:
        VendingMachine vendingMachine = new VendingMachine();

        when:
        vendingMachine.insert(2);
        vendingMachine.insert(3);

        then:
        vendingMachine.display == "0.35\$";
    }

    def "should reject PENNY"() {
        given:
        VendingMachine vendingMachine = new VendingMachine();

        when:
        vendingMachine.insert(1);

        then:
        vendingMachine.display == "INSERT A COIN";
        vendingMachine.coinReturnTray == [PENNY] as Set
    }
}