/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package gui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import model.Card;
import model.CardColor;
import model.CardNum;
import model.Hand;
import model.HtmlDoc;
import model.TextDoc;
import model.UnoGame;

/**
 * Project3 is the class that handles all the user input via console.
 * This includes the initial choice of gui or command output, 
 * as well as the options for the command output.
 * <p>
 * By Extension, this class also handles running the gui on a dedicated 
 * swing thread, as advised.
 * <p>
 * Lastly, Project3 contains all the test cases for the model of the 
 * Uno Crossfit Game.
 * 
 * @author Kyle Blauer
 * @version 1.1
 */
public class Project3 {
    private static int numDecks = 1;
    private static int shufflePref = 1;
    
    /**
     * Gets the user input for the gui or command output 
     * choice.  Allows the application to run in either 
     * way.
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException if the output file cannot be 
     *         opened and written to
     */
    public static void main(String[] args) throws FileNotFoundException {
        boolean inputOutcome = false;
        int guiPref = 0;
        
        do {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter 1 to use GUI, or 0 otherwise");
            try {
                guiPref = keyboard.nextInt();
                if(guiPref != 0 && guiPref != 1) {
                    throw new InputMismatchException();
                }
                inputOutcome = true;
            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter either 1 or 0");
            }
            
        } while (!inputOutcome);
        
        if(guiPref == 1) {
            createAndRunGUI();
        } else {
            runCMD();
        }
    }
    
    private static void createAndRunGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Project3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private static void runCMD() throws FileNotFoundException {
        // Ask user for input on number of decks and whether to shuffle individually or together
        boolean inputOutcome = false;
        
        // USER INPUT
        do {
            Scanner keyboard1 = new Scanner(System.in);
            Scanner keyboard2 = new Scanner(System.in);
            // Try to get input for number of decks, display error and loop if user entered an invalid int
            try {
                System.out.print("Please enter the number of decks you want to use (1-3): ");
                numDecks = keyboard1.nextInt();
                if (numDecks > 3) {
                    throw new Exception("Num Too Large");
                }
                inputOutcome = true;
            //Display error message if user entereed invalid data
            } catch (Exception e) {
                System.out.println("Error! Must enter an Integer between 1-3 inclusive");
                inputOutcome = false;
            }
            // Try to get input for shuffle preference, 0 for together and 1 for individual.
            try {
                System.out.print("\nPlease enter 0 to shuffle decks individually, or 1 to shuffle together: ");
                shufflePref = keyboard2.nextInt();
                System.out.print("\n");
                if (shufflePref != 0 && shufflePref != 1) {
                    throw new Exception("Not 0 or 1");
                }
                inputOutcome = true;
            // Display error if user entered something other than 0 or 1
            } catch (Exception e) {
                System.out.println("Error! Must be either 0 or 1");
                inputOutcome = false;
            }
        } while(inputOutcome == false);
        
        //Create game objects as needed for Uno Crossfit
        UnoGame game = new UnoGame(numDecks, new ArrayList<Card>(), shufflePref);
            //Shuffle the deck, with preference as enterd by user implemented by UnoGame object
        game.shuffleDeck();
        //Create the html document
        HtmlDoc html = new HtmlDoc();
        TextDoc text = new TextDoc();
        Hand hand = new Hand(html, text, 0);
        //Draws the initial seven cards into the hand from the shuffled pile
        hand.drawSeven(game);
        
        do {
            // Sort the hand, by color then rank
            hand.sortHand();
            // Print the hand after sorting
            html.printHand(hand);
            // Action Header needed to begin printing the <p> for all the action cards currently in the hand
            html.printActionHeader();
            // Parses the hand and applies all additions and * as neccesary
            hand.parseHand(game);
            
            // Prints the number of reps for each excercise as determined by parseHand()
            html.printHandReps(hand, game);
            
            //Creates a new empty hand object for the next draw of the game
            hand = new Hand (html, text, 0);
            hand.drawSeven(game);
        // Game is over when the user no longer has any cards in his hand
        } while(hand.getCard(0) != null);
        
        // Prints the total reps for each excercise after the game has been played
        html.printTotalReps(game);
        html.endHtml();
    }
    
