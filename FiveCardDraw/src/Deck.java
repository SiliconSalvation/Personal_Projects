import java.util.Random;
/**
 * A Deck represents a standard deck of 52 playing cards
 * @author Nick Stauffer
 * @author kazuma2448@protonmail.com
 */
public class Deck {
	Card[] deck;
	/**
	 * Constructor that creates a deck of 52 standard playing cards
	 */
	public Deck() {
		this.deck = new Card[52];
		
		for(int i = 0; i < deck.length; i++) {
			if(i < 13) {
				deck[i] = new Card(i, Suit.SPADE);
			}else if(i > 12 && i < 26) {
				deck[i] = new Card(i % 13, Suit.CLUB);
			}else if(i > 25 && i < 39) {
				deck[i] = new Card(i % 13, Suit.HEART);
			}else {
				deck[i] = new Card(i % 13, Suit.DIAMOND);
			}
		}
	}
	/**
	 * Getter for the array field deck
	 * @return
	 */
	public Card[] getDeck() {
		return this.deck;
	}
	/**
	 * Shuffles the deck of playing cards randomly
	 * @param card A Card object
	 * @param n int value for the number of cards to be shuffled
	 */
	public static void shuffle(Card[] card, int n) {
		Random rand = new Random(); 
        
        for (int i = 0; i < n; i++) 
        { 
            // Random for remaining positions. 
            int r = i + rand.nextInt(52 - i); 
              
             //swapping the elements 
             Card temp = card[r]; 
             card[r] = card[i]; 
             card[i] = temp; 
               
        } 
	}
	
	private static boolean isFlush( Card[] h ) {
	      if ( h.length != 5 )
	         return(false);   // Make sure we have 5 cards....

	      sortBySuit(h);      // Sort the cards by the suit values

	      return( h[0].getSuit().equals(h[4].getSuit()) );   // All cards has same suit     
	}
	private static boolean isStraight( Card[] h ) {
	      int i, testRank;

	      if ( h.length != 5 )
	         return(false);

	      sortByValue(h);      // Sort the poker hand by the getRank of each card      

	      /* ===========================
	         Check if hand has an Ace
	         =========================== */
	      if (h[4].getRank() == Rank.ACE) {
	         /* =================================
	            Check straight using an Ace
	            ================================= */
	         boolean a = h[0].getRank() == Rank.TWO && h[1].getRank() == Rank.THREE &&
	                     h[2].getRank() == Rank.FOUR && h[3].getRank() == Rank.FIVE ;
	         boolean b = h[0].getRank() == Rank.TEN && h[1].getRank() == Rank.JACK &&        
	                     h[2].getRank() == Rank.QUEEN && h[3].getRank() == Rank.KING ;

	         return ( a || b );
	      }
	      else {
	         /* ===========================================
	            General case: check for increasing values
	            =========================================== */
	         testRank = h[0].getRank().ordinal() + 1;

	         for ( i = 1; i < 5; i++ ) {
	            if ( h[i].getRank().ordinal() != testRank )
	               return(false);        // Straight failed...

	            testRank++;   // Next card in hand
	         }

	         return(true);        // Straight found !
	      }
	}
	   
