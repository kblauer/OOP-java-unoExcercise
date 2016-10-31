/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import model.Card;
import model.CardColor;
import model.CardNum;
import model.Hand;
import model.HtmlDoc;
import model.TextDoc;
import model.UnoGame;

/**
 * Controller is the class that handles all the connections 
 * between the gui and the model(Uno Game).  Because of this,
 * it is easy to see where and how the gui is changed, as well as 
 * ensuring the MVC architecture.  
 * <p>
 * Note that the controller is only created if the user decides to run 
 * the program with the gui, not with the command output.
 * 
 * @author Kyle Blauer
 * @version 1.0
 */
public class Controller {
    
    Hand hand;
    UnoGame game;
    
    int outputPref;
    HtmlDoc html;
    TextDoc text;
    
    /**
     * Creates a new controller object, by initializing the html document
     * as well as the text document.  The selection for which is written to
     * is chosen my the user.
     * 
     * @throws FileNotFoundException 
     * @see HtmlDoc
     * @see TextDoc
     */
    public Controller() throws FileNotFoundException {
        outputPref = 0;
        html = new HtmlDoc();
        text = new TextDoc();
    }
    
    /**
     * Creates a new UnoGame based on the preferences for the game chosen
     * by the user.  First it creates a new Game Object, which holds all the 
     * persistent data for the entire length of the game.  Then, a hand is 
     * created with seven cards and stored into the model.
     * 
     * 
     * @param numDecks      the number of decks chosen by the user
     * @param shufflePref   the preference for shuffling the deck(s), <code>0</code> representing
     *                      shuffling individually and <code>1</code> for together.
     * @param outputPref    the preference for output, either to an HTML document 
     *                      or plain text.  <code>0</code> represents HTML, while 
     *                      <code>1</code> is for text.
     * @param omit          an ArrayList of special cards to be omitted from the deck
     *                      if the user has chosen to do so.
     */
    public void newGame(int numDecks, int shufflePref, int outputPref, ArrayList<Card> omit) {
        game = new UnoGame(numDecks, omit, shufflePref);
        hand = new Hand(html, text, outputPref);
        
        nextHand();
    }
    
    /**
     * Creates a new hand object, retaining the preferences from the overall game.
     * Draws seven cards into the hand, and then prints the hand and adds
     * the appropriate workouts to the model
     */
    public void nextHand() {
        hand = new Hand(html, text, outputPref);
        hand.drawSeven(game);
        hand.sortHand();
        
        if (outputPref == 0) {html.printHand(hand);}
        else {text.printHand(hand);}
        
        if (outputPref == 0) {html.printActionHeader();}
        else {text.printActionHeader();}
        
        hand.parseHand(game);
        
        if (outputPref == 0) {html.printHandReps(hand, game);}
        else {text.printHandReps(hand, game);}
    }
    
    /**
     * Gets the workout reps for the current hand from 
     * the hand object.
     * 
     * @return ArrayList of Integers, contains the amounts
     *         of each type of workout for the hand
     */
    public ArrayList<Integer> getHandReps() {
        return hand.getHandReps();
    }
    
    /**
     * Gets the workout reps for the worst hand from 
     * the game object.
     * 
     * @return ArrayList of Integers, contains the amounts
     *         of each type of workout for the worst hand
     */
    public ArrayList<Integer> getWorstHand() {
        return game.getWorstHand();
    }
    
    /**
     * Gets the workout reps for the skipped exercises from 
     * the game object.
     * 
     * @return ArrayList of Integers, contains the amounts
     *         of each type of workout for the skipped exercises
     */
    public ArrayList<Integer> getSkipReps() {
        return game.getSkipReps();
    }
    
    /**
     * Gets the workout reps for the final total from 
     * the game object.
     * 
     * @return ArrayList of Integers, contains the amounts
     *         of each type of workout for the final total
     */
    public ArrayList<Integer> getFinalReps() {
        return game.getFinalReps();
    }
    
    /**
     * Determines which images to load for which cards in the hand,
     * and places them in an ArrayList.  This is determined via the 
     * card object, and a standard naming scheme for all the card .png
     * filenames.  i.e <code>unoCards/1-blue</code> is a Blue 1
     * 
     * @return ArrayList of ImageIcon , contains each Icon to be
     *         displayed for the cards in the hand.
     */
    public ArrayList<ImageIcon> getHandImages() {
        ArrayList<ImageIcon> imageList = new ArrayList<ImageIcon>();
        for (int index = 0; index<hand.getHand().size(); index++) {
            if (hand.getCard(index) != null) {
                ImageIcon image = getCardImage(hand.getCard(index));
                imageList.add(image);
            } else {
                ImageIcon image = new ImageIcon("unoCards/back.png");
                imageList.add(image);
            }
        }
        if (hand.getHand().size() < 7) {
            int lastIndex = hand.getHand().indexOf(hand.getHand().getLast());
            for (int index=lastIndex+1; index<7; index++) {
                ImageIcon image = new ImageIcon("unoCards/back.png");
                imageList.add(image);
            }
        }
        return imageList;
    }
    
    private ImageIcon getCardImage(Card card) {
        String fileName = new String();
        
        if (card.getNum() == CardNum.ZERO) {fileName = "unoCards/0-";}
        else if (card.getNum() == CardNum.ONE) {fileName = "unoCards/1-";}
        else if (card.getNum() == CardNum.TWO) {fileName = "unoCards/2-";}
        else if (card.getNum() == CardNum.THREE) {fileName = "unoCards/3-";}
        else if (card.getNum() == CardNum.FOUR) {fileName = "unoCards/4-";}
        else if (card.getNum() == CardNum.FIVE) {fileName = "unoCards/5-";}
        else if (card.getNum() == CardNum.SIX) {fileName = "unoCards/6-";}
        else if (card.getNum() == CardNum.SEVEN) {fileName = "unoCards/7-";}
        else if (card.getNum() == CardNum.EIGHT) {fileName = "unoCards/8-";}
        else if (card.getNum() == CardNum.NINE) {fileName = "unoCards/9-";}
        else if (card.getNum() == CardNum.DRAW_2) {fileName = "unoCards/d2-";}
        else if (card.getNum() == CardNum.REVERSE) {fileName = "unoCards/rev-";}
        else if (card.getNum() == CardNum.SKIP) {fileName = "unoCards/skp-";}
        else if (card.getNum() == CardNum.WILD) {fileName = "unoCards/jok.png";}
        else if (card.getNum() == CardNum.WILD_D_4) {fileName = "unoCards/+4.png";}
        
        if (card.getNum()!= CardNum.WILD && card.getNum() != CardNum.WILD_D_4) {
            if (card.getColor() == CardColor.BLUE) {fileName += "blue.png";}
            else if (card.getColor() == CardColor.RED) {fileName += "red.png";}
            else if (card.getColor() == CardColor.YELLOW) {fileName += "yellow.png";}
            else if (card.getColor() == CardColor.GREEN) {fileName += "green.png";}
        }
        
        ImageIcon cardIcon = new ImageIcon(fileName);
        return cardIcon;
    }
    
    /**
     * Returns <code>true</code> if the current game is over, 
     * i.e. if there are no more cards in the deck.  
     * 
     * @return <code>true</code> if the current game is over,
     *         <code>false</code> otherwise.
     */
    public boolean isGameOver() {
        boolean retVal = (hand.getHand().size() < 7);
        if (retVal) {
            if (outputPref == 0) {html.printTotalReps(game);}
            else {text.printTotalReps(game);}
             
            if (outputPref == 0) {html.endHtml();}
        }
        return retVal;
    }
}
