/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package model;

import java.io.*;

/**
 * HtmlDoc Class, contains all of the printing functions to the html doc 
 * Contains the printstream that handles the html
 * 
 * @author Kyle Blauer
 * @verision 1.1
 */
public class HtmlDoc {
    private final PrintStream out;
    
    /**
     * initializes the html file and adds the <html>,<head>,<title>, and <body> tags to start the document
     *
     * @throws java.io.FileNotFoundException
     */
    public HtmlDoc() throws FileNotFoundException{
        this.out = new PrintStream(new File("output.html"));
        this.startHtml();
    }
    
    /**
     * Adds the <html>,<head>,<title>, and <body> tags to start the document
     */
    private void startHtml() {
        out.println("<html>");
        out.println("   <head><title>CS 2365 Project 3 Output</title></head>");
        out.println("       <body>");
    }
    
    /**
     * Adds the ending body and html tags to finish the html doc, as well as closing the printStream object
     */
    public void endHtml() {
        out.println("       </body>");
        out.println("</html>");
        out.close();
    }
    
    /**
     * Prints the hand, one card at a time i.e HAND: Blue Skip, Blue 9, Yellow Wild, Yellow 8, Yellow 1, Red 8, Green Reverse,
     * 
     * @param hand The hand you want to print
     */
    public void printHand(Hand hand) {
        out.println("<p>HAND: ");
        for (Card card: hand.getHand()) {
            if (!(card == null)) {
                out.println(card);
            }
        }
        out.println("</p>");
    }
    
    /**
     * Prints the beginning <p> tag for the action card section of each hand
     */
    public void printActionHeader() {
        out.println("<p>&ensp;&ensp;ACTION CARDS: ");
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
        out.print(action + " - " + actionDesc + ", ");
    }
    
    /**
     * Prints the reps required by each drawn hand and the remaining cards, 
     * i.e REPS: PushUps: 0, Squats: 9, SitUps: 8, Lunges: 0, Burpees: 4 , CARDS LEFT: 88
     * 
     * @param hand Hand object, contains info for number of reps in a parsed hand
     * @param game UnoGame object, contains the deck and therefore the number of cards left.
     */
    public void printHandReps(Hand hand, UnoGame game) {
        out.println("<p>&ensp;&ensp;REPS: ");
        out.println("PushUps: " + hand._numPushUps + ", Squats: " + hand._numSquat + ", SitUps: " + hand._numSitUps + ", Lunges: " + hand._numLunges + ", Burpees: " + hand._numBurpees);
        out.println(", CARDS LEFT: " + (game.getNumCardsLeft()));
        out.println("</p>");
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
        out.println("<p></p><p>------- DECK TOTALS --------</p>");
        out.println("<p>Total PushUps: " + game.totalPush + " Total Squats: " + game.totalSquat + " Total SitUps: " + game.totalSit + " Total Lunges: " + game.totalLunge + " Total Burpees: " + game.totalBurpees);
        out.println("</p><p>Skipped PushUps: " + game.skipPush + " Skipped Squats: " + game.skipSquat + " Skipped SitUps: " + game.skipSit + " Skipped Lunges: " + game.skipLunge + "</p>");
        out.println("<p>Worst PushUps: " + game.worstPush + " Worst Squats: " + game.worstSquat + "Worst SitUps: " + game.worstSit + " Worst Lunges: " + game.worstLunge + "Worst Burpees: " + game.worstBurpees + "</p>");
    }
}
