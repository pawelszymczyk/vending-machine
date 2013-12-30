package vendingmachine

import spock.lang.Specification
import vendingmachine.domain.VendingMachine

class VendingMachineSpec extends Specification {

    def "should create vending machine"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine != null
    }
}