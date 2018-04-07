package Controller;

import Model.Card;
import Model.Deck;
import Model.Player;
import Model.pgModel;
import View.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.concurrent.TimeUnit;

public class pgController {
	public static int number;
	final private pgModel model;
	final private pgView view;
	
	 public pgController(pgModel model, pgView view) {
		 this.model = model;
		 this.view = view;



		 view.btnAsk.setOnAction((event) -> {
			 view.stage.close();
			 view.stage2.showAndWait();

		 });

		 //Action if player-button is pushed. Stage which asks how many player should join the game.
		 //Shows a Error message if the number is not between 2 and 9 or if the input isn't a integer
		 view.btnNum.setOnAction((event) -> {
			 if(event.getTarget()==view.btnNum) {
				 boolean numeric = true;
				 try {
				 number = Integer.parseInt(view.txtplayers.getText());
				 } catch (NumberFormatException e) {
					 numeric = false;
				 }
				 if(numeric) {
				 if (Integer.parseInt(view.txtplayers.getText()) <= 9 && Integer.parseInt(view.txtplayers.getText()) >= 2) {
				 view.stage2.close();
				 model.playername.clear();
				 model.players.clear();
				 model.getDeck().shuffle();
				 view.txtfields.clear();
				 view.panepopp2.setCenter(view.createTF(number));
				 view.stage3.show();
				 } else {
					 Alert alert = new Alert(AlertType.ERROR, "Input should be a number between 2 - 9");
			            alert.showAndWait();
				 }
				 } else {
					 Alert alert = new Alert(AlertType.ERROR, "Input should be a number between 2 - 9");
			            alert.showAndWait();
				 }
			 } else {
			 }
		 });

		 //Action which displays for each player a textfield to define the desired name
		 //Furthermore it creates the playerpanes
		 view.btnPlay.setOnAction((event) -> {
			 for (int i = 0; i < number; i++) {
				 model.playername.add(view.txtfields.get(i).getText());
			 }
			 model.addPlayers(number);
			 view.stage3.close();
			 view.stage.close();
			 view.root.setCenter(view.createPlayerPane(number));
			 view.stage.show();

		 });

		 // Action to shuffle the deck
		 // When cards are displayed it calls animation method to disappear the cards
		 // when no cards are dispalyed no animations will take place
		 view.btnShuf.setOnAction((event) -> {
			 for (int i = 0; i < number; i++) {
		    		Player p = model.getPlayer(i);
		    		p.discardHand();
	        		PlayerPane pp = view.getPlayerPane(i);
				 	// When cards are displayed it calls animation method to disappear the cards
				 	// when no cards are dispalyed no animations will take place
				 	if (model.getDeck().getCardsleft1()<52){
						pp.updatePlayerDisplay2();
					}
				 	pp.winorlose.setText("--");
				 	pp.lblevaluateHand.setText("--");
		    	}

		    	model.getDeck().shuffle();
		 });

		 //Deals 5 cards to each player with 2 animations
		 //If there are not enough cards it displays a error message
		 view.btnDeal.setOnAction((event) -> {
		    	int cardsRequired = number * Player.SizeH;
		    	Deck deck = model.getDeck();
		    	if (cardsRequired <= deck.getCardsleft1()) {
		        	for (int i = 0; i < number; i++) {
		        		Player p = model.getPlayer(i);
		        		p.discardHand();
		        		for (int j = 0; j < Player.SizeH; j++) {
		        			Card card = deck.dealCard();
		        			p.addPlayerCard(card);
		        		}
		        		p.getHand();
		        		PlayerPane pp = view.getPlayerPane(i);
		        		//Animation mehtod
		        		pp.updatePlayerDisplay();
		        		pp.lblevaluateHand.setText("--");
		        	}
		        // Error message if not enough cards are left for the deal
		    	} else {
		            Alert alert = new Alert(AlertType.ERROR, "There are not enough Cards left - Please shuffle first");
		            alert.showAndWait();
		    	}
		 });

		 //Action for the Button which shows the winner with a text and an animation.
		 //most important method is the compareto method which will be explained more in the specific class
		 view.btnSW.setOnAction((event) -> {
		 	PlayerPane test = view.getPlayerPane(0);
		 	if (test.lblevaluateHand.getText()!="--") {
				int index = 0;
				for (int i = index + 1; i < number; i++) {
					PlayerPane pp1 = view.getPlayerPane(index);
					PlayerPane pp2 = view.getPlayerPane(i);
					int result = model.getPlayer(index).compareTo(model.getPlayer(i));
					if (result == 1) {
						pp1.winorlose.setText("Winner!");
						pp2.winorlose.setText("Lost..");
					} else if (result == -1) {
						index = i;
						pp1.winorlose.setText("Lost..");
						pp2.winorlose.setText("Winner!");
					} else if (result == 10) {
						index = i;
						pp1.winorlose.setText("issaDraw!");
						pp2.winorlose.setText("issaDraw!");
					}
				}

				for (int i = 0; i < number; i++) {
					PlayerPane pp = view.getPlayerPane(i);
					if (pp.winorlose.getText() == "Winner!") {
						pp.WinnerAnimation();
					}
				}
			//If the dealing action is not finished or no cards are dealed an error message will be displayed
			} else {
				Alert alert = new Alert(AlertType.ERROR, "There is no winner yet");
				alert.showAndWait();
			}

         });

		 view.getStage().setOnCloseRequest((event) -> {
		 view.stop();
		 Platform.exit();
		 });
		 }
		}


