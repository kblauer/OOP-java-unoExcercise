/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * NumWorkoutPanel is the class that contains all the labels and ints
 * representing the workout stats for the current game. This includes:
 * <ul>
 *  <li>Current Hand Reps
 *  <li>Worst Hand Reps
 *  <li>Total skipped exercises 
 *  <li>Final total of exercises
 * </ul>
 * 
 * @author Kyle Blauer
 * @version 1.1
 */
public class NumWorkoutPanel extends JPanel {
    
    private JLabel sitNum;
    private JLabel pushNum;
    private JLabel squatNum;
    private JLabel lungeNum;
    private JLabel burpeeNum;
    
    private JLabel worstSitNum;
    private JLabel worstPushNum;
    private JLabel worstSquatNum;
    private JLabel worstLungeNum;
    private JLabel worstBurpeeNum;
    
    private JLabel skipSitNum;
    private JLabel skipPushNum;
    private JLabel skipSquatNum;
    private JLabel skipLungeNum;
    private JLabel skipBurpeeNum;
    
    private JLabel finalSitNum;
    private JLabel finalPushNum;
    private JLabel finalSquatNum;
    private JLabel finalLungeNum;
    private JLabel finalBurpeeNum;
    
    /**
     * Creates the NumWorkoutPanel by initializing labels and integers for the 
     * panel.  Positions the components within the panels as desired.
     * 
     */
    public NumWorkoutPanel() {
        setBorder(BorderFactory.createTitledBorder("Workout Info"));
        Dimension dim = new Dimension(500, 150);
        setPreferredSize(dim);
        
        // Create Swing components
        JLabel sitLabel = new JLabel("Sit Ups");
        JLabel pushLabel = new JLabel("Push Ups");
        JLabel squatLabel = new JLabel("Squats");
        JLabel lungeLabel = new JLabel("Lunges");
        JLabel burpeeLabel = new JLabel("Burpees");
        
        JLabel currentLabel = new JLabel("Current: ");
        JLabel totalSkipLabel = new JLabel("Total Skip: ");
        JLabel worstLabel = new JLabel("Worst Hand: ");
        JLabel finalLabel = new JLabel("Final Total: ");
        
        sitNum = new JLabel("0");
        pushNum = new JLabel("0");
        squatNum = new JLabel("0");
        lungeNum = new JLabel("0");
        burpeeNum = new JLabel("0");
        
        skipSitNum = new JLabel("0");
        skipPushNum = new JLabel("0");
        skipSquatNum = new JLabel("0");
        skipLungeNum = new JLabel("0");
        skipBurpeeNum = new JLabel("0");
        
        worstSitNum = new JLabel("0");
        worstPushNum = new JLabel("0");
        worstSquatNum = new JLabel("0");
        worstLungeNum = new JLabel("0");
        worstBurpeeNum = new JLabel("0");
        
        finalSitNum = new JLabel("0");
        finalPushNum = new JLabel("0");
        finalSquatNum = new JLabel("0");
        finalLungeNum = new JLabel("0");
        finalBurpeeNum = new JLabel("0");
        
        // Add components to layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        ///////// FIRST ROW /////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.gridx = 1; // Increment gridx on the first row, to make room for table labels
        add(sitLabel, gc);
        
        gc.gridx = 2;
        add(pushLabel, gc);
        
        gc.gridx = 3;
        add(squatLabel, gc);
        
        gc.gridx = 4;
        add(lungeLabel, gc);
        
        gc.gridx = 5;
        add(burpeeLabel, gc);
        
        
        ///////// SECOND ROW ////////////
        gc.gridy = 1;
        gc.gridx = 0;
        add(currentLabel, gc);
        
        gc.gridx = 1;
        add(sitNum, gc);
        
        gc.gridx = 2;
        add(pushNum, gc);
        
        gc.gridx = 3;
        add(squatNum, gc);
        
        gc.gridx = 4;
        add(lungeNum, gc);

        gc.gridx = 5;
        add(burpeeNum, gc);
        
        ////////// THIRD ROW //////////
        gc.gridy = 2;
        gc.gridx = 0;
        add(worstLabel, gc);
        
        gc.gridx = 1;
        add(worstSitNum, gc);
        
        gc.gridx = 2;
        add(worstPushNum, gc);
        
        gc.gridx = 3;
        add(worstSquatNum, gc);
        
        gc.gridx = 4;
        add(worstLungeNum, gc);

        gc.gridx = 5;
        add(worstBurpeeNum, gc);
        
        //// Separator ////
        gc.gridy = 3;
        gc.gridx = 0;
        gc.gridwidth = 6;
        gc.fill = GridBagConstraints.HORIZONTAL;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(5, 1));
        add(sep, gc);
        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        
        ////////// FOURTH ROW /////////
        gc.gridy = 4;
        gc.gridx = 0;
        add(totalSkipLabel, gc);
        
