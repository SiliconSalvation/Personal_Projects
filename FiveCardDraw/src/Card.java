import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;
/**
 * A Card represents a playing card in a standard deck of 52 cards.
 * A Card has fields for its value, its suit, its color and an image.
 * The Card class has methods associated for setting these fields but 
 * the constructor handles these tasks.
 * @author Nick Stauffer
 * @author kazuma2448@protonmail.com
 */
public class Card {
	private final int width = 150;		//width of the card image
	private final int height = 218;		//height of the card image
	
	private Rank rank;
//	private String value;				//the card's value
	private Suit suit;					//the card's suit
	private CardColor color;			//the card's color
	private ImageIcon icon;				//the ImageIcon associated with this card
	private ImageIcon selectedIcon;
	/**
	 * The constructor is responsible for taking an int parameter for determining the
	 * value of the card. It also takes a string value to set the suit of the card.
	 * The color is set according to the suit of the card. Also sets the Icon for the
	 * card by reading the image location in the class Images.
	 * @param value	an integer value representing the value of the card
	 * @param suit a string value representing the suit of the card
	 */
	public Card(int value, Suit suit) {
		
		if(value == 0) {
			this.rank = Rank.TWO;
		}else if(value == 1) {
			this.rank = Rank.THREE;
		}else if(value == 2) {
			this.rank = Rank.FOUR;
		}else if(value == 3) {
			this.rank = Rank.FIVE;
		}else if(value == 4) {
			this.rank = Rank.SIX;
		}else if(value == 5) {
			this.rank = Rank.SEVEN;
		}else if(value == 6) {
			this.rank = Rank.EIGHT;
		}else if(value == 7) {
			this.rank = Rank.NINE;
		}else if(value == 8) {
			this.rank = Rank.TEN;
		}else if(value == 9) {
			this.rank = Rank.JACK;
		}else if(value == 10) {
			this.rank = Rank.QUEEN;
		}else if(value == 11) {
			this.rank = Rank.KING;
		}else {
			this.rank = Rank.ACE;
		}
		
		
		
//		if(value == 0) {
//			this.value = "Ace";
//		}else if(value == 10) {
//			this.value = "Jack";
//		}else if(value == 11) {
//			this.value = "Queen";
//		}else if(value == 12) {
//			this.value = "King";
//		}else {
//			this.value = Integer.toString(value + 1);
//		}
		
		this.suit = suit;
		
		setIcon(this);
		
		if(suit == Suit.SPADE || suit == Suit.CLUB) {
			this.color = CardColor.BLACK;
		}else {
			this.color = CardColor.RED;
		}
	}
	/**
	 * Getter for the field value
	 * @return a string representing the value of the card
	 */
//	public String getValue() {
//		return value;
//	}
	/**
	 * toString method that prints the value field and suit field
	 */
	public String toString() {
		return this.rank + " of " + this.suit; 
	}
	/**
	 * Setter for field value
	 * @param value A string representing the value of the card
	 */
//	public void setValue(String value) {
//		this.value = value;
//	}
	/**
	 * Getter for the field suit
	 * @return a string representing the suit of the card
	 */
	public Suit getSuit() {
		return suit;
	}
	/**
	 * Setter for the field suit
	 * @param suit a String representing the suit of the card
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	/**
	 * Getter for the field color
	 * @return a String representing the color of the card
	 */
	public CardColor getColor() {
		return color;
	}
	/**
	 * Setter for the field color
	 * @param color
	 */
	public void setColor(CardColor color) {
		this.color = color;
	}
	/**
	 * Getter for the field icon
	 * @return an ImageIcon representing the field icon. 
	 * (A picture associated with the card)
	 */
	public ImageIcon getIcon() {
		return this.icon;
	}
	public Rank getRank() {
		return this.rank;
	}
	public ImageIcon getSelectedIcon() {
		return this.selectedIcon;
	}
	/**
	 * Equals method to decide if the input card is the same as
	 * the calling card
	 */
	public final boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(!(o instanceof Card)) {
			return false;
		}
		Card card = (Card) o;
		
