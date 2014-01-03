package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
@SuppressWarnings("serial")
class UnrecognizedCoinException extends Exception {

    private final Coin coin;

    UnrecognizedCoinException(Coin coin) {
        super("I don't know what to do with " + coin.name());
        this.coin = coin;
    }

    Coin coin() {
        return coin;
    }

}
