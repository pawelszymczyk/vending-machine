package vendingmachine.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InsertedCoins {

    private final List<Coin> insertedCoins = new ArrayList<>();

    public Money getValue() {
        return new Money(insertedCoins.stream().map(Coin::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
    }

    public void add(Coin coin) {
        insertedCoins.add(coin);
    }

    public void reset() {
        insertedCoins.clear();
    }
}
