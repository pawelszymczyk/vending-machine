package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

import static vendingmachine.domain.Coin.*

/**
 * @author bartosz walacik
 */
class CoinTest extends Specification {
    @Unroll
    def "#coin should has value #expectedValue"(Coin coin, expectedValue) {
        expect:
        coin.value == expectedValue

        where:
        coin    | expectedValue
        PENNY   | 0.01
        NICKEL  | 0.05
        DIME    | 0.1
        QUARTER | 0.25
    }

    def "#coin should be #accepted"(Coin coin, boolean expected) {
        expect:
        coin.accepted == expected

        where:
        coin    | expected
        PENNY   | false
        NICKEL  | true
        DIME    | true
        QUARTER | true
    }
}
