import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import javax.swing.JSpinner;

public class GUI extends JFrame{
	private final int bet = 10;
	private final Color BACKGROUND_COLOR = new Color(51, 153, 102);
//	private final Color BACKGROUND_COLOR = new Color(0, 0, 0);
	private Card[] pHand = new Card[5];
	private static int bankRoll = 100;
	
	private int cardCount;
	private int turnCount;
	
	private JTextField bankroll;
	private JPanel cardsPanel;
	private FlowLayout flowLayout;
	private JPanel drawButtonPanel;
	private GridLayout cardsLayout;
	private JButton drawButton;
	
	private JCheckBox drawCard1;
	private JCheckBox drawCard2;
	private JCheckBox drawCard3;
	private JCheckBox drawCard4;
	private JCheckBox drawCard5;
	
	private JPanel card1Panel;
	private JPanel card2Panel;
	private JPanel card3Panel;
	private JPanel card4Panel;
	private JPanel card5Panel;
	
//	private JLabel card1;
//	private JLabel card2;
//	private JLabel card3;
//	private JLabel card4;
//	private JLabel card5;
	
	private Deck deck;
	private JSpinner spinnerWager;
	private SpinnerModel sm;
	
	
	public GUI() {
		
		cardCount = 0;
		turnCount = 0;
		deck = new Deck();
		
		Deck.shuffle(deck.getDeck(), 52);
		
		
		setTitle("Five Card Draw!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1300, 700);
		setBackground(new Color(51, 153, 102));
		
		drawButtonPanel = new JPanel();
		drawButtonPanel.setPreferredSize(new Dimension(10, 150));
		drawButtonPanel.setBackground(BACKGROUND_COLOR);
		flowLayout = (FlowLayout) drawButtonPanel.getLayout();
		flowLayout.setHgap(300);
		getContentPane().add(drawButtonPanel, BorderLayout.SOUTH);
		
		drawButton = new JButton("Draw");
		drawButton.setPreferredSize(new Dimension(160, 75));
		drawButton.setMaximumSize(new Dimension(150, 75));
		drawButton.setMinimumSize(new Dimension(150, 75));
		drawButton.setSize(new Dimension(150, 75));
		drawButton.setMnemonic('D');
		drawButtonPanel.add(drawButton);
		bankroll = new JTextField();
		bankroll.setEditable(false);
		bankroll.setColumns(15);
		bankroll.setBackground(BACKGROUND_COLOR);
		bankroll.setText(Integer.toString(bankRoll));
		drawButtonPanel.add(bankroll);
		
		sm = new SpinnerNumberModel(10, 10, Integer.MAX_VALUE, 10);
		
		spinnerWager = new JSpinner(sm);
		spinnerWager.setPreferredSize(new Dimension(100, 30));
		drawButtonPanel.add(spinnerWager);
		drawButton.addActionListener(new DrawButtonListener());
		
		
		cardsPanel = new JPanel();
		getContentPane().add(cardsPanel, BorderLayout.CENTER);
		cardsPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		card1Panel = new JPanel();
		card1Panel.setBackground(BACKGROUND_COLOR);
		card1Panel.setLayout(new GridLayout(1,0,0,0));
		cardsPanel.add(card1Panel);
		
		
//		card1 = new JLabel("");
		drawCard1 = new JCheckBox();
		
		drawCard1.setText("Discard");
		drawCard1.setBackground(BACKGROUND_COLOR);
//		card1Panel.add(card1);
		card1Panel.add(drawCard1);
		drawCard1.addActionListener(new RadioButtonListener());
		
		card2Panel = new JPanel();
		card2Panel.setBackground(BACKGROUND_COLOR);
		card2Panel.setLayout(new GridLayout(1,0,0,0));
		cardsPanel.add(card2Panel);
		
//		card2 = new JLabel("");
		drawCard2 = new JCheckBox();
		drawCard2.setText("Discard");
		drawCard2.setBackground(BACKGROUND_COLOR);
//		card2Panel.add(card2);
		card2Panel.add(drawCard2);
		drawCard2.addActionListener(new RadioButtonListener());
		
		card3Panel = new JPanel();
		card3Panel.setBackground(BACKGROUND_COLOR);
		card3Panel.setLayout(new GridLayout(1,0,0,0));
		cardsPanel.add(card3Panel);
		
//		card3 = new JLabel("");
		drawCard3 = new JCheckBox();
		drawCard3.setText("Discard");
		drawCard3.setBackground(BACKGROUND_COLOR);
//		card3Panel.add(card3);
		card3Panel.add(drawCard3);
		drawCard3.addActionListener(new RadioButtonListener());
		
		card4Panel = new JPanel();
		card4Panel.setBackground(BACKGROUND_COLOR);
		card4Panel.setLayout(new GridLayout(1,0,0,0));
		cardsPanel.add(card4Panel);
		
//		card4 = new JLabel("");
		drawCard4 = new JCheckBox();
		drawCard4.setText("Discard");
		drawCard4.setBackground(BACKGROUND_COLOR);
//		card4Panel.add(card4);
		card4Panel.add(drawCard4);
		drawCard4.addActionListener(new RadioButtonListener());
		
		card5Panel = new JPanel();
		card5Panel.setBackground(BACKGROUND_COLOR);
		card5Panel.setLayout(new GridLayout(1,0,0,0));
		cardsPanel.add(card5Panel);
		
//		card5 = new JLabel("");
		drawCard5 = new JCheckBox();
		drawCard5.setText("Discard");
		drawCard5.setBackground(BACKGROUND_COLOR);
//		card5Panel.add(card5);
		card5Panel.add(drawCard5);
		drawCard5.addActionListener(new RadioButtonListener());
		
////		card1.setIcon(deck.getDeck()[cardCount].getIcon());
//		pHand[0] = deck.getDeck()[cardCount];
//		drawCard1.setIcon(pHand[0].getIcon());
//		drawCard1.setSelectedIcon(pHand[0].getSelectedIcon());
//		cardCount++;
////		card2.setIcon(deck.getDeck()[cardCount].getIcon());
//		pHand[1] = deck.getDeck()[cardCount];
//		drawCard2.setIcon(pHand[1].getIcon());
//		drawCard2.setSelectedIcon(pHand[1].getSelectedIcon());
//		cardCount++;
////		card3.setIcon(deck.getDeck()[cardCount].getIcon());
//		pHand[2] = deck.getDeck()[cardCount];
//		drawCard3.setIcon(pHand[2].getIcon());
//		drawCard3.setSelectedIcon(pHand[2].getSelectedIcon());
//		cardCount++;
////		card4.setIcon(deck.getDeck()[cardCount].getIcon());
//		pHand[3] = deck.getDeck()[cardCount];
//		drawCard4.setIcon(pHand[3].getIcon());
//		drawCard4.setSelectedIcon(pHand[3].getSelectedIcon());
//		cardCount++;
////		card5.setIcon(deck.getDeck()[cardCount].getIcon());
//		pHand[4] = deck.getDeck()[cardCount];
//		drawCard5.setIcon(pHand[4].getIcon());
//		drawCard5.setSelectedIcon(pHand[4].getSelectedIcon());
//		cardCount++;
		
//		bankRoll -= bet;
		
		//drawCard1.setIcon(pHand[0].getIcon());
		//drawCard1.set
		
		setVisible(true);
	}
	
	private class DrawButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(turnCount != 2) {
				bankRoll -= bet;
			}
			if(turnCount == 2) {
				drawCard1.setSelected(false);
				drawCard2.setSelected(false);	
				drawCard3.setSelected(false);
				drawCard4.setSelected(false);
				drawCard5.setSelected(false);
				Deck.shuffle(deck.getDeck(), 52);
				JOptionPane.showMessageDialog(rootPane, Deck.getWinnings(pHand, bankRoll, bet));
				bankroll.setText(Integer.toString(bankRoll));
				cardCount = 0;
//				card1.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[0] = deck.getDeck()[cardCount];
				drawCard1.setIcon(pHand[0].getIcon());
				drawCard1.setSelectedIcon(pHand[0].getSelectedIcon());
				cardCount++;
//				card2.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[1] = deck.getDeck()[cardCount];
				drawCard2.setIcon(pHand[1].getIcon());
				drawCard2.setSelectedIcon(pHand[1].getSelectedIcon());
				cardCount++;
//				card3.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[2] = deck.getDeck()[cardCount];
				drawCard3.setIcon(pHand[2].getIcon());
				drawCard3.setSelectedIcon(pHand[2].getSelectedIcon());
				cardCount++;
//				card4.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[3] = deck.getDeck()[cardCount];
				drawCard4.setIcon(pHand[3].getIcon());
				drawCard4.setSelectedIcon(pHand[3].getSelectedIcon());
				cardCount++;
//				card5.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[4] = deck.getDeck()[cardCount];
				drawCard5.setIcon(pHand[4].getIcon());
				drawCard5.setSelectedIcon(pHand[4].getSelectedIcon());
				cardCount++;
				turnCount = 0;
				drawCard1.setText("Discard");
				drawCard2.setText("Discard");
				drawCard3.setText("Discard");
				drawCard4.setText("Discard");
				drawCard5.setText("Discard");
				if(bankRoll <= 0) {
					JOptionPane.showMessageDialog(rootPane, "You are out of chips, game over!!!");
					System.exit(0);
				}
			}
			
			if(cardCount > 51) {
				Deck.shuffle(deck.getDeck(), 52);
				cardCount = 0;
			}
			if(!(drawCard1.isSelected())) {
				cardCount++;
//				card1.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[0] = deck.getDeck()[cardCount];
				drawCard1.setIcon(pHand[0].getIcon());
				drawCard1.setSelectedIcon(pHand[0].getSelectedIcon());
				
			}
			if(!(drawCard2.isSelected())) {
				cardCount++;
//				card2.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[1] = deck.getDeck()[cardCount];
				drawCard2.setIcon(pHand[1].getIcon());
				drawCard2.setSelectedIcon(pHand[1].getSelectedIcon());
			}
			if(!(drawCard3.isSelected())) {
				cardCount++;
//				card3.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[2] = deck.getDeck()[cardCount];
				drawCard3.setIcon(pHand[2].getIcon());
				drawCard3.setSelectedIcon(pHand[2].getSelectedIcon());
			}
			if(!(drawCard4.isSelected())) {
				cardCount++;
//				card4.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[3] = deck.getDeck()[cardCount];
				drawCard4.setIcon(pHand[3].getIcon());
				drawCard4.setSelectedIcon(pHand[3].getSelectedIcon());
			}
			if(!(drawCard5.isSelected())) {
				cardCount++;
//				card5.setIcon(deck.getDeck()[cardCount].getIcon());
				pHand[4] = deck.getDeck()[cardCount];
				drawCard5.setIcon(pHand[4].getIcon());
				drawCard5.setSelectedIcon(pHand[4].getSelectedIcon());
			}
			
			turnCount++;
		}
		
	}
	
	private class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == drawCard1) {
				if(drawCard1.isSelected()) {
					drawCard1.setText("Hold");
//					drawCard1.setSelectedIcon(pHand[0].getSelectedIcon());
				}else {
					drawCard1.setText("Discard");
				}
			}
			if(e.getSource() == drawCard2) {
				if(drawCard2.isSelected()) {
					drawCard2.setText("Hold");
//					drawCard2.setSelectedIcon(pHand[1].getSelectedIcon());
				}else {
					drawCard2.setText("Discard");
				}
			}
			if(e.getSource() == drawCard3) {
				if(drawCard3.isSelected()) {
					drawCard3.setText("Hold");
//					drawCard3.setSelectedIcon(pHand[2].getSelectedIcon());
				}else {
					drawCard3.setText("Discard");
				}
			}
			if(e.getSource() == drawCard4) {
				if(drawCard4.isSelected()) {
					drawCard4.setText("Hold");
//					drawCard4.setSelectedIcon(pHand[3].getSelectedIcon());
				}else {
					drawCard4.setText("Discard");
				}
			}
			if(e.getSource() == drawCard5) {
				if(drawCard5.isSelected()) {
					drawCard5.setText("Hold");
//					drawCard5.setSelectedIcon(pHand[4].getSelectedIcon());
				}else {
					drawCard5.setText("Discard");
				}
			}

		}
		
	}
	public static void setBankRoll(int br) {
		bankRoll = br;
	}
}