		return Objects.equals(rank, card.getRank()) 
				&& Objects.equals(suit, card.getSuit())
				&& Objects.equals(color, card.getColor());
	}
	private void setIcon(Card card) {
		if(card.getRank() == Rank.ACE && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.aceOfSpades, width, height);
			selectedIcon = makeIcon(Images.aceOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.TWO && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.twoOfSpades, width, height);
			selectedIcon = makeIcon(Images.twoOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.THREE && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.threeOfSpades, width, height);
			selectedIcon = makeIcon(Images.threeOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.FOUR && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.fourOfSpades, width, height);
			selectedIcon = makeIcon(Images.fourOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.FIVE && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.fiveOfSpades, width, height);
			selectedIcon = makeIcon(Images.fiveOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.SIX && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.sixOfSpades, width, height);
			selectedIcon = makeIcon(Images.sixOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.SEVEN && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.sevenOfSpades, width, height);
			selectedIcon = makeIcon(Images.sevenOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.eightOfSpades, width, height);
			selectedIcon = makeIcon(Images.eightOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.NINE && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.nineOfSpades, width, height);
			selectedIcon = makeIcon(Images.nineOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.TEN && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.tenOfSpades, width, height);
			selectedIcon = makeIcon(Images.tenOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.JACK && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.jackOfSpades, width, height);
			selectedIcon = makeIcon(Images.jackOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.QUEEN && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.queenOfSpades, width, height);
			selectedIcon = makeIcon(Images.queenOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.KING && card.getSuit() == Suit.SPADE) {
			icon = makeIcon(Images.kingOfSpades, width, height);
			selectedIcon = makeIcon(Images.kingOfSpadesSelected, width, height);
		}else if(card.getRank() == Rank.ACE && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.aceOfClubs, width, height);
			selectedIcon = makeIcon(Images.aceOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.TWO && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.twoOfClubs, width, height);
			selectedIcon = makeIcon(Images.twoOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.THREE && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.threeOfClubs, width, height);
			selectedIcon = makeIcon(Images.threeOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.FOUR && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.fourOfClubs, width, height);
			selectedIcon = makeIcon(Images.fourOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.FIVE && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.fiveOfClubs, width, height);
			selectedIcon = makeIcon(Images.fiveOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.SIX && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.sixOfClubs, width, height);
			selectedIcon = makeIcon(Images.sixOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.SEVEN && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.sevenOfClubs, width, height);
			selectedIcon = makeIcon(Images.sevenOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.eightOfClubs, width, height);
			selectedIcon = makeIcon(Images.eightOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.NINE && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.nineOfClubs, width, height);
			selectedIcon = makeIcon(Images.nineOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.TEN && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.tenOfClubs, width, height);
			selectedIcon = makeIcon(Images.tenOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.JACK && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.jackOfClubs, width, height);
			selectedIcon = makeIcon(Images.jackOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.QUEEN && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.queenOfClubs, width, height);
			selectedIcon = makeIcon(Images.queenOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.KING && card.getSuit() == Suit.CLUB) {
			icon = makeIcon(Images.kingOfClubs, width, height);
			selectedIcon = makeIcon(Images.kingOfClubsSelected, width, height);
		}else if(card.getRank() == Rank.ACE && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.aceOfHearts, width, height);
			selectedIcon = makeIcon(Images.aceOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.TWO && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.twoOfHearts, width, height);
			selectedIcon = makeIcon(Images.twoOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.THREE && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.threeOfHearts, width, height);
			selectedIcon = makeIcon(Images.threeOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.FOUR && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.fourOfHearts, width, height);
			selectedIcon = makeIcon(Images.fourOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.FIVE && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.fiveOfHearts, width, height);
			selectedIcon = makeIcon(Images.fiveOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.SIX && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.sixOfHearts, width, height);
			selectedIcon = makeIcon(Images.sixOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.SEVEN && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.sevenOfHearts, width, height);
			selectedIcon = makeIcon(Images.sevenOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.eightOfHearts, width, height);
			selectedIcon = makeIcon(Images.eightOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.NINE && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.nineOfHearts, width, height);
			selectedIcon = makeIcon(Images.nineOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.TEN && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.tenOfHearts, width, height);
			selectedIcon = makeIcon(Images.tenOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.JACK && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.jackOfHearts, width, height);
			selectedIcon = makeIcon(Images.jackOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.QUEEN && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.queenOfHearts, width, height);
			selectedIcon = makeIcon(Images.queenOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.KING && card.getSuit() == Suit.HEART) {
			icon = makeIcon(Images.kingOfHearts, width, height);
			selectedIcon = makeIcon(Images.kingOfHeartsSelected, width, height);
		}else if(card.getRank() == Rank.ACE && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.aceOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.aceOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.TWO && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.twoOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.twoOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.THREE && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.threeOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.threeOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.FOUR && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.fourOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.fourOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.FIVE && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.fiveOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.fiveOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.SIX && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.sixOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.sixOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.SEVEN && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.sevenOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.sevenOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.eightOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.eightOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.NINE && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.nineOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.nineOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.TEN && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.tenOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.tenOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.JACK && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.jackOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.jackOfDiamondsSelected, width, height);
		}else if(card.getRank() == Rank.QUEEN && card.getSuit() == Suit.DIAMOND) {
			icon = makeIcon(Images.queenOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.queenOfDiamondsSelected, width, height);
		}else {
			icon = makeIcon(Images.kingOfDiamonds, width, height);
			selectedIcon = makeIcon(Images.kingOfDiamondsSelected, width, height);
		}
	}
	
	private ImageIcon makeIcon(String img, int i, int j) {
		ImageIcon ico = new ImageIcon(img);
		Image image = ico.getImage();
		Image newimg = image.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
}