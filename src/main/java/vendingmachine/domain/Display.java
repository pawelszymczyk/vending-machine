package vendingmachine.domain;

class Display {

    private String displayText;

    Display(String displayText) {
        this.displayText = displayText;
    }

    String displayedText() {
        return displayText;
    }

    void adjustDisplayedText(String displayText) {
        this.displayText = displayText;
    }
}
