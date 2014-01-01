package vendingmachine.domain

import spock.lang.Specification

import static vendingmachine.domain.Coin.*

/**
 * @author bartosz walacik
 */
class CoinTest extends Specification {
    def "should keep proper value of coins"(Coin coin, expectedValue) {
        expect:
        coin.value == expectedValue

        where:
        coin        | expectedValue
        PENNY   | 0.01
        NICKEL  | 0.05
        DIME    | 0.1
        QUARTER | 0.25
    }
}
