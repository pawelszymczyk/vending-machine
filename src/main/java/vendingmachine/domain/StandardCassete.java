package vendingmachine.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static vendingmachine.domain.Money.zero;

class StandardCassete implements CoinCassete {

    private Money balance;
    private Set<Coin> coinReturnTray;

    public StandardCassete() {
        this.coinReturnTray = new HashSet<>();
        balance = zero();
    }

    @Override
    public boolean injectCoin(Coin coin) {
        if (isValidCoin(coin)) {
            returnCoinToTray(coin);
            return false;
        }
        updateBalance(coin);
        return true;

    }

    private void returnCoinToTray(Coin coin) {
        coinReturnTray.add(coin);
    }

    private boolean isValidCoin(Coin coin) {
        return coin == Coin.PENNY;
    }

    private void updateBalance(Coin coin) {
        balance = balance.add(coin.getMoney());
    }

    public Set<Coin> getCoinReturnTray() {
        return Collections.unmodifiableSet(coinReturnTray);
    }

    @Override
    public Money getBalance() {
        return balance;
    }
}
