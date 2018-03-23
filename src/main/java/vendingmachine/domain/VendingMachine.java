package vendingmachine.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class VendingMachine {

    private final CoinReturnTray coinReturnTray = new CoinReturnTray();
    private final InsertedCoins insertedCoins = new InsertedCoins();

    public VendingMachine() {
    }

    public String getDisplay() {
        if (getBalance().isZero()) {
            return "INSERT A COIN";
        }

        return getBalance().toString();
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

    public void insert(Coin coin) {
        if(Coin.PENNY.equals(coin)){
            this.coinReturnTray.put(coin);
            return;
        }

        this.insertedCoins.add(coin);
    }
}
