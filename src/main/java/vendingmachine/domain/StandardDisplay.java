package vendingmachine.domain;

class StandardDisplay implements Display {

    static final String NO_COIN_MESSAGE = "INSERT A COIN";

    private String currentMessage = NO_COIN_MESSAGE;

    @Override
    public String show() {
        return currentMessage;
    }

    @Override
    public void updateDisplay(String text) {
        currentMessage = text;
    }
}
