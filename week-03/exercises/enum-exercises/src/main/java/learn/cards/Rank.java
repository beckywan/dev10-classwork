package learn.cards;

public enum Rank {
    ACE("Ace",14), TWO("2", 2), THREE("3", 3), FOUR("4",4), FIVE("5", 5), SIX("6", 6), SEVEN("7",7), EIGHT("8",8), NINE("9",9), TEN("10",10), JACK("Jack", 11), QUEEN("Queen", 12), KING("King", 13);


    private final String displayText;
    private final int rankValue;



    Rank(String displayText, int rankValue) {
        this.displayText = displayText;
        this.rankValue = rankValue;
    }

    public String getDisplayText() {
        return displayText;
    }
    public int getRankValue() {
    return rankValue;
    }
}