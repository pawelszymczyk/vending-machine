package vendingmachine.domain;

import java.math.BigDecimal;
import static vendingmachine.domain.Money.money;

/**
 * @author bartosz walacik
 */
public enum Coin {
    /** 1 cent coin */
    PENNY (money(0.01), 1, false),

    /** 5 cents coin */
    NICKEL (money(0.05), 1, true),

    /** 10 cents coin */
    DIME   (money(0.1), 2, true),

    /** 25 cents coin */
    QUARTER(money(0.25), 3, true),
    ;

    private Money money;
    private int weight;
    private boolean accepted;

    Coin(Money money, int weight, boolean accepted) {
        this.money = money;
        this.weight = weight;
        this.accepted = accepted;
    }

    public Money getMoney() {
        return money;
    }

    public BigDecimal getValue() {
        return money.getValue();
    }

    public int getWeight() {
        return weight;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
