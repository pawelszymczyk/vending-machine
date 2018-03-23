package vendingmachine.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class VendingMachine {

    private CoinReturnTray coinReturnTray;
    private Money balance;
    private Display display;

    private Optional<Order> order;

    public VendingMachine() {
        coinReturnTray = new CoinReturnTray();
        balance = new Money(BigDecimal.ZERO);
        display = new Display("INSERT A COIN");
        order = Optional.empty();
    }

    public String getDisplay() {
        return display.displayedText();
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
        return Collections.unmodifiableSet(coinReturnTray.getValues());
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
        adjustOrderPeyment(valueToAdd);
        adjustDisplayText();
        return balance;
    }

    private void adjustDisplayText() {
        display.adjustDisplayedText(balance.getValue().toString());
        order.ifPresent(this::adjustDisplayForOrder);
    }

    private void adjustDisplayForOrder(Order order) {
        if (order.isOrderComplete()) {
            display.adjustDisplayedText("THANK YOU");
        } else {
            display.adjustDisplayedText(balance.getValue().toString());
        }
    }

    private void adjustOrderPeyment(BigDecimal valueToAdd) {
        order.ifPresent(o -> o.adjustPaymnet(new Money(valueToAdd)));
    }

    private void updateCoinReturnTry(Coin coin) {
        this.coinReturnTray.add(coin);
    }

    private boolean isPenny(Coin coin) {
        return Coin.PENNY.equals(coin);
    }

    public void order(Product product) {
        order = Optional.of(new Order(product, product.getPrice()));
        display.adjustDisplayedText("PRICE " + product.getPrice());
    }
}
