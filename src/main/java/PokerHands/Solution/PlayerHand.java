package PokerHands.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;
import PokerHands.Solution.Enums.HandHierarchy;

public class PlayerHand implements Comparable<PlayerHand> {

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

	public void sortCardsByValue() { // Sorts cards in hand judging the value
		cards.sort(new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return Integer.compare(c1.getValue().ordinal(), c2.getValue().ordinal());
			}
		});
	}

	public boolean isOnePair() {
		for (int i = 0; i < 4; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
				return true;
			}
		}
		return false;
	}

	public boolean isTwoPair() {
		int pairCount = 0;
		for (int i = 0; i < 4; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
				pairCount++;
				i++; // Skip the next card since it's part of the pair
			}
		}
		return pairCount == 2;
	}

	public boolean isThreeOfAKind() {
		for (int i = 0; i < 3; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()
					&& cards.get(i + 1).getValue() == cards.get(i + 2).getValue()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAceLowStraight() {
	    sortCardsByValue();
	    return cards.get(0).getValue() == Values.TWO &&
	           cards.get(1).getValue() == Values.THREE &&
	           cards.get(2).getValue() == Values.FOUR &&
	           cards.get(3).getValue() == Values.FIVE &&
	           cards.get(4).getValue() == Values.ACE;
	}

	public boolean isStraight() {
		sortCardsByValue(); // Make sure cards are sorted

		if (isAceLowStraight()) {
			return true;
		}

		for (int i = 0; i < 4; i++) {
			if (cards.get(i).getValue().ordinal() + 1 != cards.get(i + 1).getValue().ordinal()) {
				return false;
			}
		}
		return true;
	}

	public boolean isFlush() {
		Suits firstSuit = cards.get(0).getSuit();
		for (Card card : cards) {
			if (card.getSuit() != firstSuit) {
				return false;
			}
		}
		return true;
	}

	public boolean isFullHouse() {
		boolean threeStartPairEnd = cards.get(0).getValue() == cards.get(1).getValue()
				&& cards.get(1).getValue() == cards.get(2).getValue()
				&& cards.get(3).getValue() == cards.get(4).getValue();
		boolean pairStartThreeEnd = cards.get(0).getValue() == cards.get(1).getValue()
				&& cards.get(2).getValue() == cards.get(3).getValue()
				&& cards.get(3).getValue() == cards.get(4).getValue();
		return threeStartPairEnd || pairStartThreeEnd;
	}

	public boolean isFourOfAKind() {
		boolean fourStart = cards.get(0).getValue() == cards.get(1).getValue()
				&& cards.get(1).getValue() == cards.get(2).getValue()
				&& cards.get(2).getValue() == cards.get(3).getValue();
		boolean fourEnd = cards.get(1).getValue() == cards.get(2).getValue()
				&& cards.get(2).getValue() == cards.get(3).getValue()
				&& cards.get(3).getValue() == cards.get(4).getValue();
		return fourStart || fourEnd;
	}

	private int findPairValue() {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
				return cards.get(i).getValue().ordinal();
			}
		}
		return -1;
	}

	private int compareKickers(PlayerHand other) { // Compare kickers of the hand if the player 1 and player 2 hands are equal
		List<Card> thisKickers = new ArrayList<>();

		for (Card c : this.cards) {
			if (Collections.frequency(this.cards, c) == 1) {
				thisKickers.add(c);
			}
		}

		Collections.sort(thisKickers, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return Integer.compare(c2.getValue().ordinal(), c1.getValue().ordinal());
			}
		});

		List<Card> otherKickers = new ArrayList<>();
		for (Card c : other.cards) {
			if (Collections.frequency(other.cards, c) == 1) {
				otherKickers.add(c);
			}
		}

		Collections.sort(otherKickers, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return Integer.compare(c2.getValue().ordinal(), c1.getValue().ordinal());
			}
		});

		for (int i = 0; i < thisKickers.size(); i++) {
			if (thisKickers.get(i).getValue().ordinal() > otherKickers.get(i).getValue().ordinal()) {
				return 1;
			} else if (thisKickers.get(i).getValue().ordinal() < otherKickers.get(i).getValue().ordinal()) {
				return -1;
			}
		}
		return 0;
	}

	@Override
	public int compareTo(PlayerHand other) { // Determines the winner by comparing the hand with the other

		HandHierarchy thisRank = this.evaluateHand();
		HandHierarchy otherRank = other.evaluateHand();

		if (thisRank.ordinal() > otherRank.ordinal()) {
			return 1;
		} else if (thisRank.ordinal() < otherRank.ordinal()) {
			return -1;
		} else {
			if (thisRank == HandHierarchy.STRAIGHT) { // Ensures that a non-Ace-low straight wins against an ace-low straights
	            boolean thisAceLow = this.isAceLowStraight();
	            boolean otherAceLow = other.isAceLowStraight();

	            if (thisAceLow && !otherAceLow) {
	                return -1; 
	            } else if (!thisAceLow && otherAceLow) {
	                return 1; 
	            }
	        }
			
			if (thisRank == HandHierarchy.ONE_PAIR) { // If the hands have the same rank, compare the pair values
				int thisPairValue = findPairValue();
				int otherPairValue = other.findPairValue();
				if (thisPairValue != otherPairValue) {
					return Integer.compare(thisPairValue, otherPairValue);
				} else {
					return compareKickers(other); // For pairs of the same value, compare kickers
				}
			} else {
				this.sortCardsByValue(); // Compare each card's value starting from highest if hands are not pairs or have no special rank
				other.sortCardsByValue();
				for (int i = 4; i >= 0; i--) {
					int thisValue = this.cards.get(i).getValue().ordinal();
					int otherValue = other.cards.get(i).getValue().ordinal();
					if (thisValue > otherValue) {
						return 1;
					} else if (thisValue < otherValue) {
						return -1;
					}
				}
				return 0;
			}
		}
	}

	public HandHierarchy evaluateHand() { // Evaluate the rank of each hand

		int handRank = -1;
		if (isFlush() && isStraight() && cards.get(4).getValue() == Values.ACE) {
			handRank = 0;
		} else if (isFlush() && isStraight()) {
			handRank = 1;
		} else if (isFourOfAKind()) {
			handRank = 2;
		} else if (isFullHouse()) {
			handRank = 3;
		} else if (isFlush()) {
			handRank = 4;
		} else if (isStraight()) {
			handRank = 5;
		} else if (isThreeOfAKind()) {
			handRank = 6;
		} else if (isTwoPair()) {
			handRank = 7;
		} else if (isOnePair()) {
			handRank = 8;
		} else {
			handRank = 9;
		}

		switch (handRank) {
		case 0:
			return HandHierarchy.ROYAL_FLUSH;
		case 1:
			return HandHierarchy.STRAIGHT_FLUSH;
		case 2:
			return HandHierarchy.FOUR_OF_A_KIND;
		case 3:
			return HandHierarchy.FULL_HOUSE;
		case 4:
			return HandHierarchy.FLUSH;
		case 5:
			return HandHierarchy.STRAIGHT;
		case 6:
			return HandHierarchy.THREE_OF_A_KIND;
		case 7:
			return HandHierarchy.TWO_PAIR;
		case 8:
			return HandHierarchy.ONE_PAIR;
		default:
			return HandHierarchy.HIGH_CARD;
		}
	}
}