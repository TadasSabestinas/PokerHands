package PokerHands.Solution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PokerHands.Solution.Enums.HandHierarchy;
import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;

public class PlayerHandTest {

	private PlayerHand hand;

	@BeforeEach
	void setUp() {
		hand = new PlayerHand();
	}

	@Test
	void testOnePair() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.DIAMONDS, Values.TWO));
		hand.addCard(new Card(Suits.CLUBS, Values.THREE));
		hand.addCard(new Card(Suits.SPADES, Values.FOUR));
		hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
		assertTrue(hand.isOnePair(), "Hand is a pair");
	}

	@Test
	void testTwoPair() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.DIAMONDS, Values.TWO));
		hand.addCard(new Card(Suits.CLUBS, Values.THREE));
		hand.addCard(new Card(Suits.SPADES, Values.THREE));
		hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
		assertTrue(hand.isTwoPair(), "Hand is a two pair");
	}

	@Test
	void testFlush() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.HEARTS, Values.THREE));
		hand.addCard(new Card(Suits.HEARTS, Values.FOUR));
		hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
		hand.addCard(new Card(Suits.HEARTS, Values.SIX));
		assertTrue(hand.isFlush(), "Hand is a flush");
	}

	@Test
	void testStraight() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.DIAMONDS, Values.THREE));
		hand.addCard(new Card(Suits.CLUBS, Values.FOUR));
		hand.addCard(new Card(Suits.SPADES, Values.FIVE));
		hand.addCard(new Card(Suits.HEARTS, Values.SIX));
		assertTrue(hand.isStraight(), "Hand is a straight");
	}

	@Test
	void testFullHouse() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.DIAMONDS, Values.TWO));
		hand.addCard(new Card(Suits.CLUBS, Values.THREE));
		hand.addCard(new Card(Suits.SPADES, Values.THREE));
		hand.addCard(new Card(Suits.HEARTS, Values.THREE));
		assertTrue(hand.isFullHouse(), "Hand is a full house");
	}

	@Test
	void testHighCard() {
		hand.addCard(new Card(Suits.HEARTS, Values.TWO));
		hand.addCard(new Card(Suits.DIAMONDS, Values.FOUR));
		hand.addCard(new Card(Suits.CLUBS, Values.SIX));
		hand.addCard(new Card(Suits.SPADES, Values.EIGHT));
		hand.addCard(new Card(Suits.HEARTS, Values.TEN));
		assertEquals(HandHierarchy.HIGH_CARD, hand.evaluateHand(), "Hand is a high card");
	}

	@Test
	void testFourOfAKind() {
		hand.addCard(new Card(Suits.HEARTS, Values.FOUR));
		hand.addCard(new Card(Suits.DIAMONDS, Values.FOUR));
		hand.addCard(new Card(Suits.CLUBS, Values.FOUR));
		hand.addCard(new Card(Suits.SPADES, Values.FOUR));
		hand.addCard(new Card(Suits.HEARTS, Values.TEN));
		assertTrue(hand.isFourOfAKind(), "Hand is a four of a kind");
	}

	@Test
	void testThreeOfAKind() {
		hand.addCard(new Card(Suits.HEARTS, Values.SEVEN));
		hand.addCard(new Card(Suits.DIAMONDS, Values.SEVEN));
		hand.addCard(new Card(Suits.CLUBS, Values.SEVEN));
		hand.addCard(new Card(Suits.SPADES, Values.FIVE));
		hand.addCard(new Card(Suits.HEARTS, Values.KING));
		assertTrue(hand.isThreeOfAKind(), "Hand is a three of a kind");
	}
	
	@Test
	void testAceLowStraight() {
	    hand.addCard(new Card(Suits.HEARTS, Values.ACE));
	    hand.addCard(new Card(Suits.CLUBS, Values.TWO));
	    hand.addCard(new Card(Suits.DIAMONDS, Values.THREE));
	    hand.addCard(new Card(Suits.SPADES, Values.FOUR));
	    hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
	    
	    assertTrue(hand.isStraight(), "hand is an Ace-low straight.");
	}
}