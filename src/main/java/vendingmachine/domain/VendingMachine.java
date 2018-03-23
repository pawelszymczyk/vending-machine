package vendingmachine.domain;

import java.util.List;

public class VendingMachine {

    public static final String THANK_YOU = "THANK YOU";
    public static final String INSERT_A_COIN = "INSERT A COIN";

    private final CoinCassette coinCassette = new CoinCassette();
    private final Display display = new Display();

    public VendingMachine() {
        display.show(INSERT_A_COIN);
    }

    public String getDisplay() {
        return display.getText();
    }

    public Money getBalance() {
        return coinCassette.getBalance();
    }

    public List<Coin> rejectedCoins() {
        return coinCassette.rejectedCoins();
    }

    public void insert(Coin coin) {
        CoinInsertionResult result = coinCassette.insert(coin);
        if (CoinInsertionResult.ACCEPTED.equals(result)) {
            display.show(coinCassette.getBalance().toString());
        }
    }

    public PurchaseResult select(Product product) {
        if (product.canBeBoughtFor(this.getBalance())) {
            display.showForAMoment(THANK_YOU);
            display.show(INSERT_A_COIN);
            this.coinCassette.resetInsertedCoins();
            return PurchaseResult.DISPENSED;
        }

        display.showForAMoment("PRICE " + product.getPrice());
        return PurchaseResult.REJECTED;
    }
}
