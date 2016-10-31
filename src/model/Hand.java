/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Hand is the class that contains a user's hand of up to seven cards during the 
 * Uno Crossfit Game.  This hand is created each time a new hand is drawn. 
 * <p>
 * Also contains the info for how many reps to do for each exercise, corresponding 
 * to the cards in the hand. These are then added to the total within UnoGame
 * 
 * @author Kyle Blauer
 * @version 2.0
 * @see UnoGame
 */
public class Hand {
    private LinkedList<Card> _hand;
    protected int _numPushUps = 0, _numSquat = 0, _numSitUps = 0, _numLunges = 0, _numBurpees = 0;
    // Mult is used after the parsing of the hand, to apply the special card multipliers
    private int _pushMult=1, _squatMult=1, _sitMult=1, _lungeMult=1;
    private HtmlDoc _html = null;
    private TextDoc _text = null;
    private int _printPref = 0;
    
    /**
     * Creates the Hand object, by initializing a new LinkedList for the hand 
     * of seven Cards, as well as an output document as passed in to the constructor.
     * 
     * @param html        HtmlDoc used for printing specifics about the hand as it is parsed
     * @param text        TextDoc used for printing specifics about the hand as it is parsed
     * @param printPref   Whether to print to the Html or the text.
     */
    public Hand(HtmlDoc html, TextDoc text, int printPref) {
        _hand = new LinkedList<Card>();
        _html = html;
        _text = text;
        _printPref = printPref;
    }
    
    private void drawOne(UnoGame game) {
        if (_hand.size() <= 7) {
            Card tempCard = game.getNextCard();
            if (tempCard != null) {
                _hand.add(tempCard);
            }
        }
    }
    
    /**
     * Draws seven cards from the deck, within the given game object and places
     * them in the hand one after another.  If the deck is empty, the hand will be 
     * filled with <code>null</code> pointers.
     * 
     * @param game      Persistent game object, contains the deck from which to 
     *                  draw from.
     */
    public void drawSeven(UnoGame game) {
        drawOne(game);
        drawOne(game);
        drawOne(game);
        drawOne(game);
        drawOne(game);
        drawOne(game);
        drawOne(game);
    }
    
    /**
     * Returns the Card at the given index within the Hand.
     * 
     * @param index     index to get Card from within Hand
     * @return          Card at given index if it exists,
     *                  <code>null</code> otherwise.
     */
    public Card getCard(int index) {
        if (_hand.size() > 0) {
            return _hand.get(index);
        } return null;
    }
    
    /**
     * Sorts the Hand of Card objects, using the previously defined
     * natural order of cards (Color first, then number)
     */
    public void sortHand() {
        if (_hand.get(0) != null) {
            Collections.sort(_hand);
        }
    }
    
    /**
     * Steps through the hand and determines what each Card means in terms of 
     * workout reps.  Adds the correct number to this Hand , and then compares
     * this hand with the worst hand so far as well as adding the totals from this
     * hand to the overall totals within the given game object.
     * 
     * @param game  Contains the worst hand, total skips, and final totals of reps,
     *              used to add and compare this hand to the rest of the game.
     */
    public void parseHand(UnoGame game) {
        for (Card card: _hand) {
            if (card != null) {
                //Skip Card
                if (card.getNum().compareTo(CardNum.SKIP) == 0) {
                    parseSkip(card);
                //Draw 2
                } else if (card.getNum().compareTo(CardNum.DRAW_2) == 0) {
                    parseDrawTwo(card);
                //Reverse
                } else if (card.getNum().compareTo(CardNum.REVERSE) == 0) {
                    parseReverse(card, game);
                //Wild
                } else if (card.getNum().compareTo(CardNum.WILD) == 0) {
                    parseWild();
                // Wild D 4
                } else if (card.getNum().compareTo(CardNum.WILD_D_4) == 0){
                    parseDrawFour();
                // Number Card
                } else {
                    parseNumCard(card);
                }
            }
        }
        addSkips(game);
        
        _numLunges *= _lungeMult;
        _numPushUps *= _pushMult;
        _numSitUps *= _sitMult;
        _numSquat *= _squatMult;
        
        game.addToTotals(_numPushUps, _numSquat, _numSitUps, _numLunges, _numBurpees);
        game.compareWorst(_numPushUps, _numSquat, _numSitUps, _numLunges, _numBurpees);
    }
    
