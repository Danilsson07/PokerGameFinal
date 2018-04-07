package Model;


public class Card {

	//All the Ranks as Enum with a Value
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

		  //method to get the Rank of the card
		  private Rank (int value)
		  {
		    this.Value = value;
		  }

		  //method to get the value of the rank (for evaluation stuff
		  public int getValue() {
		    return Value;
		  }
	};

	//All the Suits as Enum
	public enum Suit {
		  HEARTS,
		  SPADES,
		  CLUBS,
		  DIAMONDS;
	}	

	private Suit suit;
	private Rank rank;

	//Constructor with 2 parameters to create the card
	public Card (Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	//method to get the suit
	public Suit getSuit() {
		return suit;
	}

	//method to get the suit
	public void setSuit(Suit suit) {
		  this.suit = suit;
	  }

	//method to get the rank
	public Rank getRank() {
		  return rank;
	  }

	//method to set the card value
	public void setCardValue(Rank rank) {
		  this.rank = rank;
	  }

	//tostring mehtod to return the rank as an string
	public String RanktoString() {
	        return rank.toString();
	    }

	//tostring mehtod to return the suit as an string
	public String SuittoString() {
	        return suit.toString();
	    }
	}