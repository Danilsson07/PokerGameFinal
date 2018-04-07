package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Hands as Enums with a value for the evaluation
public enum Hand {
    HighCard(0), 
    OnePair(1), 
    TwoPair(2), 
    ThreeOfAKind(3), 
    Straight(4), 
    Flush(5), 
    FullHouse(6), 
    FourOfAKind(7), 
    StraightFlush(8);

    private int value;
    private static Map map = new HashMap<>();

    //private constructor to set the hand
    private Hand(int value) {
        this.value = value;
    }

    static {
        for (Hand hand : Hand.values()) {
            map.put(hand.value, hand);
        }
    }

    public static Hand valueOf(int x) {
        return (Hand) map.get(x);
    }

    //method to get the value
    public int getValue() {
        return value;
    }

    //method to evaluate the hand due to if statements which are calling all the methods
    //stores the value in an int wich will be returned
    //default is the highestcards
    public static Hand evaluateHand(ArrayList<Card> cards) {
    	Hand currentEval = HighCard;
    	int x = 0;
        
        if (isOnePair(cards) == true) x = Hand.OnePair.getValue();
        if (isTwoPair(cards) == true) x = Hand.TwoPair.getValue();
        if (isThreeOfAKind(cards) == true) x = Hand.ThreeOfAKind.getValue();
        if (isStraight(cards) == true) x = Hand.Straight.getValue();
        if (isFlush(cards) == true) x = Hand.Flush.getValue();
        if (isFullHouse(cards) == true) x = Hand.FullHouse.getValue();
        if (isFourOfAKind(cards) == true) x = Hand.FourOfAKind.getValue();
        if (isStraightFlush(cards) == true) x = Hand.StraightFlush.getValue();
            
        currentEval = Hand.valueOf(x);        		
                     
        return currentEval;
    }

