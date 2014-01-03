package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

import static vendingmachine.domain.Coin.*

/**
 * @author bartosz walacik
 */
class CoinTest extends Specification {
    @Unroll
    def "#coin should has value #expectedValue"(coin, expectedValue) {
        expect:
        coin.value == expectedValue

        where:
        coin    | expectedValue
        PENNY   | 0.01
        NICKEL  | 0.05
        DIME    | 0.1
        QUARTER | 0.25
    }

    def "should not recognize unknown coins"() {
        expect:
        Coin.recognize(10) == UNKNOWN
    }

    def "should recognize coins"(givenWeight, expectedCoin){
        expect:
        Coin.recognize(givenWeight) == expectedCoin

        where:
        givenWeight | expectedCoin
        1           | PENNY
    }


}
