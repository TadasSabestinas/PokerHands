# Poker Hands solution // Project Euler

a Java solution for comparing poker hands, determining which player wins the hand in a 1 vs 1 poker game scenario. 

## How the Solution Works

Looking from a high level, the solution is structured around three classes: `Card`, `PlayerHand`, and `PokerMatchRunner`.

- **Card Class**: Represents a single poker card with a suit and a value (for example: "Ace of Diamonds" ).

- **PlayerHand Class**: Represents a player's hand of cards. It includes methods for adding cards, evaluating the hand's rank (for example: pair, flush, straight) and comparing with another hand to determine the winner.

- **PlayPokerHand Class**: Contains the game logic including parsing card strings from a file, creating `PlayerHand` objects and determining the outcome of games.

The comparison logic is implemented in the `PlayerHand` class which uses the `Comparable` interface to compare two hands based on poker rules. This includes handling special cases like Ace-low straights and comparing hands with the same rank using kicker cards.

### What I Like About the Solution

- **Modularity**: The solution is divided into distinct classes with clear responsibilities, making the code organized and easier to maintain.
- **Unit Testing**: Tests cover various scenarios, including edge cases, ensuring the solution works as expected and making it robust against future changes.

### What I Do Not Like About the Solution

- **Special Case Handling**: The logic for some hand comparisons, especially handling Ace-low straights and kicker comparisons, adds complexity to the `compareTo` method in the `PlayerHand` class. This could potentially be simplified or abstracted further.

## Reflections on New Technologies and Approaches

Throughout the development of this solution, I learned and applied several programming concepts, some of which were new to me:

- **Implementing the Comparable Interface**: This was my first time using Java's `Comparable` interface in a an application. Learning how to define the `compareTo` method to enforce natural ordering of custom objects (in this case, poker hands).

- **Unit Testing with JUnit**: Although I was familiar with the concept of unit testing and have worked with JUnit framework writing automation tests for a university course, this project allowed me to dive deeper into writing comprehensive test cases using JUnit. Developing tests for edge cases and understanding the importance of test coverage for maintaining code quality were significant learning aspects.
