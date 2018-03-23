package vendingmachine.domain

import spock.lang.Specification

class CoinCassetteTest extends Specification {

    def "should allow to reset inserted coins"() {
        given:
        def coinCassette = new CoinCassette()
        coinCassette.insert(Coin.NICKEL)

        when:
        coinCassette.resetInsertedCoins()

        then:
        coinCassette.insertedCoins.value == Money.zero()
    }

    def "should inform if a coin was rejected"() {
        given:
        def coinCassette = new CoinCassette()

        when:
        def result = coinCassette.insert(coin)

        then:
        result == expectedResult

        where:
        coin         || expectedResult
        Coin.QUARTER || CoinInsertionResult.ACCEPTED
        Coin.DIME    || CoinInsertionResult.ACCEPTED
        Coin.NICKEL  || CoinInsertionResult.ACCEPTED
        Coin.PENNY   || CoinInsertionResult.REJECTED
    }

}
