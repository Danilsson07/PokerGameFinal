package Model;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //Arraylist which stores all the cards for the Deck
    private final ArrayList<Card> allCards;
    public final SimpleIntegerProperty leftcards = new SimpleIntegerProperty();

    //Constructor which creates the Deck
    public Deck() {
    	this.allCards = new ArrayList<>();
        shuffle();
    }

    //method to get the cards left
    public int getCardsleft1() {
        return allCards.size();
    }

    //method whcih creates the deck and put all the cards into the arraylist and then it shuffles the arraylist
    public void shuffle() {
        allCards.clear();

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(rank, suit);
                allCards.add(card);
            }
        }

        Collections.shuffle(allCards);
    }

    //method to deal the card, afterwards it delete it from the array
    public Card dealCard() {
    	if (allCards.size() > 0) {
    		Card x = allCards.get(allCards.size()-1);
    		allCards.remove(allCards.size()-1);
    		return x;
    	} else {
    		return null;
    	}   
    }
}
