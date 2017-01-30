War card game as described here https://en.wikipedia.org/wiki/War_(card_game)
Code is implemented in java (8) 1.8

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

