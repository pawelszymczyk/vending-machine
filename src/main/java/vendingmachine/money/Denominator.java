package vendingmachine.money;

public enum Denominator {

    CENTS_500(5),
    CENTS_200(2),
    CENTS_100(1);

    public int value;

    private Denominator(int value) {
        this.value = value;
    }
}

