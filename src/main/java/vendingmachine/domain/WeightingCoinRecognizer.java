package vendingmachine.domain;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author wioleta.gozdzik
 */
public class WeightingCoinRecognizer implements CoinRecognizer {

    private static final Set<Coin> ACCEPTED_COINS = EnumSet.of(Coin.DIME, Coin.NICKEL, Coin.QUARTER);

    @Override
    public Money recognizeValue(Coin coin) throws UnrecognizedCoinException {
        if (ACCEPTED_COINS.contains(coin)) {
            return coin.getMoney();
        }
        throw new UnrecognizedCoinException(coin);
    }

}
