package Model;


public class Card {
	
	public enum Rank {
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(11),
		QUEEN(12),
		KING(13),
		ACE(14);
		 
		  private int Value;
		 
		  private Rank (int value)
		  {
		    this.Value = value;
		  }
		 
		  public int getValue() {
		    return Value;
		  }
	};
	
	public enum Suit {
		  HEARTS,
		  SPADES,
		  CLUBS,
		  DIAMONDS;
	}	
	
	private Suit suit;
	private Rank rank;
	 
	public Card (Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	 
	public Suit getSuit() {
		return suit;
	}
	 
	  public void setSuit(Suit suit) {
		  this.suit = suit;
	  }
	 
	  public Rank getRank() {
		  return rank;
	  }
	 
	  public void setCardValue(Rank rank) {
		  this.rank = rank;
	  }
	  
	    public String RanktoString() {
	        return rank.toString();
	    }
	    
	    public String SuittoString() {
	        return suit.toString();
	    }
	}