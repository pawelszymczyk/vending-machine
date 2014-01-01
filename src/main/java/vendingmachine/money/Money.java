package vendingmachine.money;

import java.util.Objects;

/**
 * immutable
 */
public class Money {

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        return hasSameValue((Money) object);
    }

    private boolean hasSameValue(Money that) {
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