    //method to check if the hand is a Pair
    private static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }

    //method to check if the hand is a TwoPair
    private static boolean isTwoPair(ArrayList<Card> cards) {
    	boolean found = false;
        // Clone the cards into an new array to proceed with the temp array
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair
        //if found --> remove the cards from the array
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        
        boolean onepair = isOnePair(clonedCards);
        
        if (firstPairFound == true && onepair == true) {
        	found = true;
        } else {
        	found = false;
        }        

        return found;
    }

    //method to check if the hand is ThreeofaKind
    private static boolean isThreeOfAKind(ArrayList<Card> cards) {
    	boolean found = false;
        for (int i = 0; i < cards.size() - 2 && !found; i++) {
            for (int j = i+1; j < cards.size() - 1 && !found; j++) {
            	for (int k = j+1; k < cards.size() && !found; k++) {
            		if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank()) {
            			found = true;
            		}            			
            	}                
            }
        }
        return found;
    }

    //method to check if the hand is straight
    private static boolean isStraight(ArrayList<Card> cards) {
    	boolean found = true;
    	ArrayList<Integer> temp = new ArrayList<>();
    	for (Card c : cards) {
    		int num = c.getRank().getValue();
    		temp.add(num);    		
    	}
    	//using collection to sort the array
    	Collections.sort(temp);

    	//loop to check if each further index is one higher -->street
    	for (int i = 0; i < temp.size()-1 && found == true; i++) {
    		
    		if (temp.get(i) == temp.get(i+1)-1) {
    			found = true;
    		} else {
    			found = false;
    		}
    	}
    	
        return found;
    }

    //method to check if the hand is a flush
    private static boolean isFlush(ArrayList<Card> cards) {
    	boolean found = true;
    	for (int i = 0; i < cards.size() - 1 && found == true; i++) {
    		if (cards.get(i) == cards.get(i+1)) {
    			found = true;
    		} else {
    			found = false;
    		}
    	}    	
    	
        return found;
    }

    //same as the one pair method, but afterwards it calls the threeofthekind method
    private static boolean isFullHouse(ArrayList<Card> cards) {
    	boolean found = false;
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        
        boolean threeofakind = isThreeOfAKind(clonedCards);

        //Checks if both is true the pair and the three of a Kind
        if (firstPairFound == true && threeofakind == true) {
        	found = true;
        } else {
        	found = false;
        }        

        return found;
    }

    //method to check if the hand is a four of a kind
    private static boolean isFourOfAKind(ArrayList<Card> cards) {
    	boolean found = false;
        for (int i = 0; i < cards.size() - 3 && !found; i++) {
            for (int j = i+1; j < cards.size() - 2 && !found; j++) {
            	for (int k = j+1; k < cards.size() - 1 && !found; k++) {
            		for (int l = k+1; l < cards.size() && !found; l++) {
            			if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank() && cards.get(k).getRank() == cards.get(l).getRank()) {
            				found = true;            				
            			}
            		}            			
            	}                
            }
        }
        return found;
    }

    //method to check if the hand is a straightflush by calling the 2 methods(isstraight and flush)
    //if both is true it will return it
    private static boolean isStraightFlush(ArrayList<Card> cards) {
        boolean found = false;
        boolean straight = isStraight(cards);
        boolean flush = isFlush(cards);
        
        if (straight == true && flush == true) {
        	found = true;
        } else {
        	found = false;
        }
        
        return found;
    }

    //Method which will be used afterwards at the compareto method
    //return the highest card if the player has the hand hgighestcard
    public static int HighestCard(ArrayList<Card> cards) {
    	int found = 0;
    	ArrayList<Integer> temp = new ArrayList<>();
    	for (Card c : cards) {
    		int num = c.getRank().getValue();
    		temp.add(num);    		
    	}
    	Collections.sort(temp); //sorts the array
    	
    	found = temp.get(temp.size()-1);

        return found;
    }

    //Method which will be used afterwards at the compareto method
    //return the highest card of the pair if the player has the hand onePair
    public static int HighestOnePair(ArrayList<Card> cards) {
        boolean found = false;
        int num = 0;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                	found = true;
                	num = cards.get(i).getRank().getValue();                	
                }
            }
        }
        return num;
    }

    //Method which will be used afterwards at the compareto method
    //return the highest pair if the player has the hand TwoPair
    //using the highestpair method
    public static int HighestPairs(ArrayList<Card> cards) {
    	int firstPair = 0;
        // Clone the cards
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    firstPair = cards.get(i).getRank().getValue();
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        
        int secondPair = HighestOnePair(clonedCards);
        if (firstPair > secondPair) {
        	return firstPair;
        } else {
        	return secondPair;
        }      
    }

    //Method which will be used afterwards at the compareto method
    //return the highest card if the player has the hand three of a kind
    public static int HigherThree(ArrayList<Card> cards) {
    	boolean found = false;
    	int num = 0;
        for (int i = 0; i < cards.size() - 2 && !found; i++) {
            for (int j = i+1; j < cards.size() - 1 && !found; j++) {
            	for (int k = j+1; k < cards.size() && !found; k++) {
            		if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank()) {
            			num = cards.get(i).getRank().getValue();
            			found = true;
            		}            			
            	}                
            }
        }
        return num;
    }

    //Method which will be used afterwards at the compareto method
    //return the highest card if the player has the hand four of a kind
    public static int HigherFour(ArrayList<Card> cards) {
    	boolean found = false;
    	int num = 0;
        for (int i = 0; i < cards.size() - 3 && !found; i++) {
            for (int j = i+1; j < cards.size() - 2 && !found; j++) {
            	for (int k = j+1; k < cards.size() - 1 && !found; k++) {
            		for (int l = k+1; l < cards.size() && !found; l++) {
            			if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank() && cards.get(k).getRank() == cards.get(l).getRank()) {
            				num = cards.get(i).getRank().getValue();
            				found = true;            				
            			}
            		}            			
            	}                
            }
        }
        return num;
    }
    
}
