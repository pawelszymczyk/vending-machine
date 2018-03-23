package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

class ProductTest extends Specification {

    @Unroll
    def "should check if can buy #product for #balance"() {
        when:
        def result = product.canBeBoughtFor(new Money(balance))

        then:
        result == expectedResult

        where:
        balance | product       || expectedResult
        0.99    | Product.COLA  || false
        1.00    | Product.COLA  || true
        0.49    | Product.CHIPS || false
        0.50    | Product.CHIPS || true
    }
}
