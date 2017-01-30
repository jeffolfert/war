package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class War {

    private Deck deck;
    private List<Player> players;
    private int roundCount;

    public int getRoundCount() {
        return roundCount;
    }

    public Player getWinner() {
        return this.players.get(0);
    }

    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {

        System.out.println(String.format("Play suits=%s, ranks=%s, players=%s", numberOfSuits, numberOfRanks, numberOfPlayers));

        if(numberOfSuits < 0)
            throw new IllegalArgumentException("Number of suites must be greater than 0");

        if(numberOfRanks < 0)
            throw new IllegalArgumentException("Number of ranks must be greater than 0");

        if(numberOfPlayers < 2)
            throw new IllegalArgumentException("Number of players must be 2 or more");

        int cardCount = numberOfRanks*numberOfSuits;

        if(cardCount < numberOfPlayers)
            throw new IllegalArgumentException("total number of cards is less than then number of players");

        deck = new CardDeck();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        //Create the players
        this.players = new ArrayList<>();
        HashMap<Player, List<Card>> currentRound = new HashMap<>();
        for(int i=0; i<numberOfPlayers ;i++) {
            Player player = new Player(i);
            players.add(player);
            currentRound.put(player, new ArrayList<>());
        }

        //Deal the deck
        int cardsPerPlayer = determinCardCountPerPlayer(numberOfSuits, numberOfRanks, numberOfPlayers);
        for(int i=0; i<cardsPerPlayer; i++) {
            for(Player player: players) {
                player.addCard(deck.deal());
            }
        }

        play(currentRound);
    }

    private void play(HashMap<Player, List<Card>> currentRound) {
        boolean winnerFound = false;
        this.roundCount = 1;
        while(!winnerFound) {
            System.out.println("round " + this.roundCount);
            winnerFound = playRound(false, currentRound);
            this.roundCount++;
        }
        System.out.println(String.format("Player %s wins", this.players.get(0).getId()));
    }

    private boolean playRound(boolean isWarRound, HashMap<Player,List<Card>> currentRound) {
        if(isWarRound)
            System.out.println("!!!!!!!!!! WAR !!!!!!!!!!!!!!");

        int maxRank = -1;

        List<Player> losers = new ArrayList<>();
        for(int i=0;i<players.size(); i++) {
            Player player = this.players.get(i);
            System.out.println(String.format("player %s total cards %s", player.getId(), player.getCardCount()));

            if(isWarRound) {
                if(player.getCardCount() >= 1) {
                    Card faceDownCard = player.getTopCard();
                    faceDownCard.setFaceDown();
                    currentRound.get(player).add(faceDownCard);
                }
                else {
                    losers.add(player);
                }
            }

            if(player.getCardCount() >= 1) {
                Card faceUpCard = player.getTopCard();
                currentRound.get(player).add(faceUpCard);
                if(faceUpCard.getRank() >= maxRank) {
                    maxRank = faceUpCard.getRank();
                }
            }
            else {
                losers.add(player);
            }
        }

        losers.stream().forEach(this.players::remove);

        //edge case there is already a winner so don't check any further
        if(this.players.size() <= 1) {
            if(this.players.size() == 0) {
                //sometimes everyone loses, but one player loses last - so I choose them as the winner
                this.players.add(losers.get(losers.size()-1));
            }
            return true;
        }

        //find out if there is war (more than one rank of highest value)
        int maxCount = 0;
        boolean foundWar = false;
        Player playerWithHighestRank = this.players.get(0);
        for(Player player: players) {
            List<Card> cardsForPlayer = currentRound.get(player);
            Card lastCard = cardsForPlayer.get(cardsForPlayer.size() - 1);

            if (lastCard.getRank() == maxRank) {
                maxCount = maxCount+1;
                playerWithHighestRank = player;
            }

            if(maxCount > 1) {
                foundWar = true;
                break;
            }
        }


        if(foundWar) {
            playRound(true, currentRound);
        }
        else {
            //A player won the battle so take all the cards in play and place them in that Players deck
            List<Card> cardsToAdd = currentRound.values().stream().flatMap(List::stream).collect(Collectors.toList());

            cardsToAdd.stream()
                    .forEach(Card::setFaceUp);

            //Without a shuffle here there is too high probability of infinite cycles
            Collections.shuffle(cardsToAdd);

            playerWithHighestRank.addCards(cardsToAdd);
            currentRound.replaceAll((p,c)->new ArrayList<>());
        }

        return players.size() == 1;
    }


    public int determinCardCountPerPlayer(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        int cardCount = numberOfRanks*numberOfSuits;

        if(cardCount % numberOfPlayers == 0)
            return cardCount / numberOfPlayers;

        return (cardCount - (cardCount % numberOfPlayers)) / numberOfPlayers;
    }
}
