package vendingmachine.domain;

import java.util.Collections;
import java.util.List;

public class VendingMachine {

    private Display display;
    private CoinCassete coinCassete;

    public VendingMachine() {
        display = new StandardDisplay();
        coinCassete = new StandardCassette();
    }

    public String getDisplay() {
        return display.show();
    }

    public Money getBalance() {
        return coinCassete.getBalance();
    }

    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinCassete.getCoinReturnTray());
    }

    public VendingMachine injectCoin(Coin coin) {
        if (coinCassete.injectCoin(coin)) {
            display.updateDisplay(getReadableBalance());
            return this;
        }
        return this;

    }

    private String getReadableBalance() {
        return coinCassete.getBalance().toString();
    }

    public void selectProduct(Product selectedProduct) {
        if (coinCassete.couldAffortFor(selectedProduct)) {
            display.confirm();
            coinCassete.resetBalance();
            return;
        }
        display.updateDisplay(selectedProduct.getPrice().toString());
    }
}