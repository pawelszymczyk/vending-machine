package vendingmachine.domain;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static vendingmachine.domain.Money.money;

public class VendingMachine {

    private Money balance = money(0);
    private Set<Coin> coinReturnTray;

    public VendingMachine() {
        coinReturnTray = new HashSet<>();
    }

    public String getDisplay() {
        if (getBalance().isZero()) {
            return "INSERT A COIN";
        }

        return balance.toString();
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

    public void put(Coin coin) {

        if(coin.isAccepted()) {
            balance = balance.add(coin.getMoney());
            return;
        }

        coinReturnTray.add(coin);
    }
}
