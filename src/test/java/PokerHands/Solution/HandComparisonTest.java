package PokerHands.Solution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;

public class HandComparisonTest {
	
	private PlayerHand player1Hand;
    private PlayerHand player2Hand;
    
    @BeforeEach
    void setUp() {
        player1Hand = new PlayerHand();
        player2Hand = new PlayerHand();
    }

    @Test
    void testHigherPairWins() {
        
        player1Hand.addCard(new Card(Suits.HEARTS, Values.EIGHT));
        player1Hand.addCard(new Card(Suits.DIAMONDS, Values.EIGHT));
        player1Hand.addCard(new Card(Suits.CLUBS, Values.FOUR));
        player1Hand.addCard(new Card(Suits.SPADES, Values.THREE));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.TWO));
        
        player2Hand.addCard(new Card(Suits.HEARTS, Values.SEVEN));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.SEVEN));
        player2Hand.addCard(new Card(Suits.CLUBS, Values.FIVE));
        player2Hand.addCard(new Card(Suits.SPADES, Values.THREE));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.TWO));

        int comparisonResult = player1Hand.compareTo(player2Hand);
        assertTrue(comparisonResult > 0, "Player 1's hand wins with a higher pair");
    }
    
    @Test
    void testSamePairHigherKickerWins() {
        
        player1Hand.addCard(new Card(Suits.HEARTS, Values.NINE));
        player1Hand.addCard(new Card(Suits.DIAMONDS, Values.NINE)); // Pair
        player1Hand.addCard(new Card(Suits.CLUBS, Values.ACE)); // Highest kicker
        player1Hand.addCard(new Card(Suits.SPADES, Values.FOUR));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.THREE));
        
        player2Hand.addCard(new Card(Suits.HEARTS, Values.NINE));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.NINE)); // Pair
        player2Hand.addCard(new Card(Suits.CLUBS, Values.KING)); // Lower kicker than ACE
        player2Hand.addCard(new Card(Suits.SPADES, Values.FIVE));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.TWO));

        int comparisonResult = player1Hand.compareTo(player2Hand);
        assertTrue(comparisonResult > 0, "Player 1's hand wins with a higher kicker (Ace vs. King)");
    }
    
    @Test
    void testTieWithStraights() {
        
        player1Hand.addCard(new Card(Suits.CLUBS, Values.FIVE));
        player1Hand.addCard(new Card(Suits.DIAMONDS, Values.FOUR));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.THREE));
        player1Hand.addCard(new Card(Suits.SPADES, Values.TWO));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.ACE)); // Ace-low straight
        
        player2Hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
        player2Hand.addCard(new Card(Suits.CLUBS, Values.FOUR));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.THREE));
        player2Hand.addCard(new Card(Suits.SPADES, Values.TWO));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.ACE)); // Ace-low straight, same as player 1

        int comparisonResult = player1Hand.compareTo(player2Hand);
        assertEquals(0, comparisonResult, "Equal straights, hand is a tie");
    }
    
    @Test
    void testAceLowStraightVsHigherStraight() {
        
        player1Hand.addCard(new Card(Suits.CLUBS, Values.FIVE));
        player1Hand.addCard(new Card(Suits.DIAMONDS, Values.FOUR));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.THREE));
        player1Hand.addCard(new Card(Suits.SPADES, Values.TWO));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.ACE)); // Ace-low straight
        
        player2Hand.addCard(new Card(Suits.HEARTS, Values.FIVE));
        player2Hand.addCard(new Card(Suits.CLUBS, Values.SIX));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.SEVEN));
        player2Hand.addCard(new Card(Suits.SPADES, Values.EIGHT));
        player2Hand.addCard(new Card(Suits.DIAMONDS, Values.NINE)); // 5-9 straight
        
        int comparisonResult = player1Hand.compareTo(player2Hand);
        System.out.println(comparisonResult);
        assertTrue(comparisonResult < 0, "Player 2's hand wins with a higher straight");
    }
    
    @Test
    void testTieWithFlushes() {
        
        player1Hand.addCard(new Card(Suits.HEARTS, Values.TEN));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.NINE));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.EIGHT));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.SEVEN));
        player1Hand.addCard(new Card(Suits.HEARTS, Values.TWO));
        
        player2Hand.addCard(new Card(Suits.HEARTS, Values.TEN));
        player2Hand.addCard(new Card(Suits.HEARTS, Values.NINE));
        player2Hand.addCard(new Card(Suits.HEARTS, Values.EIGHT));
        player2Hand.addCard(new Card(Suits.HEARTS, Values.SEVEN));
        player2Hand.addCard(new Card(Suits.HEARTS, Values.TWO));

        int comparisonResult = player1Hand.compareTo(player2Hand);
        assertEquals(0, comparisonResult, "Equal flushes, hand is a tie");
    }
}