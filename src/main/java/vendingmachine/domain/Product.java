package vendingmachine.domain;

import static vendingmachine.domain.Money.money;

/**
 * @author bartosz walacik
 */
public enum Product {

    COLA(money(1)),
    CHIPS(money(0.05)),
    CANDY(money(0.65));

    private final Money price;

    Product(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
