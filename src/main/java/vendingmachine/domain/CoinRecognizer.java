package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
public interface CoinRecognizer {

    Money recognizeValue(Coin coin) throws UnrecognizedCoinException;
}
