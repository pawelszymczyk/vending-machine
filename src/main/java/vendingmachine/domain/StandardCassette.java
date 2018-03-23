package vendingmachine.domain;

import java.util.*;

import static vendingmachine.domain.Money.zero;

class StandardCassette implements CoinCassete {

    private Money balance;
    private ReturnTray returnTray;

    StandardCassette() {
        returnTray = new ReturnTray();
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
        returnTray.add(coin);
    }

    private boolean isValidCoin(Coin coin) {
        return coin == Coin.PENNY;
    }

    private void updateBalance(Coin coin) {
        balance = balance.add(coin.getMoney());
    }

    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(returnTray.coinReturnTray);
    }

    @Override
    public void resetBalance() {
        balance = Money.zero();
    }

    @Override
    public boolean couldAffortFor(Product product) {
        Money productPrice = product.getPrice();
        return balance.isGreaterThen(productPrice);
    }

    @Override
    public Money getBalance() {
        return balance;
    }

    private class ReturnTray {
        private List<Coin> coinReturnTray;

        ReturnTray() {
            coinReturnTray = new ArrayList<>();
        }

        void add(Coin coin) {
            coinReturnTray.add(coin);
        }
    }
}