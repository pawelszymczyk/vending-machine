package vendingmachine.money;

public class Coin extends Money {

    private Coin(int cents) {
        super(cents);
    }

    public static Coin coin5() {
        return valueOf(Denominator.CENTS_500);
    }

    public static Coin coin2() {
        return valueOf(Denominator.CENTS_200);
    }

    public static Coin coin1() {
        return valueOf(Denominator.CENTS_100);
    }

    private static Coin valueOf(Denominator denominator) {
        return new Coin(denominator.value);
    }
}