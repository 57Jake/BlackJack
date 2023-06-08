import java.awt.EventQueue;
import java.awt.Image;

import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class BlackJackWindow extends JFrame {

	private JPanel contentPane;
	private static JLabel lblPlayerTotal;
	private static JLabel lblDealerTotal;
	private static JLabel lblPotValue;
	private static JLabel lblCardsDrawn;
	private static JLabel lblPlayerPoints;
	private static JLabel lblDealerPoints;
	
	private static JLabel lblPlayerCardOne;
	/**
	 * Launch the application.
	 */
	public static String[] deckArray = new String[52];
	
	public static boolean gameOn = false;
	public static boolean playerStand = false;
	public static boolean playerHasAce = false;
	public static boolean dealerHasAce = false;
	public static boolean gameOver = false;
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * Method: Void Main
	 * 
	 * Parameters: args (allows for string line parameters)
	 * 
	 * Returns: void
	 * 
	 * Synopsis: Main method that opens the window used to play the game BlackJack.
	 * 
	 * Developed by Jacob Hache
	 * 
	 * March 31st, 2023
	 * 
	 -------------------------------------------------------------------------------------------------------------------------*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackWindow frame = new BlackJackWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BlackJackWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//puts a label for all of the cards to be displayed.
		JLabel lblDealerCardOne = new JLabel();
		lblDealerCardOne.setBounds(58, 30, 89, 110);
		contentPane.add(lblDealerCardOne);
		lblDealerCardOne.setIcon(new ImageIcon("card back red.png"));
		
		JLabel lblDealerCardTwo = new JLabel("");
		lblDealerCardTwo.setBounds(146, 30, 46, 14);
		contentPane.add(lblDealerCardTwo);
		
		JLabel lblDealerCardThree = new JLabel("");
		lblDealerCardThree.setBounds(308, 30, 46, 14);
		contentPane.add(lblDealerCardThree);
		
		JLabel lblDealerCardFour = new JLabel("");
		lblDealerCardFour.setBounds(252, 30, 46, 14);
		contentPane.add(lblDealerCardFour);
		
		JLabel lblDealerCardFive = new JLabel("");
		lblDealerCardFive.setBounds(434, 30, 46, 14);
		contentPane.add(lblDealerCardFive);
		
		
		 lblPlayerCardOne = new JLabel("");
		lblPlayerCardOne.setBounds(58, 190, 89, 110);
		contentPane.add(lblPlayerCardOne);
		
		
		
		JLabel lblPlayerCardTwo = new JLabel("");
		lblPlayerCardTwo.setBounds(146, 267, 46, 14);
		contentPane.add(lblPlayerCardTwo);
		
		JLabel lblPlayerCardThree = new JLabel("");
		lblPlayerCardThree.setBounds(252, 267, 46, 14);
		contentPane.add(lblPlayerCardThree);
		
		JLabel lblPlayerCardFour = new JLabel("");
		lblPlayerCardFour.setBounds(349, 267, 46, 14);
		contentPane.add(lblPlayerCardFour);
		
		JLabel lblPlayerCardFive = new JLabel("");
		lblPlayerCardFive.setBounds(460, 267, 46, 14);
		contentPane.add(lblPlayerCardFive);
		
		
		/*sets labels for the score of the dealer and player, how many cards were drawn, 
		 * the amount of money both the dealer and player have, the current pot,
		 * 
		 */
		JLabel lblDelaer = new JLabel("Dealer: ");
		lblDelaer.setBounds(535, 63, 74, 14);
		contentPane.add(lblDelaer);
		
		JLabel lblPlayer = new JLabel("Player: ");
		lblPlayer.setBounds(535, 242, 89, 14);
		contentPane.add(lblPlayer);
		
		JLabel lblTotalPot = new JLabel("Total Pot:");
		lblTotalPot.setBounds(535, 166, 89, 14);
		contentPane.add(lblTotalPot);
		
		 lblPlayerTotal = new JLabel("1000");
		lblPlayerTotal.setBounds(535, 267, 46, 14);
		contentPane.add(lblPlayerTotal);
		
		 lblDealerTotal = new JLabel("1000");
		lblDealerTotal.setBounds(535, 88, 46, 14);
		contentPane.add(lblDealerTotal);
		
		 lblPotValue = new JLabel("0");
		lblPotValue.setBounds(535, 190, 46, 14);
		contentPane.add(lblPotValue);
		
		JLabel lblcardLabel = new JLabel("Cards Drawn:");
		lblcardLabel.setBounds(58, 141, 74, 14);
		contentPane.add(lblcardLabel);
		
		 lblCardsDrawn = new JLabel("0");
		lblCardsDrawn.setBounds(68, 166, 46, 14);
		contentPane.add(lblCardsDrawn);
		
		JLabel lblPlayerLabel = new JLabel("Points:");
		lblPlayerLabel.setBounds(608, 242, 46, 14);
		contentPane.add(lblPlayerLabel);
		
		 lblPlayerPoints = new JLabel("0");
		lblPlayerPoints.setBounds(618, 267, 16, 14);
		contentPane.add(lblPlayerPoints);
		
		JLabel lblDealerLabel = new JLabel("Points:");
		lblDealerLabel.setBounds(608, 63, 46, 14);
		contentPane.add(lblDealerLabel);
		
		 lblDealerPoints = new JLabel("0");
		lblDealerPoints.setBounds(618, 88, 36, 14);
		contentPane.add(lblDealerPoints);
		
		//button that calls the increase bet method.
		JButton btnBet = new JButton("Increase Bet");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				increaseBet();
				
			}
		});
		btnBet.setBounds(434, 311, 89, 23);
		contentPane.add(btnBet);
		
		/*
		 * button that calls the draw card method for the player, but only if a game is in progress.
		 * Also calls the gameCheck method for the win Conditions.
		 * 
		 */
		JButton btnHit = new JButton("Hit Me!");
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (gameOn == false) {
					JOptionPane.showMessageDialog(null, "Start a new game first!");
				}
				else {
					String playerCardsDrawn = lblCardsDrawn.getText();
					int playerCardCount = Integer.parseInt(playerCardsDrawn);
					
					drawCard(playerCardCount);
					 playerCardCount += 1;
					 
					 playerCardsDrawn = Integer.toString(playerCardCount);
					lblCardsDrawn.setText(playerCardsDrawn);
					
					gameCheck();
				}

				 
			}
		});
		btnHit.setBounds(58, 311, 89, 23);
		contentPane.add(btnHit);
		
		//button that calls the drawCard and gameCheck methods for the dealer, until the game ends, but only if a game is in progress.
		JButton btnStand = new JButton("Stand");
		btnStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (gameOn == false) {
					
						JOptionPane.showMessageDialog(null, "Start a new game first!");
					
				}
				else {
					
					playerStand = true;
					
					String dealerCardsDrawn = lblCardsDrawn.getText();
					int dealerCardCount = Integer.parseInt(dealerCardsDrawn);
					
					dealerCardCount = 0;

					String playerPoints = lblPlayerPoints.getText();
					String dealerPoints = lblDealerPoints.getText();
					
					int playerTotal = Integer.parseInt(playerPoints);
					int dealerTotal = Integer.parseInt(dealerPoints);
					
					while (dealerTotal <= playerTotal && gameOn == true) {
						
						playerPoints = lblPlayerPoints.getText();
						dealerPoints = lblDealerPoints.getText();
						
						playerTotal = Integer.parseInt(playerPoints);
						dealerTotal = Integer.parseInt(dealerPoints);
						
						drawCard(dealerCardCount);
						 dealerCardCount += 1;
						 
						 dealerCardsDrawn = Integer.toString(dealerCardCount);
						lblCardsDrawn.setText(dealerCardsDrawn);
						
						gameCheck();
					}
				}


			}
		});
		btnStand.setBounds(173, 311, 89, 23);
		contentPane.add(btnStand);
		
		/*
		 * button that starts a new game if the last game has ended,
		 *  and both the dealer and player have bet money into the pot.
		 */
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String potString;
				potString = lblPotValue.getText();
				
				int potOfHand = Integer.parseInt(potString);
				int index = 0;
				
				if (gameOver == true) {
					JOptionPane.showMessageDialog(null, "Either you or the dealre has run out of money. Come back later!");
				}
				else if (potOfHand == 0) {
					JOptionPane.showMessageDialog(null, "The pot is empty. Bet some money first!");
				}
				else if (gameOn == true) {
					JOptionPane.showMessageDialog(null, "A game is already in progress, finish it first!");
				}
				else {
					
					//double for loop that makes a new deck of cards.
					String[] suitArray = {"Hearts", "Diamonds", "Spades", "Clubs"};
					String[] rankArray = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
					
					for (int suit = 0; suit < 4; suit++) {
						for (int rank = 0; rank < 13; rank++) {
							
							deckArray[index] = (rankArray[rank] + " of " + suitArray[suit]);
							index += 1;						
							
						}
					}		
					//calls the shuffleDeck method to shuffle the newly made deck.
							shuffleDeck();
							
							//displays a window saying the deck is shuffled, and shows how much money is in the pot.
							JOptionPane.showMessageDialog(null, "The deck has been shuffled. " + potOfHand + " Dollars are at stake!");
							
							playerStand = false;
							
							String playerCardsDrawn = lblCardsDrawn.getText();
							int playerCardCount = Integer.parseInt(playerCardsDrawn);
							
							//calls the drawCard method twice to start the player with 2 cards.
							for (int startCards = 0; startCards < 2; startCards++) {

								drawCard(playerCardCount);
								 playerCardCount += 1;
								 
								 playerCardsDrawn = Integer.toString(playerCardCount);
								lblCardsDrawn.setText(playerCardsDrawn);
							}
							
							//turns on the boolean gameOn to allow btnHit and btnStand to be pressed, whilst disabling btnBet.
							gameOn = true;
				}
				
			}
		});
		btnNewGame.setBounds(306, 311, 89, 23);
		contentPane.add(btnNewGame);
		

		


	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * Method: void shuffleDeck
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * Synopsis: Shuffles the deck for the following game.
	 * 
	 * Developed by Jacob Hache
	 * 
	 * March 21st, 2023
	 * 
	 -------------------------------------------------------------------------------------------------------------------------*/
	public static void shuffleDeck() {
		
		//places the array into a list to be shuffled, then places the shuffled list back into the array, shuffling the deck.
		
		List<String> shuffledDeck = Arrays.asList(deckArray);
		
		Collections.shuffle(shuffledDeck);
		
		shuffledDeck.toArray(deckArray);
		

	}
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * Method: void increase bet
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * Synopsis: Takes 10dollars from the player and the dealer and puts it into the pot,
	 * if both players have sufficient money.
	 * 
	 * Developed by Jacob Hache
	 * 
	 * March 21st, 2023
	 * 
	 -------------------------------------------------------------------------------------------------------------------------*/
	
	public static void increaseBet() {
		
		String playerString, dealerString, potString;
		int playerMoney, dealerMoney, totalPot = 0;
		
		playerString = lblPlayerTotal.getText();
		dealerString = lblDealerTotal.getText();
		potString = lblPotValue.getText();
		
		playerMoney = Integer.parseInt(playerString);
		dealerMoney = Integer.parseInt(dealerString);
		totalPot = Integer.parseInt(potString);
		
		if (gameOn == true) {
			JOptionPane.showMessageDialog(null, "A game is in progress. Wait for the next game to bet!");
		}
		else if(playerMoney == 0) {
			JOptionPane.showMessageDialog(null, "You've already gone all in. You can't bet any higher!");
		}
		else if (dealerMoney == 0) {
			JOptionPane.showMessageDialog(null, "The dealer is all in. You can't bet any higher!");
		}
		else {
			
			playerMoney -= 10;
			dealerMoney -= 10;
			totalPot += 20;
			
			playerString = Integer.toString(playerMoney);
			dealerString = Integer.toString(dealerMoney);
			potString = Integer.toString(totalPot);
			
			lblPlayerTotal.setText(playerString);
			lblDealerTotal.setText(dealerString);
			lblPotValue.setText(potString);
		}
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * Method: int drawCard
	 * 
	 * Parameters: integer cardCounter
	 * 
	 * Returns: cardCounter
	 * 
	 * Synopsis: Assigns the player, or the dealer points, based on whatever card they just drew.
	 * 
	 * Developed by Jacob Hache
	 * 
	 * March 21st, 2023
	 * 
	 -------------------------------------------------------------------------------------------------------------------------*/
	
	public static int drawCard(int cardCounter) {
		
		String cardString = lblCardsDrawn.getText();
		String playerPoints = lblPlayerPoints.getText();
		String dealerPoints = lblDealerPoints.getText();
		
		//takes the counter parameter to determine what card to draw next, in sequential order.
		cardString = deckArray[cardCounter];
		
		int playerGets = Integer.parseInt(playerPoints);
		int dealerGets = Integer.parseInt(dealerPoints);
		
		//gives points based on what the string of the card drawn contains.
		if (cardString.contains("Ace")){
			if (playerStand == true) {
				dealerGets += 11;
				dealerHasAce = true;
			}
			else {
				playerGets += 11;
				playerHasAce = true;
			}
			
		}
		else if (cardString.contains("2")){
			if (playerStand == true) {
				dealerGets += 2;
			}
			else {
				playerGets += 2;
			}
		}
		if (cardString.contains("3")){
			if (playerStand == true) {
				dealerGets += 3;
			}
			else {
				playerGets += 3;
			}
		}
		if (cardString.contains("4")){
			if (playerStand == true) {
				dealerGets += 4;
			}
			else {
				playerGets += 4;
			}
		}
		if (cardString.contains("5")){
			if (playerStand == true) {
				dealerGets += 5;
			}
			else {
				playerGets += 5;
			}
		}
		if (cardString.contains("6")){
			if (playerStand == true) {
				dealerGets += 6;
			}
			else {
				playerGets += 6;
			}
		}
		if (cardString.contains("7")){
			if (playerStand == true) {
				dealerGets += 7;
			}
			else {
				playerGets += 7;
			}
		}
		if (cardString.contains("8")){
			if (playerStand == true) {
				dealerGets += 8;
			}
			else {
				playerGets += 8;
			}
		}
		if (cardString.contains("9")){
			if (playerStand == true) {
				dealerGets += 9;
			}
			else {
				playerGets += 9;
			}
		}
		if (cardString.contains("10") || cardString.contains("Jack") || cardString.contains("Queen") || cardString.contains("King")){
			if (playerStand == true) {
				dealerGets += 10;
			}
			else {
				playerGets += 10;
			}
		}
		
		if (playerStand == true) {
			
			//turns the 11 of the ace into a 1 if required.
			if(dealerHasAce == true && dealerGets > 21)
				dealerGets -= 10;
			
		}
		
		else {
			
			if(playerHasAce == true && playerGets > 21)
				playerGets -= 10;
			
		}
		
		dealerPoints = Integer.toString(dealerGets);
		playerPoints = Integer.toString(playerGets);
		lblPlayerPoints.setText(playerPoints);
		lblDealerPoints.setText(dealerPoints);
		
		
		
		
		return cardCounter;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * Method: void gameCheck
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * Synopsis: Checks to see if any of the win/lose conditions of BlackJack have been met, such as busting,
	 * getting 21 points, the dealer beating the player in points, or a 5 card monty. 
	 * 
	 * Developed by Jacob Hache
	 * 
	 * March 21st, 2023
	 * 
	 -------------------------------------------------------------------------------------------------------------------------*/
	
	public static void gameCheck() {
		
		String playerPoints = lblPlayerPoints.getText();
		String dealerPoints = lblDealerPoints.getText();
		String playerCardCount = lblCardsDrawn.getText();
		String potString = lblPotValue.getText();
		
		String playerString = lblPlayerTotal.getText();
		String dealerString = lblDealerTotal.getText();
		
		
		int playerMoney = Integer.parseInt(playerString);
		int dealerMoney = Integer.parseInt(dealerString);
		
		
		int playerTotal = Integer.parseInt(playerPoints);
		int dealerTotal = Integer.parseInt(dealerPoints);
		int cardTotal = Integer.parseInt(playerCardCount);
		int winnings = Integer.parseInt(potString);
				if (playerStand == false) {
					if (playerTotal > 21) {
						JOptionPane.showMessageDialog(null, "You've busted, be careful next time!");
						gameOn = false;
						//adds the pot onto the total money of whoever won the hand.
						dealerMoney += winnings;
						winnings = 0;
						

					}
					else if (cardTotal == 5 && playerTotal < 21) {
						JOptionPane.showMessageDialog(null, "You win with a 5 card monty!");
						playerMoney += winnings;
						winnings = 0;
						gameOn = false;
					}
				}
				//win conditions for when the dealer starts drawing cards.
				else {
					if (dealerTotal > 21) {
						JOptionPane.showMessageDialog(null, "The dealer busted, you win!");
						playerMoney += winnings;
						winnings = 0;
						gameOn = false;
					}
					
					else if (dealerTotal > playerTotal) {
						JOptionPane.showMessageDialog(null, "The dealer beat your total. Try again.");
						dealerMoney += winnings;
						winnings = 0;
						gameOn = false;
					}
					
					else if (cardTotal == 5 && dealerTotal < 21) {
						JOptionPane.showMessageDialog(null, "The dealer got a 5 card monty. Try again.");
						dealerMoney += winnings;
						winnings = 0;
						gameOn = false;
					}
					
					else if (dealerTotal == 21 && playerTotal == 21) {
						JOptionPane.showMessageDialog(null, "Both you and the dealer have 21. It's a push!");
						dealerMoney += winnings / 2;
						playerMoney += winnings / 2;
						winnings = 0;
						gameOn = false;
						
					}
				}
		
				//resets all values except for the money of the player and dealer to 0 if the game ends.
				if (gameOn == false) {
					
					playerTotal = 0;
					dealerTotal = 0;
					cardTotal = 0;
					
					
					playerPoints = Integer.toString(playerTotal);
					dealerPoints = Integer.toString(dealerTotal);
					playerString = Integer.toString(playerMoney);
					dealerString = Integer.toString(dealerMoney);
					potString = Integer.toString(winnings);
					playerCardCount = Integer.toString(cardTotal);
					
					lblCardsDrawn.setText(playerCardCount);
					lblPlayerTotal.setText(playerString);
					lblDealerTotal.setText(dealerString);
					lblPotValue.setText(potString);
					lblPlayerPoints.setText(playerPoints);
					lblDealerPoints.setText(dealerPoints);
					
					//prevents any more games from being played if either the player or the dealer go broke.
					if (playerMoney == 0) {
						JOptionPane.showMessageDialog(null, "GAME OVER. You've run out of money!");
						gameOver = true;
					}
					else if (dealerMoney == 0) {
						JOptionPane.showMessageDialog(null, "WELL DONE. You've taken all of the dealer's money!");
						gameOver = true;
					}
				}
				
	}
}


/*
 * Pankaj. (2022, August 3). How to shuffle an array in Java. How To Shuflle An Array In Java | DigitalOcean.
 *  Retrieved March 30, 2023, from https://www.digitalocean.com/community/tutorials/shuffle-array-java 
 *  
 *  Used to shuffle the deck of cards.
 */


*/