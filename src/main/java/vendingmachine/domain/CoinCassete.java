package vendingmachine.domain;

import java.util.Set;

interface CoinCassete {
    boolean injectCoin(Coin coin);
    Money getBalance();
    Set<Coin> getCoinReturnTray();
}