    private void parseSkip(Card card) {
        CardColor color = card.getColor();
        if (color == CardColor.RED) {_sitMult = 0;}
        else if (color == CardColor.GREEN) {_lungeMult = 0;}
        else if (color == CardColor.BLUE) {_pushMult = 0;}
        else if (color == CardColor.YELLOW) {_squatMult = 0;}
        
        if (_printPref == 0) _html.printAction("Skip", "All cards of this color discarded");
        else _text.printAction("Skip", "All cards of this color discarded");
    }
    
    private void parseDrawTwo(Card card) {
        CardColor color = card.getColor();
        if (color == CardColor.RED) {_sitMult *= 2;}
        else if (color == CardColor.GREEN) {_lungeMult *= 2;}
        else if (color == CardColor.BLUE) {_pushMult *= 2;}
        else if (color == CardColor.YELLOW) {_squatMult *= 2;}
        
        if (_printPref == 0) _html.printAction("Draw Two", "Total # of color *2");
        else _text.printAction("Draw Two", "Total # of color *2");
    }
    
    private void parseReverse(Card card, UnoGame game) {
        CardColor color = card.getColor();
        if (color == CardColor.RED) {_sitMult = 0;}
        else if (color == CardColor.GREEN) {_lungeMult = 0;}
        else if (color == CardColor.BLUE) {_pushMult = 0;}
        else if (color == CardColor.YELLOW) {_squatMult = 0;}
        
        if (_printPref == 0) _html.printAction("Reverse", "All cards of color returned to deck");
        else _text.printAction("Reverse", "All cards of color returned to deck");
        
        int currentIndex = _hand.indexOf(card);
        while(currentIndex < _hand.size()-1 && _hand.get(currentIndex).getColor().compareTo(_hand.get(currentIndex+1).getColor()) == 0) {
            currentIndex++;
            game.discardInPile(_hand.get(currentIndex));
        }
    }
    
    private void parseWild() {
        _numBurpees += 4;
        
        if (_printPref == 0) _html.printAction("Wild", "Adds 4 Burpees");
        else _text.printAction("Wild", "Adds 4 Burpees");
    }
    
    
    private void parseDrawFour() {
        _numBurpees += 4;
        
        _lungeMult *= 4;
        _pushMult *= 4;
        _sitMult *= 4;
        _squatMult *= 4;
        
        if (_printPref == 0) _html.printAction("Wild Draw 4", "Adds 4 burpees, Total # of all colors *4");
        else _text.printAction("Wild Draw 4", "Adds 4 burpees, Total # of all colors *4");
    }
    
    private void parseNumCard(Card card) {
        CardColor color = card.getColor();
        int numInt = card.getNum().ordinal();
        if (color == CardColor.RED) {_numSitUps += numInt;}
        else if (color == CardColor.GREEN) {_numLunges += numInt;}
        else if (color == CardColor.BLUE) {_numPushUps += numInt;}
        else if (color == CardColor.YELLOW) {_numSquat += numInt;}
    }
    
    private void addSkips(UnoGame game) {
        int numPushSkip = 0, numSquatSkip = 0, numSitSkip = 0, numLungeSkip = 0;
            
        if (_sitMult == 0) {numSitSkip = _numSitUps;}
        if (_pushMult == 0) {numPushSkip = _numPushUps;}
        if (_lungeMult == 0) {numLungeSkip = _numLunges;}
        if (_squatMult == 0) {numSquatSkip = _numSquat;}
        
        game.addSkips(numPushSkip, numSquatSkip, numSitSkip, numLungeSkip);
    }
    
    /**
     * Returns the LinkedList that represents the player's hand.
     * @return LinkedList of Cards, the player's current hand.
     */
    public LinkedList<Card> getHand() {
        return _hand;
    }
    
    /**
     * Returns an ArrayList of Integers containing the number of 
     * reps needed to complete this hand.  
     * 
     * @return ArrayList of reps needed to complete this hand.
     */
    public ArrayList<Integer> getHandReps() {
        ArrayList<Integer> handReps = new ArrayList<Integer>();
        handReps.add(Integer.valueOf(_numSitUps));
        handReps.add(Integer.valueOf(_numPushUps));
        handReps.add(Integer.valueOf(_numSquat));
        handReps.add(Integer.valueOf(_numLunges));
        handReps.add(Integer.valueOf(_numBurpees));
        
        return handReps;
    }
}
