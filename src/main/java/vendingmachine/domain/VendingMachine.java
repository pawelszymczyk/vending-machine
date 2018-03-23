package vendingmachine.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class VendingMachine {

    private Set<Coin> coinReturnTray;
    private Money balance;

    public VendingMachine() {
        coinReturnTray = new HashSet<>();
        balance = new Money(BigDecimal.ZERO);
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
        return balance;
    }

    public String getBalanceAsString() {
        return getBalance().toString();
    }

    /**
     * @return unmodifiableSet
     */
    public Set<Coin> getCoinReturnTray() {
        return Collections.unmodifiableSet(coinReturnTray);
    }

    public Money insert(Coin coin) {
        if (isPenny(coin)) {
            updateCoinReturnTry(coin);
            return balance;
        }
        return updateBalance(coin.getValue());
    }

    private Money updateBalance(BigDecimal valueToAdd) {
        balance = balance.add(new Money(valueToAdd));
        return balance;
    }

    private void updateCoinReturnTry(Coin coin) {
        this.coinReturnTray.add(coin);
    }

    private boolean isPenny(Coin coin) {
        return Coin.PENNY.equals(coin);
    }

}
