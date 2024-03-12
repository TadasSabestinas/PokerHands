package PokerHands.Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import PokerHands.Solution.Enums.Suits;
import PokerHands.Solution.Enums.Values;

public class PokerMatchRunner {
	private Card parseCard(String cardStr) { // Parses the string that represents the card into a Card object

		Suits suit;
		Values value;

		switch (cardStr.charAt(1)) { // Determines the suit
		case 'H':
			suit = Suits.HEARTS;
			break;
		case 'D':
			suit = Suits.DIAMONDS;
			break;
		case 'C':
			suit = Suits.CLUBS;
			break;
		case 'S':
			suit = Suits.SPADES;
			break;
		default:
			throw new IllegalArgumentException("Invalid card suit");
		}

		char valueChar = cardStr.charAt(0); // Determine the value

		if (Character.isDigit(valueChar)) {
			int numericValue = Character.getNumericValue(valueChar); // Convert numbers to corresponding values
			value = Values.values()[numericValue - 2]; 
		} else {
			switch (valueChar) { // Do the same to Ten, Jack, Queen, King and Ace
			case 'T':
				value = Values.TEN;
				break;
			case 'J':
				value = Values.JACK;
				break;
			case 'Q':
				value = Values.QUEEN;
				break;
			case 'K':
				value = Values.KING;
				break;
			case 'A':
				value = Values.ACE;
				break;
			default:
				throw new IllegalArgumentException("Invalid card value");
			}
		}
		return new Card(suit, value);
	}

	public void playGame() {

		String filename = "src\\main\\resources\\data\\poker.txt";

		try {
			Scanner scanner = new Scanner(new File(filename));

			int player1Wins = 0;
			int player2Wins = 0;

			while (scanner.hasNextLine()) { // Process each line from poker.txt

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

				int comparisonResult = player1Hand.compareTo(player2Hand); // Compares two hands and determines the winning player (If a tie, then not accounted)

				if (comparisonResult > 0) {
					player1Wins++;
				} else if (comparisonResult < 0) {
					player2Wins++;
				}
			}

			System.out.println("Player 1 wins: " + player1Wins);
			System.out.println("Player 2 wins: " + player2Wins);

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
