package Model;

import Controller.pgController;
import View.pgView;

import java.util.ArrayList;

public class pgModel {
	private pgView view;
	private pgController controller;
    public ArrayList<String> playername = new ArrayList<>();

	//ArrayList which stores the players
	public ArrayList<Player> players;
	private Deck deck;

	//constructor for the model
	public pgModel() {
		players = new ArrayList<>();
		addPlayers(controller.number);
		deck = new Deck();
	}

	//method to add the players according to the number of the players
	//takes the desired name from the textfields and set is as palyername
	public void addPlayers(int number) {
		for (int i = 0; i < number; i++) {
			//add and create new player according to the desired name
			players.add(new Player(playername.get(i)));
		}		
	}

	//method to get the player
	public Player getPlayer(int i) {
		return players.get(i);
	}

	//method to get the deck
	public Deck getDeck() {
		return deck;
	}
}
