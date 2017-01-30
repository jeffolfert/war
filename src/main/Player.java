package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {

    private List<Card> cards;
    private int id;

    public Player(int id) {
        cards = new ArrayList<>();
        this.id = id;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(Collection<Card> newCards) {
        cards.addAll(newCards);
    }

    public Card getTopCard() {
        Card topCard = cards.remove(0);
        return topCard;
    }

    public int getCardCount() {
        return cards.size();
    }

    public int getId() {
        return id;
    }

}
