package View;

import Model.Card;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardLabelView extends Label {
	
	public CardLabelView() {
		super();
		this.getStyleClass().add("card");
	}

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

	public String createFileName(Card card) {
		String rank = card.RanktoString();
		String suit = card.SuittoString();
		return rank+suit+".png";
	}

}
