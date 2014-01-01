package vendingmachine.domain

import spock.lang.Specification

class MoneyTest extends Specification {

    def "should add Money"() {
        given:
        def money1 = new Money(0.5)
        def money2 = new Money(0.5)

        when:
        Money result = money1.add(money2)

        then:
        result.value == 1
    }
}