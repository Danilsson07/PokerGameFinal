package Model;

import Controller.pgController;
import View.pgView;

import java.util.ArrayList;

public class pgModel {
	private pgView view;
	private pgController controller;
    public ArrayList<String> playername = new ArrayList<>();
	
	protected ArrayList<Player> players;
	private Deck deck;
	
	public pgModel() {
		players = new ArrayList<>();
		addPlayers(controller.number);
		deck = new Deck();
	}
	
	public void addPlayers(int number) {
		for (int i = 0; i < number; i++) {
			players.add(new Player(playername.get(i)));
		}		
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public Deck getDeck() {
		return deck;
	}
}
