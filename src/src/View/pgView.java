package View;

import Model.pgModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class pgView {

	private pgModel model;
	public Stage stage;
	public Stage stage2;
	protected Scene scene1;
	protected Scene scene2;
	public Button btnAsk;
	public Button btnNum;
	public Button btnPlay;
	public Button btnShuf = new Button("Shuffle");
	public Button btnDeal = new Button("Deal");
	protected Button btnQuit = new Button("Quit");
	protected Label label1, label2;
	public TextField txtplayers;
	protected BorderPane pane1;
	public BorderPane panepopp;
	protected GridPane panepop;
	protected GridPane players;
	protected HBox menuBox;
	protected HBox playerBox;
	protected HBox controlBox;
	public BorderPane root;
    private Label lblDeck = new Label("");
    Region spacer = new Region(); 
    
    public ArrayList<TextField> txtfields = new ArrayList<>();
	protected ArrayList<Label> labels = new ArrayList<>();
	protected ArrayList<PlayerPane> pps = new ArrayList<>();
		 
	public pgView(Stage stage, pgModel model) {
	this.stage = stage;
	this.model = model;
	
	btnAsk = new Button("Players");
	menuBox = new HBox(btnAsk);
    playerBox = new HBox();
    controlBox = new HBox(5, lblDeck, spacer, btnShuf, btnDeal, btnQuit);
    
      
    root = new BorderPane();
    root.setTop(menuBox);
    //root.setCenter(playerBox);
    root.setBottom(controlBox);

    panepopp = new BorderPane();
	panepop = new GridPane();
	label2 = new Label("How many Players joint the game??");
	txtplayers = new TextField();
	btnNum = new Button("Accept!");
	panepop.add(label2, 0, 0);
	panepop.add(txtplayers, 1, 0);
	panepop.add(btnNum, 1, 1);
	panepopp.setCenter(panepop);
		
	scene1 = new Scene(root, 350, 130);
	scene2 = new Scene(panepopp);
		
	stage2 = new Stage();
	stage2.setScene(scene2);
	stage2.initModality(Modality.APPLICATION_MODAL);


	stage.setTitle("Poker Game");
	stage.setScene(scene1);
	scene1.getStylesheets().add(
            getClass().getResource("poker.css").toExternalForm());
	stage.show();
	btnPlay = new Button("Play!");
	}
	
	public GridPane createTF(int number) {
		for (int i = 0; i < number; i++) {
			txtfields.add(new TextField("Player "+(i+1)));			
		}
		for (int i = 0; i <= number; i++) {
			labels.add(new Label("Name for Player"+(i+1)));			
		}
		
		GridPane pane = new GridPane();
		for (int i = 0; i < number; i++) {
			pane.add(labels.get(i), 0, i);
			pane.add(txtfields.get(i), 1, i);			
		}
		pane.add(btnPlay, 1, 11);
		return pane;
	}
	
	public String getName(int number) {
		String namefield = txtfields.get(number).getText();
		return namefield;
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public GridPane createPlayerPane(int number) {
		players = new GridPane();
		int y = 0;
		int x = 0;
		for (int i = 0; i < number; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i));
			if (y < 4) {
			players.add(pp, y, x);
			y++;
			} else {
				y = 0;
				x++;
				players.add(pp, y, x);
				y++;
			}
			pp.updatePlayerDisplay();
		}		
		return players;
	}
	 
	public void start() {
	stage2.show();
	}
	 
	public void stop() {
	stage.hide();
	}
		 
	public Stage getStage() {
	return stage;
	}


	
}