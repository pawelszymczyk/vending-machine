package vendingmachine.domain;

import java.util.LinkedList;
import java.util.Queue;
import vendingmachine.integration.EmailService;

public class VendingMachine implements TickListener {

    private final CoinBank coinBank;

    private final ProductStorage productStorage;

    private final CoinRecognizer coinRecognizer;

    private final EmailService emailService;

    private VendingMachineState currentState = VendingMachineState.IDLE;

    private final Queue<VendingMachineState> stateQueue = new LinkedList<>();

    private Money balance = new Money(0);

    public VendingMachine(CoinBank coinBank, ProductStorage productStorage, CoinRecognizer coinRecognizer, EmailService emailService) {
        this.coinBank = coinBank;
        this.productStorage = productStorage;
        this.coinRecognizer = coinRecognizer;
        this.emailService = emailService;
    }

    @Override
    public void ticktock() {
        if (stateQueue.isEmpty()) {
            currentState = currentState.nextState(this);
        } else {
            currentState = stateQueue.poll();
        }
    }

    private void changeState(VendingMachineState state) {
        if (state != currentState) {
            stateQueue.add(state);
        }
        ticktock();
    }

    public String getDisplay() {
        if (currentState == VendingMachineState.IDLE) {
            return "INSERT A COIN";
        }
        if (currentState == VendingMachineState.COINS_INSERTED) {
            return "BALANCE: " + balance.toString();
        }
        if (currentState == VendingMachineState.UNRECOGNIZED_COIN_INSERTED) {
            return "UNRECOGNIZED COIN";
        }
        if (currentState == VendingMachineState.PRODUCT_SOLD) {
            return "THANK YOU <3";
        }

        if (currentState == VendingMachineState.PRODUCT_SOLD_OUT) {
            return "PRODUCT IS SOLD OUT";
        }

        return "INSERT A COIN";
    }

    public Money getBalance() {
        return balance;
    }

    public void insertCoin(Coin coin) {
        try {
            changeState(VendingMachineState.COINS_INSERTED);

            Money value = coinRecognizer.recognizeValue(coin);
            this.balance = this.balance.add(value);
        } catch (UnrecognizedCoinException exception) {
            changeState(VendingMachineState.UNRECOGNIZED_COIN_INSERTED);
            coinBank.returnCoin(coin);
        }
    }

    public void buy(Product product) {
        if (!productStorage.hasProduct(product)) {
            changeState(VendingMachineState.PRODUCT_SOLD_OUT);
            emailService.sendSupplyRequestToVendor(product);
        } else if (balance.isGreaterOrEqualTo(product.getPrice())) {
            changeState(VendingMachineState.PRODUCT_SOLD);
            this.balance = this.balance.subtract(product.getPrice());
            coinBank.returnChange(balance);
            this.balance = new Money(0);
        } else {
            changeState(VendingMachineState.NOT_ENOUGH_MONEY);
        }
    }

}
