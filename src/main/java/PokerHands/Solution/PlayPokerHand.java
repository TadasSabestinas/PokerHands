package PokerHands.Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import PokerHands.Solution.Enums.HandHierarchy;
import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;


public class PlayPokerHand {
	private Card parseCard(String cardStr) {
	    Suits suit;
	    Values value;

	    switch (cardStr.charAt(1)) {
	        case 'H': suit = Suits.HEARTS; break;
	        case 'D': suit = Suits.DIAMONDS; break;
	        case 'C': suit = Suits.CLUBS; break;
	        case 'S': suit = Suits.SPADES; break;
	        default: throw new IllegalArgumentException("Invalid card suit");
	    }

	    char valueChar = cardStr.charAt(0);
	    if (Character.isDigit(valueChar)) {
	        int numericValue = Character.getNumericValue(valueChar);
	        value = Values.values()[numericValue - 2]; // Adjust for zero-based indexing
	    } else {
	        switch (valueChar) {
	            case 'T': value = Values.TEN; break;
	            case 'J': value = Values.JACK; break;
	            case 'Q': value = Values.QUEEN; break;
	            case 'K': value = Values.KING; break;
	            case 'A': value = Values.ACE; break;
	            default: throw new IllegalArgumentException("Invalid card value");
	        }
	    }

	    return new Card(suit, value);
	}
	
	public void playGame() {
		int counter = 0;
	    String filename = "src\\main\\java\\PokerHands\\Solution\\data\\poker.txt"; // Adjust the path as necessary
	    try {
	        Scanner scanner = new Scanner(new File(filename));
	        int player1Wins = 0;
	        int player2Wins = 0;
	        
	        while (scanner.hasNextLine()) {
	        	counter++;
	            String line = scanner.nextLine();
	            String[] cardStrs = line.split(" ");
	            PlayerHand player1Hand = new PlayerHand();
	            PlayerHand player2Hand = new PlayerHand();
	            
	            for (int i = 0; i < 5; i++) {
	                player1Hand.addCard(parseCard(cardStrs[i]));
	            }
	            for (int i = 5; i < 10; i++) {
	                player2Hand.addCard(parseCard(cardStrs[i]));
	            }

	            HandHierarchy player1 = player1Hand.evaluateHand();
	            HandHierarchy player2 = player2Hand.evaluateHand();
                if (player1.ordinal() > player2.ordinal()) {
                    player1Wins++;
                } else if (player1.ordinal() < player2.ordinal()) {
                    player2Wins++;
                } 
            }     
	        System.out.println("Player 1 wins: " + player1Wins);
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
