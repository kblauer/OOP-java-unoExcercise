/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package gui;

import java.util.ArrayList;
import model.Card;

/**
 * The NewGameListener interface acts as a custom ActionListener for the entire
 * NewGame panel which contains all the user's preferences for starting a new 
 * game.  This allows for all the data in the panel to be sent on a single button
 * click.
 * 
 * @author Kyle Blauer
 */
public interface NewGameListener {
    public void newGame(int numDecks, int shufflePref, int outputPref, ArrayList<Card> omit);
}
