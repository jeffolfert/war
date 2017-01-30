package main;

public interface Deck {
    void create(int numberOfSuits, int numberOfRanks);
    void shuffle();
    Card deal();
}
