package View;

import Model.Card;
import Model.Hand;
import Model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PlayerPane extends VBox{
    private Label lblPlayerName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblevaluateHand = new Label("--");
    public Label winorlose = new Label("--");
    private Player player;
    
    public PlayerPane() {
    	super();
        this.getStyleClass().add("player"); // CSS style class

        this.getChildren().addAll(lblPlayerName, hboxCards, lblevaluateHand, winorlose);
        
        VBox.setVgrow(hboxCards, Priority.ALWAYS); // On resize, expand the card area
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabelView();
            HBox.setHgrow(lblCard, Priority.ALWAYS); //On resize, expand the cards
            hboxCards.getChildren().add(lblCard);
        }
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	lblPlayerName.setText(player.getPlayerName());
    	updatePlayerDisplay();    	
    }
    
    public void updatePlayerDisplay() {
    	   	for (int i = 0; i < Player.SizeH; i++) {
    		Card card = null;
    		if (player.getPlayerCards().size() > i) card = player.getPlayerCards().get(i);
    		CardLabelView cl = (CardLabelView) hboxCards.getChildren().get(i);
    		cl.setCard(card);
    		Hand evaluation = player.getHand();
    		if (evaluation != null) {
    			lblevaluateHand.setText(evaluation.toString());
    		} else {
    			lblevaluateHand.setText("--");
    		}    		
    	}
    }

    public Player getPlayerPP(Player p) {
    	return player;
    }
}


