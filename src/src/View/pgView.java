package View;

import Model.Card;
import Model.pgModel;
import javafx.geometry.Pos;
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
	public Scene scene1;
	public Scene scene2;
	public Button btnAsk;
	public Button btnNum;
	public Button btnPlay;
	public Button btnShuf = new Button("Shuffle");
	public Button btnDeal = new Button("Deal");
	public Button btnSW = new Button("Show Winner!");
	protected Label label1, label2;
	public TextField txtplayers;
	protected BorderPane pane1;
	public BorderPane panepopp;
	public GridPane panepop;
	protected GridPane players;
	protected HBox menuBox;
	protected HBox playerBox;
	protected HBox controlBox;
	public BorderPane root;
    private Label pgTitel;
    Region spacer = new Region();
	public CardLabelView c2;

    public ArrayList<TextField> txtfields = new ArrayList<>();
	protected ArrayList<Label> labels = new ArrayList<>();

	public pgView(Stage stage, pgModel model) {
	this.stage = stage;
	this.model = model;


	pgTitel = new Label("Poker Game");
	pgTitel.setId("titel");


	btnAsk = new Button("Players");
    playerBox = new HBox();

	//Creating Deck
    c2 = new CardLabelView();
    c2.setDeckCard();

    //Creating controlBox and menubox, setting both to the center of the stage
    controlBox = new HBox(5, c2, spacer, btnShuf, btnDeal, btnSW, btnAsk);
    controlBox.setAlignment(Pos.CENTER);
    menuBox = new HBox(pgTitel);
    menuBox.setAlignment(Pos.CENTER);

	//creating BorderPane for the maingame and setting other views
    root = new BorderPane();
    root.setTop(menuBox);
    root.setCenter(playerBox);
    root.setBottom(controlBox);

	//creating the start menu
    panepopp = new BorderPane();
    label2 = new Label("How many Players joint the game??");
	txtplayers = new TextField();
	btnNum = new Button("Accept!");
	panepop = new GridPane();
	panepop.add(label2, 0, 0);
	panepop.add(txtplayers, 1, 0);
	panepop.add(btnNum, 1, 1);
	panepop.setAlignment(Pos.CENTER);
	panepopp.setCenter(panepop);

	// Create the scene1 (maingame) using our layout; then display it
	scene1 = new Scene(root);
	scene1.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
	stage.setScene(scene1);

	// Create the scene2 (startmenu) using our layout; then display it
	scene2 = new Scene(panepopp,500,450);
	scene2.getStylesheets().add(getClass().getResource("poker2.css").toExternalForm());
	stage2 = new Stage();
	stage2.setScene(scene2);
	stage2.initModality(Modality.APPLICATION_MODAL);
	stage2.setTitle("Poker Game");


	btnPlay = new Button("Play!");


	}

	//Creates the Labels and text field to write the players name
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
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	
	public String getName(int number) {
		String namefield = txtfields.get(number).getText();
		return namefield;
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}

	//Creates the Playerpanes and put it in a gridpane
	public GridPane createPlayerPane(int number) {
		players = new GridPane();
		int y = 0;
		int x = 0;
		for (int i = 0; i < number; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i));
			if (y < 3) {
			players.add(pp, y, x);
			y++;
			} else {
				y = 0;
				x++;
				players.add(pp, y, x);
				y++;
			}
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