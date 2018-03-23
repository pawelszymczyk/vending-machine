package vendingmachine.domain;

import java.util.HashSet;
import java.util.Set;

class CoinReturnTray {

    private Set<Coin> coinReturnTray;

    CoinReturnTray() {
        this.coinReturnTray = new HashSet<>();
    }

    void add(Coin coin) {
        coinReturnTray.add(coin);
    }

    public Set<Coin> getValues() {
        return coinReturnTray;
    }
}
