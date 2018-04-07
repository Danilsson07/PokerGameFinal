import Controller.pgController;
import Model.pgModel;
import View.pgView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PokerGame extends Application{
	pgView view;
	pgController controller;
	pgModel model;

	 public static void main(String[] args) {
	 launch(args);
	 }
	 
	 @Override
	 public void start(Stage primaryStage) {
	 model = new pgModel();
	 view = new pgView(primaryStage, model);
	 controller = new pgController(model, view);
	 view.start();
	 }
	 
	 @Override
	 public void stop() {
	 if (view != null)
	 view.stop();
	 }

}
