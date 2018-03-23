package vendingmachine.domain;

import java.util.List;

interface CoinCassete {
    boolean injectCoin(Coin coin);
    Money getBalance();
    List<Coin> getCoinReturnTray();
    void resetBalance();
    boolean couldAffortFor(Product product);
}
