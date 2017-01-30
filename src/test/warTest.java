package test;

import main.War;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class warTest {

    @Test
    public void numberOfCardsPerPlayer() {
        War war = new War();

        assertEquals(1, war.determinCardCountPerPlayer(1, 2, 2));
        assertEquals(1, war.determinCardCountPerPlayer(1, 3, 2));
        assertEquals(2, war.determinCardCountPerPlayer(1, 4, 2));
        assertEquals(2, war.determinCardCountPerPlayer(1, 5, 2));
        assertEquals(3, war.determinCardCountPerPlayer(1, 6, 2));
        assertEquals(50, war.determinCardCountPerPlayer(10, 10, 2));

        assertEquals(0, war.determinCardCountPerPlayer(1, 2, 3));
        assertEquals(1, war.determinCardCountPerPlayer(1, 3, 3));
        assertEquals(1, war.determinCardCountPerPlayer(1, 4, 3));
        assertEquals(1, war.determinCardCountPerPlayer(1, 5, 3));
        assertEquals(2, war.determinCardCountPerPlayer(1, 6, 3));
        assertEquals(33, war.determinCardCountPerPlayer(10, 10, 3));
    }

    @Test
    public void playHighRanksLowSuits() {
        War war = new War();
        int suits = 1;
        int ranks = 60;
        int players = 5;
        war.play(suits,ranks, players);
        assertTrue(war.getRoundCount() > 0);
        assertTrue(war.getWinner().getId() <= players);
    }

    @Test
    public void playLowRanksHighSuits() {
        War war = new War();
        int suits = 60;
        int ranks = 2;
        int players = 5;
        war.play(suits,ranks, players);
        assertTrue(war.getRoundCount() > 0);
        assertTrue(war.getWinner().getId() <= players);
    }

    @Test
    public void playHighPlayers() {
        War war = new War();
        int suits = 200;
        int ranks = 2;
        int players = 100;
        war.play(suits,ranks, players);
        assertTrue(war.getRoundCount() > 0);
        assertTrue(war.getWinner().getId() <= players);
    }

}
