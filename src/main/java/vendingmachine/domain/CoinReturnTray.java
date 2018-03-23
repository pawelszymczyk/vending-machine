package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class CoinReturnTray {

    private List<Coin> coinReturnTray = new ArrayList<>();

    public CoinReturnTray() {
    }

    public void put(Coin coin) {
        coinReturnTray.add(coin);
    }

    public List<Coin> getCoins() {
        return coinReturnTray;
    }
}