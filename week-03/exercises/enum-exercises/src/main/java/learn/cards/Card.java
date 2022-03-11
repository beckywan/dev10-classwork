package learn.cards;

public class Card {

    // 1. Add a Suit and Rank field the Card class.
    private final Suit suit;
    private final Rank rank;

    // 2. Add a constructor that accepts a Suit and Rank and sets the appropriate fields.
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // 3. Add getters for both suit and rank.
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }


    public String getName() {

        // 4. Complete the getName method.
        // Given a card's suit and rank, getName returns a String in the format:
        // "[rank] of [suit]"

        // Examples:
        // Ace of Clubs
        // 5 of Diamonds
        // King of Hearts
        // 9 of Spades

        String rankText = rank.getDisplayText();
        String suitText  = suit.getDisplayText();


        // Note: it's unlikely you'll be able to use the enum name directly since enum naming conventions
        // don't match the required output.
        return String.format("%s of %s", rankText, suitText);
    }
}