        gc.gridx = 1;
        add(skipSitNum, gc);
        
        gc.gridx = 2;
        add(skipPushNum, gc);
        
        gc.gridx = 3;
        add(skipSquatNum, gc);
        
        gc.gridx = 4;
        add(skipLungeNum, gc);

        gc.gridx = 5;
        add(skipBurpeeNum, gc);
        
        ////////// FIFTH ROW /////////
        gc.gridy = 5;
        gc.gridx = 0;
        add(finalLabel, gc);
        
        gc.gridx = 1;
        add(finalSitNum, gc);
        
        gc.gridx = 2;
        add(finalPushNum, gc);
        
        gc.gridx = 3;
        add(finalSquatNum, gc);
        
        gc.gridx = 4;
        add(finalLungeNum, gc);

        gc.gridx = 5;
        add(finalBurpeeNum, gc);
    }
    
    /**
     * Sets the display for the Current Hand Reps to the integers
     * inside the ArrayList of reps.  This allows the user to see how
     * many reps he/she should be doing.
     * 
     * @param reps ArrayList of reps for different exercises
     */
    public void setHandWorkout(ArrayList<Integer> reps) {
        sitNum.setText(String.valueOf(reps.get(0)));
        pushNum.setText(String.valueOf(reps.get(1)));
        squatNum.setText(String.valueOf(reps.get(2)));
        lungeNum.setText(String.valueOf(reps.get(3)));
        burpeeNum.setText(String.valueOf(reps.get(4)));
    }
    
    /**
     * Sets the display for the Worst Hand Reps to the integers
     * inside the ArrayList of reps.  This allows the user to see the worst
     * hand so far.
     * 
     * @param reps ArrayList of reps for different exercises in the worst hand
     */
    public void setWorstWorkout(ArrayList<Integer> reps) {
        worstSitNum.setText(String.valueOf(reps.get(0)));
        worstPushNum.setText(String.valueOf(reps.get(1)));
        worstSquatNum.setText(String.valueOf(reps.get(2)));
        worstLungeNum.setText(String.valueOf(reps.get(3)));
        worstBurpeeNum.setText(String.valueOf(reps.get(4)));
    }
    
    /**
     * Sets the display for the Skipped Reps to the integers
     * inside the ArrayList of reps.  This allows the user to see how
     * many reps he/she has skipped total in the game.
     * 
     * @param reps ArrayList of skipped reps for different exercises
     */
    public void setSkipWorkout(ArrayList<Integer> reps) {
        skipSitNum.setText(String.valueOf(reps.get(0)));
        skipPushNum.setText(String.valueOf(reps.get(1)));
        skipSquatNum.setText(String.valueOf(reps.get(2)));
        skipLungeNum.setText(String.valueOf(reps.get(3)));
        skipBurpeeNum.setText(String.valueOf(reps.get(4)));
    }
    
    /**
     * Sets the display for the Final total Reps to the integers
     * inside the ArrayList of reps.  This allows the user to see how
     * many reps he/she did during the entire game.
     * 
     * @param reps ArrayList of reps for different exercises
     */
    public void setFinalWorkout(ArrayList<Integer> reps) {
        finalSitNum.setText(String.valueOf(reps.get(0)));
        finalPushNum.setText(String.valueOf(reps.get(1)));
        finalSquatNum.setText(String.valueOf(reps.get(2)));
        finalLungeNum.setText(String.valueOf(reps.get(3)));
        finalBurpeeNum.setText(String.valueOf(reps.get(4)));
    }
}
