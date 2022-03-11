package learn.poker;

import learn.cards.Card;

public class TwoCardHand implements Comparable<TwoCardHand> {

    private final Card one;
    private final Card two;

    public TwoCardHand(Card one, Card two) {
        this.one = one;
        this.two = two;
    }

    public Card getOne() {
        return one;
    }

    public Card getTwo() {
        return two;
    }

    @Override
    public int compareTo(TwoCardHand other) {
        // 1. Complete the compareTo method.
        // If the current TwoCardHand(`this`) has a lower score than the TwoCardHand parameter, compareTo returns
        // an int less than 0.
        // If `this` has a higher score than the TwoCardHand parameter, compareTo returns an int greater than 0.
        // If `this` and the TwoCardHand parameter have the same score, compareTo returns 0.
        // See Exercise04.md for scoring rules.

        int oneRankValue = this.getOne().getRank().getRankValue();
        int twoRankValue = this.getTwo().getRank().getRankValue();

        int otherOneRankValue = other.getOne().getRank().getRankValue();
        int otherTwoRankValue = other.getTwo().getRank().getRankValue();

        //both hands have pairs
        if (this.one.getRank() == this.two.getRank() && other.getOne().getRank() == other.getTwo().getRank()) {
            return Integer.compare(oneRankValue, otherOneRankValue);
        }

        // only this hand has a pair
        if (this.one.getRank() == this.two.getRank()) {
            return 1;
        }

        // only other hand has a pair
        if (other.getOne().getRank() == other.getTwo().getRank()) {
            return -1;
        }

        // no pairs
        int maxRank = Math.max(oneRankValue, twoRankValue);
        int minRank = Math.min(oneRankValue, twoRankValue);
        int otherMaxRank = Math.max(otherOneRankValue, otherTwoRankValue);
        int otherMinRank = Math.min(otherOneRankValue, otherTwoRankValue);


        //same high low is a tie
        if (maxRank == otherMaxRank && minRank == otherMinRank) {
            return 0;
        }

        // Same high means higher low card
        if (maxRank == otherMaxRank) {
            return Integer.compare(minRank, otherMinRank);
        }

        // Else highest card wins
        return Integer.compare(maxRank, otherMaxRank);
    }
}
