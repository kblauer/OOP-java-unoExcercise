/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */
package gui;

import controller.Controller;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Card;

/**
 * MainFrame is the class that acts as a container for all the swing components
 * in this gui.  Within MainFrame, several JPanels are created (i.e. CardDisplayPanel)
 * and placed appropriately on the screen. This class also creates the
 * listeners for each of these panels, and acts as a router or secondary control to the 
 * Controller class.
 * 
 * @author Kyle Blauer
 * @version 1.1
 */
public class MainFrame extends JFrame {
    
    JButton nextHand;
    Controller controller;
    NumWorkoutPanel numPanel;
    CardDisplayPanel cardDisplay;
    
    /**
     * Creates the MainFrame, containing all of the swing components 
     * of the gui, as well as the controller which acts as a router between
     * gui and model (Uno Crossfit) code. 
     * 
     * @throws FileNotFoundException Since the controller creates the HTML and 
     *                               text documents
     */
    public MainFrame() throws FileNotFoundException {
        super("Uno Crossfit");
        
        controller = new Controller();

        //// Set Layout manager
        setLayout(new GridBagLayout());
        
        layoutFrame();
        
        /// Create and show window
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void layoutFrame() {
        // Create Swing Components
        cardDisplay = new CardDisplayPanel();
        NewGamePanel newGamePanel = new NewGamePanel();
        numPanel = new NumWorkoutPanel();
        
        nextHand = new JButton("Next Hand");
        nextHand.setPreferredSize(new Dimension(100, 50));
        nextHand.setEnabled(false);
        
        // Add listeners
        nextHand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.nextHand();
                updateGui();
            }
        });
        
        newGamePanel.setNewGameListener(new NewGameListener() {
            @Override
            public void newGame(int numDecks, int shufflePref, int outputPref, ArrayList<Card> omit) {
                //try {
                    controller.newGame(numDecks, shufflePref, outputPref, omit);
                    nextHand.setEnabled(true);
                    updateGui();
                //}
            }
        });
        

        // Add components to frame
        GridBagConstraints gc = new GridBagConstraints();
        ///////////// FIRST ROW //////////////
        // Contains New Game Panel and Worst Hand panel
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(newGamePanel, gc);
        
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(nextHand, gc);
        
        ////////////// SECOND ROW /////////////////
        //Contains card display
        gc.gridy = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(cardDisplay, gc);
        
        ///////////// THIRD ROW ///////////////
        //Contains NumInfoPanel
        gc.gridy = 2;
        gc.weighty = 1;
        add(numPanel, gc);
    }
    
    private void updateGui() {
        ArrayList<ImageIcon> images = controller.getHandImages();
        cardDisplay.setCardImages(images);
        
        ArrayList<Integer> currentReps = controller.getHandReps();
        ArrayList<Integer> worstReps = controller.getWorstHand();
        ArrayList<Integer> skipReps = controller.getSkipReps();
        ArrayList<Integer> finalReps = controller.getFinalReps();
        
        numPanel.setHandWorkout(currentReps);
        numPanel.setWorstWorkout(worstReps);
        numPanel.setSkipWorkout(skipReps);
        numPanel.setFinalWorkout(finalReps);
        
        if (controller.isGameOver()) {
            nextHand.setEnabled(false);
        }
    }



}
