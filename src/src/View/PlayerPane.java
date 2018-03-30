package View;

import Model.Card;
import Model.Hand;
import Model.Player;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.binding.When;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.swing.*;
import java.sql.Time;
import java.util.EventListener;
import java.util.concurrent.TimeUnit;

public class PlayerPane extends VBox{
    private Label lblPlayerName = new Label();
    private HBox hboxCards = new HBox();
    public Label lblevaluateHand = new Label("--");
    public Label winorlose = new Label("--");
    private Player player;
    public pgView view;
    
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
    	//updatePlayerDisplay();
    }
    
    public void updatePlayerDisplay() {
    	   	for (int i = 0; i < Player.SizeH; i++) {
    		Card card = null;
    		if (player.getPlayerCards().size() > i) card = player.getPlayerCards().get(i);

    		CardLabelView c2 = new CardLabelView();
    		c2 = (CardLabelView) hboxCards.getChildren().get(i);
    		c2.setDeckCard();

    		    PathElement pe1 = new MoveTo(55.5, 1000);
    		    PathElement pe2 = new LineTo(55.5, 81);

                Path path = new Path();
                path.getElements().add(pe1);
                path.getElements().add(pe2);

                PathTransition move = new PathTransition(Duration.millis(2000), path, c2);
                //move.play();

                    RotateTransition rotator = new RotateTransition(Duration.millis(2000), c2);
                    rotator.setAxis(Rotate.Y_AXIS);
                    rotator.setFromAngle(0);
                    rotator.setToAngle(-90);
                    //rotator.play();

                    CardLabelView cl = new CardLabelView();
                    cl = (CardLabelView) hboxCards.getChildren().get(i);
                    cl.setCard(card);

                    RotateTransition rotator2 = new RotateTransition(Duration.millis(2000), c2);
                    rotator2.setAxis(Rotate.Y_AXIS);
                    rotator2.setFromAngle(90);
                    rotator2.setToAngle(0);
                    //rotator.play();

                    SequentialTransition s = new SequentialTransition(move, rotator, rotator2);
                    s.play();
                }

    		Hand evaluation = player.getHand();
    		if (evaluation != null) {
    			lblevaluateHand.setText(evaluation.toString());
    		} else {
    			lblevaluateHand.setText("--");
    		}
    	}


    public void updatePlayerDisplay2() {
        for (int i = 0; i < Player.SizeH; i++) {
            CardLabelView cl = (CardLabelView) hboxCards.getChildren().get(i);
            //cl.setCard(card);
            cl.setDeckCard();

            PathElement pe1 = new MoveTo(55.5, 81);
            PathElement pe2 = new LineTo(55.5,-1000);

            Path path = new Path();
            path.getElements().add(pe1);
            path.getElements().add(pe2);

            PathTransition move = new PathTransition(Duration.millis(6000), path, cl);
            move.play();
        }
    }

    public Player getPlayerPP(Player p) {
    	return player;
    }
}


