package vendingmachine.domain

import spock.lang.Specification

class InsertedCoinsTest extends Specification {

    def "Should sum up the value of inserted coins"() {

        given:
        def insertedCoins = new InsertedCoins()

        when:
        coins.collect { it -> insertedCoins.add(it) }

        then:
        insertedCoins.value == new Money(sum)

        where:
        coins                        || sum
        [Coin.QUARTER, Coin.NICKEL]  || 0.30
        [Coin.QUARTER, Coin.QUARTER] || 0.50
    }
}
