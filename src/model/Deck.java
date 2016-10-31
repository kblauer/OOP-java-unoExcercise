/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Deck is the class that contains a LinkedList of cards, representing a 
 * deck of cards.  The deck can be shuffled, drawn from, and have new cards 
 * placed in the back.
 * <p>
 * The deck can contain from 1-3 uno decks, and they can be shuffled either 
 * individually or togeher. This is decided upon instantiation, as a preference
 * from the user.
 * 
 * @author Kyle Blauer
 * @version 2.0
 */
public class Deck {
    private LinkedList<Card> _deck;
    private final int _numDecks;
    private final int _numCards;
    private final int _shufflePref;
    
    /**
     * Creates a Deck object that contains 1-3 uno decks of Card s.  The provided 
     * arguments decide this as well as any special cards to be omitted, and 
     * whether to shuffle these decks individually or separately within the Deck object.
     * 
     * @param numDecks      The number of decks as chosen by the user
     * @param omitCards     An ArrayList of cards to omit from the deck
     * @param shufflePref   Whether to shuffle the deck separately or individually,
     *                      as chosen by the user.
     */
    public Deck(int numDecks, ArrayList<Card> omitCards, int shufflePref) {
        _deck = new LinkedList<Card>();
        _numDecks = numDecks;
        _shufflePref = shufflePref;
        
        for (int numDeckIndex = 1; numDeckIndex<= _numDecks; numDeckIndex++) {
            // Card number or special loop
            for (CardNum num: CardNum.values()) {
                // Color loop
                for (CardColor color: CardColor.values()) {
                    Card tempCard = new Card(num, color);
                    _deck.add(tempCard);
                    // If the card is not a zero or a wild, we want to add it twice
                    if (!isZeroWild(tempCard)) {
                        _deck.add(tempCard);
                    }
                }
            }
        }
        
        // Now, remove all of the cards in the deck that the user has opted to omit.
        _deck.removeAll(omitCards);
        _numCards = _deck.size();
        shuffleDeck();
    }
    
    //Returns true if the given card is either a zero or a wild
    //Used to determine if a card should not be placed in the deck twice
    private boolean isZeroWild(Card card) {
        return (card.getNum().compareTo(CardNum.ZERO) == 0) | 
               (card.getNum().compareTo(CardNum.WILD) == 0) | 
               (card.getNum().compareTo(CardNum.WILD_D_4) == 0);
    }
    
    /**
     * Shuffles the LinkedList in the Deck object, as according to the user's 
     * preference within the Deck creation.
     */
    public void shuffleDeck() {
        if (_shufflePref == 0) {indivShuffle();}
        else {shuffleAllDecks();}
    }
    
    private void shuffleAllDecks() {
        Collections.shuffle(_deck);
    }
    
    private void indivShuffle() {
        if(_numDecks == 1) {
            Collections.shuffle(_deck);
        } else if (_numDecks == 2) {
            int numSep = (int)_numCards/_numDecks;
            List<Card> tempList1 = new LinkedList<Card>(_deck.subList(0, numSep));
            List<Card> tempList2 = new LinkedList<Card>(_deck.subList(numSep, _numCards-1));
            Collections.shuffle(tempList1);
            Collections.shuffle(tempList2);
            
            _deck.clear();
            
            _deck.addAll(tempList1);
            _deck.addAll(tempList2);
        } else {
            int numSep = (int)_numCards/_numDecks;
            List<Card> tempList1 = new LinkedList<Card>(_deck.subList(0, numSep));
            List<Card> tempList2 = new LinkedList<Card>(_deck.subList(numSep, numSep*2));
            List<Card> tempList3 = new LinkedList<Card>(_deck.subList((numSep*2), _numCards-1));
            Collections.shuffle(tempList1);
            Collections.shuffle(tempList2);
            Collections.shuffle(tempList3);
            
            _deck.clear();
            _deck.addAll(tempList1);
            _deck.addAll(tempList2);
            _deck.addAll(tempList3);
        }
    }
    
    /**
     * Returns the Card at the given index
     * @param index   index to retrieve the Card from
     * @return        Card at the given index if not <code>null</code>, 
     *                <code>null</code> otherwise.
     */
    public Card getCard(int index) {
        return _deck.get(index);
    }
    
    /**
     * Pops the next Card off the top of the Deck and returns it
     * @return  The top card on the deck, after it has been removed.
     */
    public Card getNextCard() {
        return _deck.pop();
    }
    
    /**
     * Returns the LinkedList representing the Deck of cards.
     * @return LinkedList of Card Objects
     */
    public LinkedList<Card> getDeck() {
        return _deck;
    }
    
    /**
     * Adds the given Card to the back of the deck, increases size of deck 
     * by one.
     * @param card  Card to add to the back of the deck
     */
    public void addToDeck(Card card) {
        if(card != null) {
            _deck.add(card);
        }
    }
    
    /**
     * Checks if the deck is empty, i.e. no Cards in the LinkedList.
     * @return <code>true</code> if the Deck is empty,
     *         <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return _deck.isEmpty();
    }
    
}
