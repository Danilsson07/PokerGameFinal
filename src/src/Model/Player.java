package Model;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	public static final int SizeH = 5;
	private static int highestID = 0;
	private final int ID;
    private String playerName;
    private ArrayList<Card> PlayerCards;
    private ArrayList<Card> temp1;
    private ArrayList<Card> temp2;
    private Hand hand;
    
	  private static int getNextID() {
	    highestID++;
	    return highestID;
	  }
    
    public Player(String playerName) {
		this.ID = getNextID();
        this.playerName = playerName;
        this.PlayerCards = new ArrayList<>();
        this.hand = null;
    }
    
	public int getID() {
		return ID;
	}
    
    public void setPlayerName() {
    	if (!playerName.isEmpty()) this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    public void addPlayerCard(Card card) {
        if (PlayerCards.size() < SizeH) {
        	PlayerCards.add(card);
        }
    }

    public ArrayList<Card> getPlayerCards() {
        return PlayerCards;
    }
    
    public int getNumPlayerCards() {
        return PlayerCards.size();
    }

    public void discardHand() {
    	PlayerCards.clear();
        hand = null;
    }

    public Hand getHand() {
        if (hand == null && PlayerCards.size() == SizeH) {
            hand = Hand.evaluateHand(PlayerCards);
        }
        return hand;
    }
    
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

    @Override
    public int compareTo(Player p) {
    	int winner = 0;
    	if (this.getHand().getValue() < p.getHand().getValue()) {
    		winner = -1;    		
    	} else if (this.getHand().getValue() > p.getHand().getValue()) {
    		winner = 1;    	
    	
    	} else {
        	temp1 = (ArrayList<Card>) this.PlayerCards.clone();
        	temp2 = (ArrayList<Card>) p.PlayerCards.clone();
    		if (this.getHand().getValue()==0) {
    			winner = deleteGetNextHigher(p);
    		}
    		
    		if (this.getHand().getValue()==1) {
    			winner = GetHigherOnePairandNextHigher(p);
    		}

    		
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
    		
    		if (this.getHand().getValue()==3) {
    			winner =  GetHigherThreeandNextHigher(p);
    		} 
    		
    		if (this.getHand().getValue()==4) {
    			winner = deleteGetNextHigher(p);
    		}
    		
    		if (this.getHand().getValue()==5) {
    			winner = deleteGetNextHigher(p);
    		}
    		
    		if (this.getHand().getValue()==6) {
    			winner = deleteGetNextHigher(p);
    		} 
    		
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
