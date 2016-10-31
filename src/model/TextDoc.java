/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * TextDoc Class, contains all of the printing functions to the text doc 
 * Contains the printstream that handles the text
 * 
 * @author Kyle Blauer
 * @verision 1.1
 */
public class TextDoc {
    private final PrintStream out;
    
    /**
     * initializes the text file for output
     *
     * @throws java.io.FileNotFoundException
     */
    public TextDoc() throws FileNotFoundException {
        this.out = new PrintStream(new File("output.txt"));
    }
    
    /**
     * Prints the hand, one card at a time i.e HAND: Blue Skip, Blue 9, Yellow Wild, Yellow 8, Yellow 1, Red 8, Green Reverse,
     * 
     * @param hand The hand you want to print
     */
    public void printHand(Hand hand) {
        out.print("\nHAND: ");
        for (Card card: hand.getHand()) {
            if (!(card == null)) {
                out.print(card);
            }
        }
    }
    
    /**
     * Prints the beginning <code>\n ACTION CARDS:</code> tag for the action card section of each hand
     */
    public void printActionHeader() {
        out.print("\n     ACTION CARDS: ");
    }
    
    /**
     * Prints the description of each action card present in the hand, 
     * i.e 'Skip - All cards of this color discarded, 
     * Wild - Adds 4 Burpees, Reverse - All cards of color returned to deck'
     * 
     * @param action        The name of the action card that was just parsed
     * @param actionDesc    The description of the action card that was just parsed.
     */
    public void printAction(String action, String actionDesc) {
        out.print( action + " - " + actionDesc + ", ");
    }
    
    /**
     * Prints the reps required by each drawn hand and the remaining cards, 
     * i.e REPS: PushUps: 0, Squats: 9, SitUps: 8, Lunges: 0, Burpees: 4 , CARDS LEFT: 88
     * 
     * @param hand Hand object, contains info for number of reps in a parsed hand
     * @param game UnoGame object, contains the deck and therefore the number of cards left.
     */
    public void printHandReps(Hand hand, UnoGame game) {
        out.print("\n     REPS:");
        out.print("PushUps: " + hand._numPushUps + ", Squats: " + hand._numSquat + ", SitUps: " + hand._numSitUps + ", Lunges: " + hand._numLunges + ", Burpees: " + hand._numBurpees);
        out.print(", CARDS LEFT: " + (game.getNumCardsLeft()));
    }
    
    /**
     * Prints the total reps and skips for the entire game after the pile has been exhausted.
     * i.e ------- DECK TOTALS --------
     * Total PushUps: 128 Total Squats: 99 Total SitUps: 130 Total Lunges: 134 Total Burpees: 24
     * Skipped PushUps: 17 Skipped Squats: 0 Skipped SitUps: 0 Skipped Lunges: 1
     * 
     * @param game UnoGame object, contains the info for the total reps as well as the 
     */
    public void printTotalReps(UnoGame game) {
        out.println("\n------- DECK TOTALS --------");
        out.println("\nTotal PushUps: " + game.totalPush + " Total Squats: " + game.totalSquat + " Total SitUps: " + game.totalSit + " Total Lunges: " + game.totalLunge + " Total Burpees: " + game.totalBurpees);
        out.println("\nSkipped PushUps: " + game.skipPush + " Skipped Squats: " + game.skipSquat + " Skipped SitUps: " + game.skipSit + " Skipped Lunges: " + game.skipLunge);
        out.println("\nWorst PushUps: " + game.worstPush + " Worst Squats: " + game.worstSquat + "Worst SitUps: " + game.worstSit + " Worst Lunges: " + game.worstLunge + "Worst Burpees: " + game.worstBurpees);
    }
}


