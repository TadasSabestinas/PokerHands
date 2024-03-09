package PokerHands.Solution;

public class Cards {
    private final Suits suit;
    private final Values value;

    public Cards(Suits suit, Values value) {
        this.suit = suit;
        this.value = value;
    }

    public Suits getSuit() {
        return suit;
    }

    public Values getValue() {
        return value;
    }
}