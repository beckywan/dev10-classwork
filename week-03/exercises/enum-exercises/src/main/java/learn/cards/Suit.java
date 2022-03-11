package learn.cards;

public enum Suit {
    SPADES("Spades"), HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs");

    private final String displayText;

    Suit(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
