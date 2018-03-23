package vendingmachine.domain;

import java.util.List;

public class CoinCassette {

    final CoinReturnTray coinReturnTray = new CoinReturnTray();
    final InsertedCoins insertedCoins = new InsertedCoins();

    public CoinCassette() {
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    public Money getBalance() {
        return insertedCoins.getValue();
    }

    public List<Coin> rejectedCoins() {
        return coinReturnTray.getCoins();
    }

    public CoinInsertionResult insert(Coin coin) {
        if (Coin.PENNY.equals(coin)) {
            this.coinReturnTray.put(coin);
            return CoinInsertionResult.REJECTED;
        }

        this.insertedCoins.add(coin);
        return CoinInsertionResult.ACCEPTED;
    }

    public void resetInsertedCoins() {
        this.insertedCoins.reset();
    }
}