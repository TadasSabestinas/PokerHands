package PokerHands.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;
import PokerHands.Solution.Enums.HandHierarchy;

public class PlayerHand {
	private final List<Card> cards;

    public PlayerHand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (cards.size() < 5) {
            cards.add(card);
        } else {
            System.out.println("A hand can only contain 5 cards.");
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public void sortCardsByValue() {
        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Integer.compare(c1.getValue().ordinal(), c2.getValue().ordinal());
            }
        });
    }
    
    
    boolean isOnePair() {
        // Check for exactly one pair in the hand
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                return true;
            }
        }
        return false;
    }
    
    boolean isTwoPair() {
        int pairCount = 0;
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                pairCount++;
                i++; // skipping next card (it's part of the pair)
            }
        }
        return pairCount == 2;
    }
    
    boolean isThreeOfAKind() {
        for (int i = 0; i < 3; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue() &&
                cards.get(i + 1).getValue() == cards.get(i + 2).getValue()) {
                return true;
            }
        }
        return false;
    }
    
    boolean isStraight() {
        sortCardsByValue(); // cards are sorted
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getValue().ordinal() + 1 != cards.get(i + 1).getValue().ordinal()) {
                return false;
            }
        }
        return true;
    }
    
    boolean isFlush() {
        Suits firstSuit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true; 
    }
    
    boolean isFullHouse() {
        boolean threeStartPairEnd = cards.get(0).getValue() == cards.get(1).getValue() &&
                                    cards.get(1).getValue() == cards.get(2).getValue() &&
                                    cards.get(3).getValue() == cards.get(4).getValue();
        boolean pairStartThreeEnd = cards.get(0).getValue() == cards.get(1).getValue() &&
                                    cards.get(2).getValue() == cards.get(3).getValue() &&
                                    cards.get(3).getValue() == cards.get(4).getValue();
        return threeStartPairEnd || pairStartThreeEnd;
    }
    
    boolean isFourOfAKind() {
        boolean fourStart = cards.get(0).getValue() == cards.get(1).getValue() &&
                            cards.get(1).getValue() == cards.get(2).getValue() &&
                            cards.get(2).getValue() == cards.get(3).getValue();
        boolean fourEnd = cards.get(1).getValue() == cards.get(2).getValue() &&
                          cards.get(2).getValue() == cards.get(3).getValue() &&
                          cards.get(3).getValue() == cards.get(4).getValue();
        return fourStart || fourEnd;
    }
    
    public HandHierarchy evaluateHand() {
        sortCardsByValue();
        
        boolean isFlush = isFlush();
        boolean isStraight = isStraight();
        
        if (isFlush && isStraight && cards.get(4).getValue() == Values.ACE) {
            return HandHierarchy.ROYAL_FLUSH;
        } else if (isFlush && isStraight) {
            return HandHierarchy.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return HandHierarchy.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return HandHierarchy.FULL_HOUSE;
        } else if (isFlush) {
            return HandHierarchy.FLUSH;
        } else if (isStraight) {
            return HandHierarchy.STRAIGHT;
        } else if (isThreeOfAKind()) {
            return HandHierarchy.THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return HandHierarchy.TWO_PAIR;
        } else if (isOnePair()) {
            return HandHierarchy.ONE_PAIR;
        } else {
            return HandHierarchy.HIGH_CARD;
        }
    }
}
