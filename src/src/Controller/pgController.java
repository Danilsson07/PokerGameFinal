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
			 if(event.getTarget()==view.btnNum) {
				 view.stage2.close();
			 } else {
				 view.stage2.showAndWait();
			 }
		 });
		 
		 view.btnNum.setOnAction((event) -> {
			 if(event.getTarget()==view.btnNum) {
				 boolean numeric = true;
				 try {
				 number = Integer.parseInt(view.txtplayers.getText());
				 } catch (NumberFormatException e) {
					 numeric = false;
				 }
				 if(numeric) {
				 if (Integer.parseInt(view.txtplayers.getText()) <= 10) {
				 view.stage2.close();
				 view.panepopp.setCenter(view.createTF(number));
				 view.stage2.showAndWait();
				 } else {
					 Alert alert = new Alert(AlertType.ERROR, "Input should be a number between 1 - 10");
			            alert.showAndWait();
				 }
				 } else {
					 Alert alert = new Alert(AlertType.ERROR, "Input should be a number between 1 - 10");
			            alert.showAndWait();
				 }
			 } else {
				 view.stage2.showAndWait();
			 }			 			 
		 });
		 
		 view.btnPlay.setOnAction((event) -> {
			 for (int i = 0; i < number; i++) {
				 model.playername.add(view.txtfields.get(i).getText());
			 }
			 model.addPlayers(number);
			 view.stage2.close();
			 //view.panepopp.setCenter(view.panepop);
			 view.stage.close();
			 view.root.setCenter(view.createPlayerPane(number));

			 view.stage.show();

		 });
		 
		 view.btnShuf.setOnAction((event) -> {
			 for (int i = 0; i < number; i++) {
		    		Player p = model.getPlayer(i);
		    		p.discardHand();
	        		PlayerPane pp = view.getPlayerPane(i);
	        		//pp.updatePlayerDisplay();
				 	pp.updatePlayerDisplay2();
				 	pp.winorlose.setText("--");
				 	pp.lblevaluateHand.setText("--");
		    	}

		    	model.getDeck().shuffle();
		 });
		 
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
		        		pp.updatePlayerDisplay();
		        	}
		    	} else {
		            Alert alert = new Alert(AlertType.ERROR, "There are not enough Cards left - Please shuffle first");
		            alert.showAndWait();
		    	}
		 });

		 view.btnSW.setOnAction((event) -> {
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

             for (int i = 0; i < number-1; i++) {
                 PlayerPane pp = view.getPlayerPane(i);
                 if (pp.winorlose.getText()=="Winner!") {
                     pp.WinnerAnimation();
                 } else {
                     pp.LoserAnimation();
                 }
             }

         });

		 view.getStage().setOnCloseRequest((event) -> {
		 view.stop();
		 Platform.exit();
		 });
		 }
		}


