package vendingmachine.domain;

interface Display {
    String show();
    void updateDisplay(String text);
    void confirm();
}
