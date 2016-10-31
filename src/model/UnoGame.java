/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * UnoGame class contains all member variables that need to stay 
 * constant throughout the game (deck, discard, total reps and skips)
 * 
 * @author Kyle
 * @version 2.0
 */
public class UnoGame {
    private Deck _deck = null;
    
    
    protected int skipPush=0, skipSquat=0, skipSit=0, skipLunge=0;
    protected int totalPush=0, totalSquat=0, totalSit=0, totalLunge=0, totalBurpees=0;
    protected int worstPush=0, worstSquat=0, worstSit=0, worstLunge=0, worstBurpees=0;
    
    /**
     * creates the UnoGame object by initializing the Deck within the class. 
     * See Deck for info about it's constructor.
     * 
     * @param numDecks      The number of decks as chosen by the user
     * @param omitCards     An ArrayList of cards to omit from the deck
     * @param shufflePref   Whether to shuffle the deck separately or individually,
     *                      as chosen by the user.
     * @see Deck
     */
    public UnoGame(int numDecks, ArrayList<Card> omitCards, int shufflePref) {
        _deck = new Deck(numDecks, omitCards, shufflePref);
    }
    
    /**
     * Returns the next card on the Deck if it exists.
     * 
     * @return  The top card on the deck, after it has been removed.
     */
    public Card getNextCard() {
        Card retVal = null;
        if (!_deck.isEmpty()) {
            retVal = _deck.getNextCard();
        }
        return retVal;
    }
    
    /**
     * Shuffles the LinkedList in the Deck object, as according to the user's 
     * preference within the Deck creation.
     */
    public void shuffleDeck() {
        _deck.shuffleDeck();
    }
    
    /**
     * Adds the given Card to the back of the deck, increases size of deck 
     * by one.
     * @param card  Card to add to the back of the deck
     */
    public void discardInPile(Card card) {
        _deck.addToDeck(card);
    }
    
    /**
     * Adds the given number of reps for each exercise to the totals within
     * the UnoGame object.
     * 
     * @param numPushUp     number of Push-Ups to add
     * @param numSquat      number of Squats to add
     * @param numSitUp      number of Sit-ups to add
     * @param numLunge      number of lunges to add
     * @param numBurpees    number of burpees to add
     */
    public void addToTotals(int numPushUp, int numSquat, int numSitUp, int numLunge, int numBurpees) {
         totalPush += numPushUp;
         totalSquat += numSquat;
         totalSit += numSitUp;
         totalLunge += numLunge;
         totalBurpees += numBurpees;
    }
    
    /**
     * Adds the given number of reps for each exercise to the total number skipped within
     * the UnoGame object.
     * 
     * @param numPushUp     number of Push-Ups skipped
     * @param numSquat      number of Squats skipped
     * @param numSitUp      number of Sit-ups skipped
     * @param numLunge      number of lunges skipped
     */
    public void addSkips(int numPushUp, int numSquat, int numSitUp, int numLunge) {
         skipPush += numPushUp;
         skipSquat += numSquat;
         skipSit += numSitUp;
         skipLunge += numLunge;
    }
    
    /**
     * Compares the current worst hand with the current hand to see if the current
     * is worse. If so, then replace the current worst with the given arguments.
     * 
     * @param numPushUp     number of Push-Ups to add
     * @param numSquat      number of Squats to add
     * @param numSitUp      number of Sit-ups to add
     * @param numLunge      number of lunges to add
     * @param numBurpees    number of burpees to add
     */
    public void compareWorst(int numPushUp, int numSquat, int numSitUp, int numLunge, int numBurpees) {
        if((numPushUp + numSquat + numSitUp + numLunge + numBurpees) > getWorstSize()) {
            worstPush = numPushUp;
            worstSquat= numSquat;
            worstSit= numSitUp;
            worstLunge = numLunge;
            worstBurpees = numBurpees;
        }
    }
    
    private int getWorstSize() {
        return worstPush + worstSquat + worstSit + worstLunge + worstBurpees;
    }
    
    /**
     * Returns the LinkedList representing the Deck of cards.
     * @return LinkedList of Card Objects
     */
    public LinkedList<Card> getDeck() {
        return _deck.getDeck();
    }
    
    /**
     * Returns the number of cards left in the deck, or the size of the 
     * LinkedList.
     * 
     * @return Size of the Deck, number of Card elements within it
     */
    public int getNumCardsLeft() {
        return _deck.getDeck().size();
    }
    
    /**
     * Creates an ArrayList with the info for the worst hand, in order to 
     * display it to the gui.
     * 
     * @return ArrayList of Integer with the info for number of reps in the worst hand.
     */
    public ArrayList<Integer> getWorstHand() {
        ArrayList<Integer> handReps = new ArrayList<Integer>();
        handReps.add(Integer.valueOf(worstSit));
        handReps.add(Integer.valueOf(worstPush));
        handReps.add(Integer.valueOf(worstSquat));
        handReps.add(Integer.valueOf(worstLunge));
        handReps.add(Integer.valueOf(worstBurpees));
        
        return handReps;
    }
    
    /**
     * Creates an ArrayList with the info for the total skips, in order to 
     * display it to the gui.
     * 
     * @return ArrayList of Integer with the info for number of reps skipped.
     */
    public ArrayList<Integer> getSkipReps() {
        ArrayList<Integer> handReps = new ArrayList<Integer>();
        handReps.add(Integer.valueOf(skipSit));
        handReps.add(Integer.valueOf(skipPush));
        handReps.add(Integer.valueOf(skipSquat));
        handReps.add(Integer.valueOf(skipLunge));
        handReps.add(Integer.valueOf(0));
        
        return handReps;
    }
    
    /**
     * Creates an ArrayList with the info for the Final total, in order to 
     * display it to the gui.
     * 
     * @return ArrayList of Integer with the info for number of reps in the final total.
     */
    public ArrayList<Integer> getFinalReps() {
        ArrayList<Integer> handReps = new ArrayList<Integer>();
        handReps.add(Integer.valueOf(totalSit));
        handReps.add(Integer.valueOf(totalPush));
        handReps.add(Integer.valueOf(totalSquat));
        handReps.add(Integer.valueOf(totalLunge));
        handReps.add(Integer.valueOf(totalBurpees));
        
        return handReps;
    }
}
