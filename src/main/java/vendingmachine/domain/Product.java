package vendingmachine.domain;

import static vendingmachine.domain.Money.money;

/**
 * @author bartosz walacik
 */
public enum Product {
    COLA (money(2)),
    CHIPS(money(3.1)),
    CANDY(money(2.5));

    private Money price;

    Product(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