    //All tests for the Uno Crossfit game
    private static int testSuite() throws FileNotFoundException {
        //Create dummy hand with specific cards
        Card y6 = new Card(CardNum.SIX, CardColor.YELLOW);
        Card gR = new Card(CardNum.REVERSE, CardColor.GREEN);
        Card bW4 = new Card(CardNum.WILD_D_4, CardColor.BLUE);
        Card bD2 = new Card(CardNum.DRAW_2, CardColor.BLUE);
        Card gD2 = new Card(CardNum.DRAW_2, CardColor.GREEN);
        Card yW = new Card(CardNum.WILD, CardColor.YELLOW);
        Card y1 = new Card(CardNum.ONE, CardColor.YELLOW);
        HtmlDoc foo = new HtmlDoc();
        TextDoc bar = new TextDoc();
        Hand hand = new Hand(foo, bar, 1);
        Hand handEmpty = new Hand(foo, bar, 1); //Empty hand
        hand.getHand().add(y6);
        hand.getHand().add(gR);
        hand.getHand().add(bW4);
        hand.getHand().add(bD2);
        hand.getHand().add(gD2);
        hand.getHand().add(yW);
        hand.getHand().add(y1);
        
        // ---------- CARD TESTS -------------
        boolean testGetFaceString = hand.getCard(0).getNum().equals(CardNum.SIX);
        boolean testGetColorString = hand.getCard(0).getColor().equals(CardColor.YELLOW);
        
        // ---------- DECK TESTS -------------
        ArrayList<Card> fooList = new ArrayList<Card>();
        UnoGame testGame1 = new UnoGame(1, fooList, 1); // 1 deck
        UnoGame testGame2 = new UnoGame(2,fooList, 0); //2 decks, shuffle together
        UnoGame testGame3 = new UnoGame(3,fooList, 1); //3 decks, shuffled independently
        UnoGame testGame4 = new UnoGame(1,fooList,1); //Leave unshuffled
        testGame1.shuffleDeck();
        testGame2.shuffleDeck();
        testGame3.shuffleDeck();
        // aldo tests shuffleDeck in UnoGame
        boolean testShuffleDeck1 = testGame1.getNextCard().getColor() != null;
        boolean testShuffleDeck2 = testGame2.getNextCard().getColor() != null;
        boolean testShuffleDeck3 = testGame3.getNextCard().getColor() != null;
        
        //boolean TestGetCard = testGame4.deck.getCard(0).getColor() == 0;
        //Also tests getNextCard in UnoGame
        //boolean testGetNextCard = testGame4.deck.getNextCard().getColor() == 0;
        //boolean testGetDeckLocation = testGame4.deck.getDeckLocation() == 1;
        
        /*
        // ---------- HAND TESTS ------------
        testGame4 = new UnoGame(1,1); //Reinitialize unshuffled
        handEmpty.drawOne(testGame4);
        handEmpty.hand[1] = new Card(12, 0);
        handEmpty.hand[2] = new Card(25, 0);
        boolean testDrawOne = handEmpty.hand[0].getColor() == 0;
        boolean testGetCard = hand.getCard(0).getColor() == 1;
        //contains both sortHandColor and sortHandFace
        hand.sortHand();
        boolean testSortHand = hand.getCard(0).getColor() == 0 && hand.getCard(0).getFace()== 23;
        handEmpty.parseHand(testGame4);
        boolean testParseHand = testGame4.totalPush == 0;
        //also test UnoGame.addSkips
        boolean testAddSkips = testGame4.skipPush == 6;
        
        // --------- UNO GAME TESTS ----------
        testGame4.discardInPile(y1);
        //tests discardPile addToDiscardPile as well
        boolean testDiscardInPile = testGame4.discardPile.pile[0].getColor() == 1;
        boolean testAddToTotals = testGame4.totalPush == 0;
        boolean testDiscardLength = testGame4.getDiscardLength() == 1;
        
        // --------- DISCARD PILE TESTS --------
        Card tempDraw = testGame4.discardPile.drawFromDiscardPile();
        boolean testDrawFromDiscardPile = tempDraw.getColor() == 1;
        boolean testIsEmpty = testGame4.discardPile.isEmpty() == true;
        boolean testGetLength = testGame4.discardPile.getLength() == 0;
        */
        
        return 1;
    }
}
