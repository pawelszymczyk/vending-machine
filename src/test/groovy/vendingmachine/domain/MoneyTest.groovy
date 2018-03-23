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

    def "should be comparable"() {
        given:
        def money1 = new Money(ammount1)
        def money2 = new Money(ammount2)

        when:
        def result = money1.isLessOrEqual(money2)

        then:
        result == isLessOrEqual

        where:
        ammount1 | ammount2 | isLessOrEqual
        0.05     | 0.06     | true
        0.05     | 0.05     | true
        0.06     | 0.05     | false

    }
}