	private static void sortBySuit( Card[] h ) {
	      int i, j, min_j;

	      /* ---------------------------------------------------
	         The selection sort algorithm
	         --------------------------------------------------- */
	      for ( i = 0 ; i < h.length ; i ++ ) {
	         /* ---------------------------------------------------
	            Find array element with min. value among
	            h[i], h[i+1], ..., h[n-1]
	            --------------------------------------------------- */
	         min_j = i;   // Assume elem i (h[i]) is the minimum

	         for ( j = i+1 ; j < h.length ; j++ ) {
	            if ( h[j].getSuit().compareTo(h[min_j].getSuit()) < 0 ) {
	               min_j = j;    // We found a smaller suit value, update min_j     
	            }
	         }

	         /* ---------------------------------------------------
	            Swap a[i] and a[min_j]
	            --------------------------------------------------- */
	         Card help = h[i];
	         h[i] = h[min_j];
	         h[min_j] = help;
	      }
	   }
	/* ---------------------------------------------
	Sort hand by getRank:
	smallest suit card first ....
	(Finding a flush is eaiser that way)
	--------------------------------------------- */
	private static void sortByValue( Card[] h ) {
		int i, j, min_j;
		/* ---------------------------------------------------
		The selection sort algorithm
		--------------------------------------------------- */
		for ( i = 0 ; i < h.length ; i ++ ) {
			/* ---------------------------------------------------
			Find array element with min. value among
			h[i], h[i+1], ..., h[n-1]
			--------------------------------------------------- */
			min_j = i;
			// Assume elem i (h[i]) is the minimum
			for ( j = i+1 ; j < h.length ; j++ ) {
				if (h[j].getRank().compareTo(h[min_j].getRank()) < 0) {
					min_j = j;
					// We found a smaller getRank value, update min_j
				}
			}
			/* ---------------------------------------------------
			Swap a[i] and a[min_j]
			--------------------------------------------------- */
			Card help = h[i];
			h[i] = h[min_j];
			h[min_j] = help;
		}
	}
	private static boolean isStraightFlush( Card[] h) {
		return isStraight(h) && isFlush(h) && h[4].getRank() != Rank.ACE;
	}
	private static boolean isRoyalFlush(Card[] h) {
		return isStraight(h) && isFlush(h) && h[4].getRank() == Rank.ACE;
	}
	private static boolean isFourOfAKind( Card[] h ) {
		boolean a1, a2;
		if ( h.length != 5 )
			return(false);
		sortByValue(h);
		// Sort by getRank first
		/* ------------------------------------------------------
		Check for: 4 cards of the same getRank
		+ higher getRanked unmatched card
		------------------------------------------------------- */
		a1 = h[0].getRank() == h[1].getRank() &&
		h[1].getRank() == h[2].getRank() &&
		h[2].getRank() == h[3].getRank() ;
		/* ------------------------------------------------------
		Check for: lower getRanked unmatched card
		+ 4 cards of the same getRank
		------------------------------------------------------- */
		a2 = h[1].getRank() == h[2].getRank() &&
		h[2].getRank() == h[3].getRank() &&
		h[3].getRank() == h[4].getRank();
		return( a1 || a2 );
	}
	private static boolean isFullHouse( Card[] h ) {
		boolean a1, a2;
		if ( h.length != 5 )
			return(false);
		sortByValue(h);
		// Sort by getRank first
		/* ------------------------------------------------------
		Check for: x x x y y
		------------------------------------------------------- */
		a1 = h[0].getRank() == h[1].getRank() &&
		h[1].getRank() == h[2].getRank() &&
		h[3].getRank() == h[4].getRank();
		/* ------------------------------------------------------
		Check for: x x y y y
		------------------------------------------------------- */
		a2 = h[0].getRank() == h[1].getRank() &&
		h[2].getRank() == h[3].getRank() &&
		h[3].getRank() == h[4].getRank();
		return( a1 || a2 );
	}
	private static boolean isThreeOfAKind( Card[] h ) {
		boolean a1, a2, a3;
		if ( h.length != 5 )
			return(false);
		if ( isFourOfAKind(h) || isFullHouse(h) )
			return(false);
		// The hand is not 3 of a kind (but better)
		sortByValue(h);
		/* ------------------------------------------------------
		Check for: x x x a b
		------------------------------------------------------- */
		a1 = h[0].getRank() == h[1].getRank() &&
		h[1].getRank() == h[2].getRank() ;
		/* ------------------------------------------------------
		Check for: a x x x b
		------------------------------------------------------- */
		a2 = h[1].getRank() == h[2].getRank() &&
		h[2].getRank() == h[3].getRank() ;
		/* ------------------------------------------------------
		Check for: a b x x x
		------------------------------------------------------- */
		a3 = h[2].getRank() == h[3].getRank() &&
		h[3].getRank() == h[4].getRank();
		return( a1 || a2 || a3 );
	}
	private static boolean isTwoPair( Card[] h ) {
		boolean a1, a2, a3;
		if ( h.length != 5 )
			return(false);
		if ( isFourOfAKind(h) || isFullHouse(h) || isThreeOfAKind(h) )
			return(false);
		// The hand is not 2 pairs (but better)
		sortByValue(h);
		/* --------------------------------
		Checking: a a b b x
		-------------------------------- */
		a1 = h[0].getRank() == h[1].getRank() &&
		h[2].getRank() == h[3].getRank() ;
		/* ------------------------------
		Checking: a a x b b
		------------------------------ */
		a2 = h[0].getRank() == h[1].getRank() &&
		h[3].getRank() == h[4].getRank() ;
		/* ------------------------------
		Checking: x a a b b
		------------------------------ */
		a3 = h[1].getRank() == h[2].getRank() &&
		h[3].getRank() == h[4].getRank() ;
		return( a1 || a2 || a3 );
	}
	private static boolean isPair( Card[] h ) {
		boolean a1, a2, a3, a4;
		if ( h.length != 5 )
			return(false);
		if ( isFourOfAKind(h) || isFullHouse(h) || isThreeOfAKind(h) || isTwoPair(h) )
			return(false);
		// The hand is not one pair (but better)
		sortByValue(h);
		/* --------------------------------
		Checking: a a x y z
		-------------------------------- */
		a1 = h[0].getRank() == h[1].getRank() && h[1].getRank().compareTo(Rank.TEN) > 0;
		/* --------------------------------
		Checking: x a a y z
		-------------------------------- */
		a2 = h[1].getRank() == h[2].getRank() && h[2].getRank().compareTo(Rank.TEN) > 0;
		/* --------------------------------
		Checking: x y a a z
		-------------------------------- */
		a3 = h[2].getRank() == h[3].getRank() && h[3].getRank().compareTo(Rank.TEN) > 0;
		/* --------------------------------
		Checking: x y z a a
		-------------------------------- */
		a4 = h[3].getRank() == h[4].getRank() && h[4].getRank().compareTo(Rank.TEN) > 0;
		return( a1 || a2 || a3 || a4 );
	}
	public static String getWinnings(Card[] h, int w, int b) {
		String message = "";
		
		if(isRoyalFlush(h)) {
			message = "You made a Royal Flush and Won 60x your bet!";
			GUI.setBankRoll(w += b * 60);
		}else if(isStraightFlush(h)) {
			message = "You made a Straight Flush and Won 30x your bet!";
			GUI.setBankRoll(w += b * 30);
		}else if(isFourOfAKind(h)) {
			message = "You made Four of a Kind and Won 20x your bet!";
			GUI.setBankRoll(w += b * 20);
		}else if(isFullHouse(h)) {
			message = "You made a Full House and Won 15x your bet!";
			GUI.setBankRoll(w += b * 15);
		}else if(isFlush(h)) {
			message = "You made a Flush and Won 10x your bet!";
			GUI.setBankRoll(w += b * 10);
		}else if(isStraight(h)) {
			message = "You made a Straight and Won 7x your bet!";
			GUI.setBankRoll(w += b * 7);
		}else if(isThreeOfAKind(h)) {
			message = "You made Three of a Kind and Won 3x your bet!";
			GUI.setBankRoll(w += b * 3);
		}else if(isTwoPair(h)) {
			message = "You made Two Pair and Won 2x your bet!";
			GUI.setBankRoll(w += b * 2);
		}else if(isPair(h)) {
			message = "You made a Pair and Won 1x your bet!";
			GUI.setBankRoll(w += b);
		}else {
			message = "Sorry, You didn't win :(";
//			GUI.setBankRoll(w -= b);
		}
		return message;
	}
}