package vendingmachine.domain;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static vendingmachine.domain.Coin.DIME;
import static vendingmachine.domain.Coin.NICKEL;
import static vendingmachine.domain.Coin.QUARTER;
import static vendingmachine.domain.Money.money;

public class VendingMachine {

    private static final Set<Coin> VALID_COINS = ImmutableSet.of(DIME, NICKEL, QUARTER);
    private Set<Coin> coinReturnTray;
    private Money balance;

    public VendingMachine() {
        coinReturnTray = new HashSet<>();
        balance = money(0);
    }

    public void insert(int coinWeight) {
        Coin coin = Coin.recognize(coinWeight);

        if (VALID_COINS.contains(coin)) {
            accept(coin);
        }
        else {
            reject(coin);
        }
    }

    private void accept(Coin coin) {
        ballanceUp(coin);
    }

    private void reject(Coin coin) {
        coinReturnTray.add(coin);
    }

    private void ballanceUp(Coin coin) {
        balance = balance.add(coin.getMoney());
    }

    public String getDisplay() {
        if (getBalance().isZero()) {
            return "INSERT A COIN";
        }


        return balance +"$";
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    public Money getBalance() {
        return balance;
    }

    /**
     * @return unmodifiableSet
     */
    public Set<Coin> getCoinReturnTray() {
        return Collections.unmodifiableSet(coinReturnTray);
    }
}
