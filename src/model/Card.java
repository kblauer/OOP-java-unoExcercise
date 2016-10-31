/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */
package model;

/**
 * Card is the class that contains all the information about a single, 
 * specific card.
 * <p>
 * This includes the card's Number and color, or in the case of special cards 
 * the special name and associated color.
 * 
 * @author Kyle Blauer
 * @version 2.0
 */
public class Card implements Comparable<Card> {

    private final CardNum _cardNum;
    private final CardColor _cardColor;
    
    
    /**
     * Creates a card object by initializing the card's number and 
     * color to the given arguments.  Arguments must be of type enum, either 
     * CardNum or CardColor.  
     * 
     * @param num       The number or special event associated with this card. 
     *                  Is of type CardNum (i.e. CardNum.THREE).
     * @param color     The color associated with this card.
     *                  Is of type CardColor (i.e. CardColor.BLUE).
     * @see CardNum     enum
     * @see CardColor   enum
     */
    public Card(CardNum num, CardColor color) {
        _cardNum = num;
        _cardColor = color;
    }
    
    /**
     * Returns the number or special event associated with this card.
     * 
     * @return  CardNum associated with this card, i.e. CardNum.DRAW_2
     */
    public CardNum getNum() {
        return _cardNum;
    }
    
    /**
     * Returns the color associated with this card.
     * 
     * @return CardColor associated with this card, i.e. CardColor.GREEN
     */
    public CardColor getColor() {
        return _cardColor;
    }
    
    /**
     * Implementation of the compareTo method within the comparable interface.
     * <p>
     * Allows for Cards to be given a 'natural order' and be sorted within the 
     * Java Collections.
     * 
     * @param card card to compare this one with
     * @return     <code>-1</code> if this card is larger than the parameter (should be sorted after)
     *             <code>0</code> if the two cards are the same
     *             <code>1</code> if this card is smaller than the parameter
     */
    @Override
    public int compareTo(Card card) {
        if (card == null) {
            return 1;
        }
        // Compare Color first
        if (_cardColor.compareTo(card.getColor()) > 0) {
            return -1;
        } else if (_cardColor.compareTo(card.getColor()) < 0) {
            return 1;
        } else {

            // Now compare Number/Face
            if (_cardNum.compareTo(card.getNum()) > 0) {
                return -1;
            } else if (_cardNum.compareTo(card.getNum()) < 0) {
                return 1;
            }

        }
        return 0;
    }
    
    /**
     * Overrides the toString() method of Object, allowing for cards to be printed
     * directly by calling this method.
     * 
     * @return String the representation of this card object in a String. 
     */
    @Override
    public String toString() {
        return _cardNum + " " + _cardColor + ", ";
    }
    
    /**
     * Overrides the equals(Object obj) method, allowing for cards to be checked
     * equality based on color and number.
     * 
     * @param obj the Card to compare this one with to check for equality
     * @return <code>true</code> if the two card objects have the same color and number
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Card)) return false;
        Card card = (Card)obj;
        return (_cardColor.compareTo(card.getColor()) == 0) && (_cardNum.compareTo(card.getNum()) == 0);
    }

}
