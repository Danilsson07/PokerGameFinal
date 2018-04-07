package View;

import Model.Card;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardLabelView extends Label {

	//constructor to display the card
	public CardLabelView() {
		super();
		//specific css design for the card
		this.getStyleClass().add("card");
	}

	//method to create all the cards by calling the pictures with the createfilename method
	public void setCard(Card card) {
		if (card != null) {
			String fileName = createFileName(card);
			String image = getClass().getResource("/Images/"+fileName).toExternalForm();
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.setGraphic(imv);
		} else {
			this.setGraphic(null);
		}
	}

	//method to set the Deck Card by calling the picture of the backside of the card.
	public void setDeckCard() {
			String image = getClass().getResource("/Images/BACK6.png").toExternalForm();
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.setGraphic(imv);
	}

	//creates the filename string which is equal to the filename of the cards.
	public String createFileName(Card card) {
		String rank = card.RanktoString();
		String suit = card.SuittoString();
		return rank+suit+".png";
	}

}
