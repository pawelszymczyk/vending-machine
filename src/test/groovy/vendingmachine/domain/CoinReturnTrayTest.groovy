package vendingmachine.domain

import spock.lang.Specification

class CoinReturnTrayTest extends Specification {

    def "Should store rejected coins"() {
        given:
        def coinReturnTray = new CoinReturnTray()

        when:
        coinReturnTray.put(Coin.PENNY)
        coinReturnTray.put(Coin.PENNY)

        then:
        coinReturnTray.coins == [Coin.PENNY, Coin.PENNY]
    }
}
