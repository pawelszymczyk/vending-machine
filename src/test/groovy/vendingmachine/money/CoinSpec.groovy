package vendingmachine.money

import spock.lang.Specification

import static vendingmachine.money.Coin.coin1
import static vendingmachine.money.Coin.coin2

class CoinSpec extends Specification {

    def "should add two coins"() {
        given:
        def coin1 = coin1()
        def coin2 = coin2()

        when:
        Money result = coin1.add(coin2)

        then:
        result == new Money(3)
    }
}