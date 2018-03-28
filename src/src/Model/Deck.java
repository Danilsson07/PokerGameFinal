package Model;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> allCards;
    private final SimpleIntegerProperty leftcards = new SimpleIntegerProperty();

    public Deck() {
    	this.allCards = new ArrayList<>();
        shuffle();
    }

    public int getCardsleft1() {
        return allCards.size();
    }
    
    public SimpleIntegerProperty getCardsRemainingProperty() {
        return leftcards;
    }
    public int getCardsLeft() {
    	return leftcards.get();
    }

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
