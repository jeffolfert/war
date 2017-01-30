#War Game

War card game as described here https://en.wikipedia.org/wiki/War_(card_game)  
In this version of the game, a war round includes 2 cards from each player with 1 of the cards face up and one face down.
For games with more than 2 players, a war round occurs only when 2 or more players match the highest rank card.

Implemented using java (8) 1.8

To play the game instantiate a War class as shown in the example below.

```java
War war = new War();
int suits = 10;
int ranks = 10;
int players = 3;
war.play(suits,ranks, players);
```

As each round of play is executed status is printed to System.out

When the game is finished find out who won using   
war.getWinner()

And how many rounds of play it took to complete the game.  
war.getRoundCount()


A test in test/warTest.java is also include to drive the game.

#Build / Test / Demo  
The project uses maven to build using the default maven command.
From the top leve directory run  
mvn compile  (to compile)
mvn test (to test)

#Demo
For this project you can demo simply by running mvn test, which plays several types of games using various suites, ranks, and number of players.

