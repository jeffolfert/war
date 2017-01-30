package main;

public class Card {

    private int rank;
    private int suite;
    private boolean isFaceDown;

    public Card(int rank, int suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public int getRank() {
        return rank;
    }

    public int getSuite() {
        return suite;
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }

    public void setFaceDown() {
        this.isFaceDown = true;
    }

    public void setFaceUp() {
        this.isFaceDown = false;
    }

    @Override
    public String toString() {
        return "Rank= " + rank + " Suite= " + suite + " Facedown=" + isFaceDown;
    }
}
