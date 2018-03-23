package vendingmachine.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static vendingmachine.domain.Money.money;

public class VendingMachine {

    private Display display;

    private CoinCassete coinCassete;

    public VendingMachine() {
        display = new StandardDisplay();
        coinCassete = new StandardCassete();

    }

    public String getDisplay() {
        return display.show();
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    public Money getBalance() {
        return coinCassete.getBalance();
    }

    /**
     * @return unmodifiableSet
     */
    public Set<Coin> getCoinReturnTray() {
        return Collections.unmodifiableSet(coinCassete.getCoinReturnTray());
    }

    public boolean injectCoin(Coin coin) {
        if (coinCassete.injectCoin(coin)) {
            display.updateDisplay("zonk");
            return true;
        }
        return false;

    }

}
