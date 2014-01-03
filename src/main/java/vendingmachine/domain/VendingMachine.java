package vendingmachine.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static vendingmachine.domain.Money.money;

public class VendingMachine {

    private final Set<Coin> coinReturnTray;

    private final CoinBank coinBank;

    private final CoinRecognizer coinRecognizer;

    private VendingMachineState currentState = VendingMachineState.IDLE;

    private Money balance = new Money(0);

    public VendingMachine(CoinBank coinBank, CoinRecognizer coinRecognizer) {
        this.coinReturnTray = new HashSet<>();
        this.coinRecognizer = coinRecognizer;
        this.coinBank = coinBank;
    }

    public String getDisplay() {
        if (currentState == VendingMachineState.IDLE) {
            return "INSERT A COIN";
        }

        return "zonk";
    }

    /**
     * Current amount on display: sum of *valid* coins inserted, minus sold
     * products, minus change
     */
    public Money getBalance() {
        return balance;
    }

    /**
     * @return unmodifiableSet
     */
    public Set<Coin> getCoinReturnTray() {
        return Collections.unmodifiableSet(coinReturnTray);
    }

    public void insertCoin(Coin coin) {
        try {
            Money value = coinRecognizer.recognizeValue(coin);
            this.balance = this.balance.add(value);
        } catch (UnrecognizedCoinException exception) {
            coinBank.returnCoin(coin);
        }
    }
}
