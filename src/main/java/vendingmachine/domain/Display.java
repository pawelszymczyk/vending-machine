package vendingmachine.domain;

public class Display {

    private String text = "";
    private String temporaryText = "";

    public void show(String textToShow) {
        text = textToShow;
    }

    public String getText() {
        if (!temporaryText.isEmpty()) {
            String temporaryText = this.temporaryText;
            this.temporaryText = "";
            return temporaryText;
        }
        return text;
    }

    public void showForAMoment(String temporaryText) {
        this.temporaryText = temporaryText;
    }
}
