package Model;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	//default final size of the amount of cards a player has
	public static final int SizeH = 5;
	private static int highestID = 0;
	private final int ID;
    private String playerName;
    //Array or the playercards
    private ArrayList<Card> PlayerCards;
    //temp array for the comapre to method of the first player
    private ArrayList<Card> temp1;
	//temp array for the comapre to method of the second player
    private ArrayList<Card> temp2;
    private Hand hand;

    //method to create aumaticly an ID
	  private static int getNextID() {
	    highestID++;
	    return highestID;
	  }

	//constructor to create the player with one parameter which is the name
    public Player(String playerName) {
		this.ID = getNextID();
        this.playerName = playerName;
        this.PlayerCards = new ArrayList<>();
        this.hand = null;
    }

    //method to get the ID
	public int getID() {
		return ID;
	}

	//Method to set the playername
    public void setPlayerName() {
    	if (!playerName.isEmpty()) this.playerName = playerName;
    }

    //method to get the playername
    public String getPlayerName() {
        return playerName;
    }

    //method to add the player's card to the specific array of the player
    public void addPlayerCard(Card card) {
        if (PlayerCards.size() < SizeH) {
        	PlayerCards.add(card);
        }
    }

    //Method to get the playercards of an specific player
    public ArrayList<Card> getPlayerCards() {
        return PlayerCards;
    }

    public int getNumPlayerCards() {
        return PlayerCards.size();
    }

    //method to delete the player's cards
    public void discardHand() {
    	PlayerCards.clear();
        hand = null;
    }

    //method to get the hand type of the player's cards
    public Hand getHand() {
        if (hand == null && PlayerCards.size() == SizeH) {
            hand = Hand.evaluateHand(PlayerCards);
        }
        return hand;
    }

    //Method which will be used for the compareto method to delete the hgighest card if it is the same as the card from the other player and get the next higher
    public int deleteGetNextHigher(Player p) {
		int x = 0;
		while (x == 0) {
		if (Hand.HighestCard(temp1) < Hand.HighestCard(temp2)) {
			x = -1;			
		} else if (Hand.HighestCard(temp1) > Hand.HighestCard(temp2)){
			x = 1;			
		} else if (Hand.HighestCard(temp1) == Hand.HighestCard(temp2) && temp1.size() > 0){			
			for (int i = 0; i < temp1.size(); i++) {
				if (Hand.HighestCard(temp1) == temp1.get(i).getRank().getValue()) {
					temp1.remove(i);				
			}
			}
			for (int j = 0; j < temp2.size(); j++) {
				if (Hand.HighestCard(temp2) == temp2.get(j).getRank().getValue()) {
					temp2.remove(j);
				}
			}
			
		} else {
			x = 10;
		}		
		}
		return x;
    }

	//Method which will be used for the compareto to get the highest pair
	//if its the same as the one from the other player it goes to the next highercard method
    public int GetHigherOnePairandNextHigher(Player p) {
		int x = 0;
		while (x == 0) {
		if (Hand.HighestOnePair(temp1) < Hand.HighestOnePair(temp2)) {
			x = -1;
		} else if (Hand.HighestOnePair(temp1) > Hand.HighestOnePair(temp2)){
			x = 1;
		} else if (Hand.HighestOnePair(temp1) == Hand.HighestOnePair(temp2)){
	        boolean found = false;
	        for (int i = 0; i < temp1.size() - 1 && !found; i++) {
	            for (int j = i+1; j < temp1.size() && !found; j++) {
	                if (temp1.get(i).getRank() == temp1.get(j).getRank() && temp1.get(i).getRank().getValue() == Hand.HighestOnePair(temp1)) {
	                	found = true;
	                	temp1.remove(j);
	                	temp1.remove(i);
	                }
	            }
	        }    				
	        boolean found2 = false;
	        for (int i = 0; i < temp2.size() - 1 && !found2; i++) {
	            for (int j = i+1; j < temp2.size() && !found2; j++) {
	                if (temp2.get(i).getRank() == temp2.get(j).getRank() && temp2.get(i).getRank().getValue() == Hand.HighestOnePair(temp2)) {
	                	found2 = true;
	                	temp2.remove(j);
	                	temp2.remove(i);
	                }
	            }
	        }
	        x = deleteGetNextHigher(p);    		        
		}
		}
		return x;
	}

	//Method which will be used for the compareto to get the highest three of a kind
	//if its the same as the one from the other player it goes to the next highercard method
    public int GetHigherThreeandNextHigher(Player p) {
		int x = 0;
		while (x == 0) {
		if (Hand.HigherThree(temp1) < Hand.HigherThree(temp2)) {
			x = -1;
		} else if (Hand.HigherThree(temp1) > Hand.HigherThree(temp2)){
			x = 1;
		} else if (Hand.HigherThree(temp1) == Hand.HigherThree(temp2)){
	        boolean found = false;
	        for (int i = 0; i < temp1.size() - 2 && !found; i++) {
	            for (int j = i+1; j < temp1.size() - 1 && !found; j++) {
	            	for (int k = j+1; k < temp1.size() && !found; k++) {
	            		if (temp1.get(i).getRank() == temp1.get(j).getRank() && temp1.get(j).getRank() == temp1.get(k).getRank() && temp1.get(i).getRank().getValue() == Hand.HigherThree(temp1)) {
	                	found = true;
	                	temp1.remove(k);
	                	temp1.remove(j);
	                	temp1.remove(i);
	            		}
	                }
	            }
	        }    				
	        boolean found2 = false;
	        for (int i = 0; i < temp2.size() - 2 && !found2; i++) {
	            for (int j = i+1; j < temp2.size() - 1 && !found2; j++) {
	            	for (int k = j+1; k < temp2.size() && !found2; k++) {
	            		if (temp2.get(i).getRank() == temp2.get(j).getRank() && temp2.get(j).getRank() == temp2.get(k).getRank() && temp2.get(i).getRank().getValue() == Hand.HigherThree(temp2)) {
	                	found2 = true;
	                	temp2.remove(k);
	                	temp2.remove(j);
	                	temp2.remove(i);
	            		}
	                }
	            }
	        }  
	        x = deleteGetNextHigher(p);    		        
		}
		}
		return x;
    }

    //compare to method which first check who has the higher hand
	//if the players has the same hand value it controls which have the next higher card or hand by calling the methods or use a own way
    @Override
    public int compareTo(Player p) {
    	int winner = 0;
    	//other players wins
    	if (this.getHand().getValue() < p.getHand().getValue()) {
    		winner = -1;
    	//this player wins
    	} else if (this.getHand().getValue() > p.getHand().getValue()) {
    		winner = 1;    	
    	//if they have the same handtype value
    	} else {
        	temp1 = (ArrayList<Card>) this.PlayerCards.clone();
        	temp2 = (ArrayList<Card>) p.PlayerCards.clone();
        	//if both have highestcard hand it calls the method to get check which card is higher when its same it proceeds to next highercard
    		if (this.getHand().getValue()==0) {
    			winner = deleteGetNextHigher(p);
    		}

			//if both have OnePair hand it calls the method to get the higher card when its the same it proceeds to next highercard
    		if (this.getHand().getValue()==1) {
    			winner = GetHigherOnePairandNextHigher(p);
    		}

			//if both have twopair hand it checks the first higher pairs if its the same it proceeds to next higher pair if they are the same it checks the next highercard
    		if (this.getHand().getValue()==2) {
    			int x = 0;
    			while (x == 0) {
    			if (Hand.HighestPairs(temp1) < Hand.HighestPairs(temp2)) {
    				x = -1;
    			} else if (Hand.HighestPairs(temp1) > Hand.HighestPairs(temp2)){
    				x = 1;
    			} else if (Hand.HighestPairs(temp1) == Hand.HighestPairs(temp2)){
    		        boolean found = false;
    		        for (int i = 0; i < temp1.size() - 1 && !found; i++) {
    		            for (int j = i+1; j < temp1.size() && !found; j++) {
    		                if (temp1.get(i).getRank() == temp1.get(j).getRank() && temp1.get(i).getRank().getValue() == Hand.HighestPairs(temp1)) {
    		                	found = true;
    		                	temp1.remove(j);
    		                	temp1.remove(i);
    		                }
    		            }
    		        }    				
    		        boolean found2 = false;
    		        for (int i = 0; i < temp2.size() - 1 && !found2; i++) {
    		            for (int j = i+1; j < temp2.size() && !found2; j++) {
    		                if (temp2.get(i).getRank() == temp2.get(j).getRank() && temp2.get(i).getRank().getValue() == Hand.HighestPairs(temp2)) {
    		                	found2 = true;
    		                	temp2.remove(j);
    		                	temp2.remove(i);
    		                }
    		            }
    		        }
    		        x = GetHigherOnePairandNextHigher(p);    		        
    			}
    			}
    			winner = x;
    		}

			//if both have the same hand it calls the method to check which card is higher when its same it proceeds to next highercard
    		if (this.getHand().getValue()==3) {
    			winner =  GetHigherThreeandNextHigher(p);
    		}

			//if both have the same hand it calls the method to check which card is higher when its same it proceeds to next highercard
    		if (this.getHand().getValue()==4) {
    			winner = deleteGetNextHigher(p);
    		}

			//if both have highestcard hand it calls the method to check which card is higher when its same it proceeds to next highercard
    		if (this.getHand().getValue()==5) {
    			winner = deleteGetNextHigher(p);
    		}

			//if both have highestcard hand it calls the method to check which card is higher when its same it proceeds to next highercard
    		if (this.getHand().getValue()==6) {
    			winner = deleteGetNextHigher(p);
    		}

			//if both have full house hand it checks the first higher three of a kind if its the same it proceeds to next higher pair
    		if (this.getHand().getValue()==7) {
    			int x = 0;
    			while (x == 0) {
    			if (Hand.HigherFour(temp1) < Hand.HigherFour(temp2)) {
    				x = -1;
    			} else if (Hand.HigherFour(temp1) > Hand.HigherFour(temp2)){
    				x = 1;
    			} else if (Hand.HigherFour(temp1) == Hand.HigherFour(temp2)){
    		    	boolean found = false;
    		    	int num = 0;
    		        for (int i = 0; i < temp1.size() - 3 && !found; i++) {
    		            for (int j = i+1; j < temp1.size() - 2 && !found; j++) {
    		            	for (int k = j+1; k < temp1.size() - 1 && !found; k++) {
    		            		for (int l = k+1; l < temp1.size() && !found; l++) {
    		            			if (temp1.get(i).getRank() == temp1.get(j).getRank() && temp1.get(j).getRank() == temp1.get(k).getRank() && temp1.get(k).getRank() == temp1.get(l).getRank() && temp1.get(i).getRank().getValue() == Hand.HigherFour(temp1)) {
    		                	found = true;
    		                	temp1.remove(l);
    		                	temp1.remove(k);
    		                	temp1.remove(j);
    		                	temp1.remove(i);
    		            			}
    		            		}
    		                }
    		            }
    		        }    				
    		        boolean found2 = false;
    		        for (int i = 0; i < temp2.size() - 3 && !found; i++) {
    		            for (int j = i+1; j < temp2.size() - 2 && !found; j++) {
    		            	for (int k = j+1; k < temp2.size() - 1 && !found; k++) {
    		            		for (int l = k+1; l < temp2.size() && !found; l++) {
    		            			if (temp2.get(i).getRank() == temp2.get(j).getRank() && temp2.get(j).getRank() == temp2.get(k).getRank() && temp2.get(k).getRank() == temp2.get(l).getRank() && temp2.get(i).getRank().getValue() == Hand.HigherFour(temp2)) {
    		                	found = true;
    		                	temp2.remove(l);
    		                	temp2.remove(k);
    		                	temp2.remove(j);
    		                	temp2.remove(i);
    		            			}
    		            		}
    		                }
    		            }
    		        }   
    		        x = deleteGetNextHigher(p);    		        
    			}
    			}
    			winner = x;
    		} 
    		
    		if (this.getHand().getValue()==8) {
    			winner = deleteGetNextHigher(p);
    		}         
    }
    	return winner;
}
    
}
