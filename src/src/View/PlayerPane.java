package View;

import Model.Card;
import Model.Hand;
import Model.Player;
import Controller.pgController;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
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

public class PlayerPane extends VBox{
    private Label lblPlayerName = new Label();
    private HBox hboxCards = new HBox();
    public Label lblevaluateHand = new Label("--");
    public Label winorlose = new Label("--");
    private Player player;
    public pgView view;

    //constructor - no parameters
    public PlayerPane() {
    	super();
        this.getStyleClass().add("player"); // CSS style class

        this.getChildren().addAll(lblPlayerName, hboxCards, lblevaluateHand, winorlose);
        
        VBox.setVgrow(hboxCards, Priority.ALWAYS); //resizing
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabelView();
            HBox.setHgrow(lblCard, Priority.ALWAYS); //resizing
            hboxCards.getChildren().add(lblCard);
        }
    }

    //method to set the player
    public void setPlayer(Player player) {
    	this.player = player;
    	lblPlayerName.setText(player.getPlayerName());
    	//updatePlayerDisplay();
    }

    //method to update the display
    //method will be called in the controller class for the action of the deal button
    //place the cards for each player hbox
    public void updatePlayerDisplay() {
    	   	for (int i = 0; i < Player.SizeH; i++) {
    	   	winorlose.setText("--");
    		Card card = null;
    		if (player.getPlayerCards().size() > i) card = player.getPlayerCards().get(i);

    		CardLabelView cl = new CardLabelView();
    		cl = (CardLabelView) hboxCards.getChildren().get(i);
    		cl.setDeckCard(); //set the deck cards

    		//moves the cards from the buttom to the specific card places in the hbox of each player
    		    PathElement pe1 = new MoveTo(55.5, 1000);
    		    PathElement pe2 = new LineTo(55.5, 81);

                Path path = new Path();
                path.getElements().add(pe1);
                path.getElements().add(pe2);

                PathTransition move = new PathTransition(Duration.millis(2000), path, cl);

                //rotates the card to 90°
                    RotateTransition rotator = new RotateTransition(Duration.millis(1500), cl);
                    rotator.setAxis(Rotate.Y_AXIS);
                    rotator.setFromAngle(0);
                    rotator.setToAngle(-90);

                    //plays the animation one for one
                    SequentialTransition s = new SequentialTransition(move, rotator);
                    s.play();


                    //set the front of the card and rotate the final 90°
                CardLabelView finalCl = cl;
                Card finalCard = card;
                rotator.setOnFinished(e -> {
                        RotateTransition rotator2 = rotateback(finalCl, finalCard);
                        rotator2.play();
                        rotator2.setOnFinished((ActionEvent ee) -> {
                            Hand evaluation = player.getHand();
                            if (evaluation != null) {
                                lblevaluateHand.setText(evaluation.toString());
                            } else {
                                lblevaluateHand.setText("--");
                            }
                        });
                    });
                }
    	}

    //set the front of the card and rotate the final 90°
    	private RotateTransition rotateback (CardLabelView cl, Card card){
            cl.setCard(card);
            RotateTransition rotator2 = new RotateTransition(Duration.millis(1500), cl);
            rotator2.setAxis(Rotate.Y_AXIS);
            rotator2.setFromAngle(90);
            rotator2.setToAngle(0);
            rotator2.play();
            return rotator2;
        }

    //set the back of the card and rotate the final 90°
    private RotateTransition rotateback2 (CardLabelView cl) {
        cl.setDeckCard();
        RotateTransition rotator2 = new RotateTransition(Duration.millis(1500), cl);
        rotator2.setAxis(Rotate.Y_AXIS);
        rotator2.setFromAngle(90);
        rotator2.setToAngle(0);
        rotator2.play();
        return rotator2;
    }

    //method to update the display
    //method will be called in the controller class for the action of the shuffle button
    public void updatePlayerDisplay2() {
        for (int i = 0; i < Player.SizeH; i++) {
            CardLabelView cl = (CardLabelView) hboxCards.getChildren().get(i);

            //let the cards disappear to the top
            PathElement pe1 = new MoveTo(55.5, 81);
            PathElement pe2 = new LineTo(55.5,-1000);

            Path path = new Path();
            path.getElements().add(pe1);
            path.getElements().add(pe2);

            PathTransition move = new PathTransition(Duration.millis(3000), path, cl);

            //rotate the card 90°
            RotateTransition rotator = new RotateTransition(Duration.millis(1500), cl);
            rotator.setAxis(Rotate.Y_AXIS);
            rotator.setFromAngle(0);
            rotator.setToAngle(-90);
            rotator.play();

            //set the back of the card and rotate the final 90°
            CardLabelView finalCl = cl;
            rotator.setOnFinished(e -> {
                RotateTransition rotator2 = rotateback2(finalCl);
                SequentialTransition s = new SequentialTransition(rotator2, move);
                s.play();

            });
        }
    }

    //method to update the display
    //method will be called in the controller class for the action of the show winner button
    //rotate the winner cards a long the y axis for 720°
    public void WinnerAnimation() {
        for (int i = 0; i < Player.SizeH; i++) {
            CardLabelView c2 = (CardLabelView) hboxCards.getChildren().get(i);
            RotateTransition rt = new RotateTransition(Duration.millis(2000), c2);
            rt.setAxis(Rotate.X_AXIS);
            rt.setByAngle(720);
            rt.play();
        }
    }

    public Player getPlayerPP(Player p) {
    	return player;
    }
}


