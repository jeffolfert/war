package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardDeck implements Deck {

    private List<Card> cards;

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        cards = new ArrayList<Card>();

        for(int suite=0; suite<numberOfSuits; suite++) {
            for(int rank=0; rank<numberOfRanks; rank++) {
                cards.add(new Card(rank, suite));
            }
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards, new Random(System.nanoTime()));
    }

    @Override
    public Card deal() {
        return cards.remove(0);
    }
}
