package vendingmachine.domain;

import java.util.Objects;

class StandardDisplay implements Display {

    static final String NO_COIN_MESSAGE = "INSERT A COIN";
    static final String CONFIRM_MESSAGE = "Thank you";

    private String currentMessage = NO_COIN_MESSAGE;

    @Override
    public String show() {
        if (!Objects.equals(currentMessage, NO_COIN_MESSAGE)) {
            String msgToReturn = currentMessage;
            currentMessage = NO_COIN_MESSAGE;
            return msgToReturn;
        }
        return currentMessage;
    }

    @Override
    public void updateDisplay(String text) {
        currentMessage = text;
    }

    @Override
    public void confirm() {
        currentMessage = CONFIRM_MESSAGE;
    }
}