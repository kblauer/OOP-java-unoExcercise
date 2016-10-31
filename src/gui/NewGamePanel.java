/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Card;
import model.CardColor;
import model.CardNum;

/**
 * NewGamePanel is the class that contains all the gui components for the 
 * display of user settings when creating a new game.  The settings are as follows:
 * <ul>
 *  <li>Number of Decks to use
 *  <li>Shuffling individually or together
 *  <li>Outputting to HTML or text
 *  <li>Omitting special cards
 * </ul>
 * <p>
 * The class also implements ActionListener in order to send these preferences
 * to the controller via a button press.
 * 
 * @author Kyle Blauer
 * @version 1.1
 */
public class NewGamePanel extends JPanel implements ActionListener {
    private JComboBox numDecks;
    private JComboBox shufflePref;
    private JComboBox outputPref;
    
    private JCheckBox draw2Check;
    private JCheckBox skipCheck;
    private JCheckBox revCheck;
    private JCheckBox wildCheck;
    private JCheckBox wild4Check;
    
    private JButton newGameButton;
    private NewGameListener newGameListener;
    
    /**
     * Creates the NewGamePanel by initializing and positioning all the
     * swing elements in the panel. 
     * <p>
     * Also adds the action listener implemented in this class to the New Game Button.
     * 
     * @see actionPerformed(ActionEvent ae)
     */
    public NewGamePanel() {
        setBorder(BorderFactory.createTitledBorder("New Game"));
        
        // Create Swing Components
        JLabel numDecksLabel = new JLabel("Number of Decks:  ");
        JLabel shufflePrefLabel = new JLabel("Shuffle Preference:  ");
        JLabel outPrefLabel = new JLabel("Output Preference: ");
        
        String [] numDeckStrings = {"1", "2", "3"};
        String [] shufflePrefStrings = {"Individual", "Together"};
        String [] outPrefStrings = {"HTML", "TXT"};
        numDecks = new JComboBox(numDeckStrings);
        shufflePref = new JComboBox(shufflePrefStrings);
        outputPref = new JComboBox(outPrefStrings);
        
        draw2Check = new JCheckBox("Draw 2");
        skipCheck = new JCheckBox("Skip");
        revCheck = new JCheckBox("Reverse");
        wildCheck = new JCheckBox("Wild");
        wild4Check = new JCheckBox("Wild Draw 4");
        draw2Check.setSelected(true);
        skipCheck.setSelected(true);
        revCheck.setSelected(true);
        wildCheck.setSelected(true);
        wild4Check.setSelected(true);
        
        newGameButton = new JButton("New Game");
        
        //Add listeners
        newGameButton.addActionListener(this);
        
        // Add components to layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        ///////////// FRIST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(numDecksLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(numDecks, gc);
        
        /////////// SECOND ROW //////////
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(shufflePrefLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(shufflePref, gc);
        
        /////////// THIRD ROW /////////////
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(outPrefLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(outputPref, gc);
        
        /////////// FOURTH ROW /////////////
        gc.gridy = 3;
        gc.gridx = 0;
        add(draw2Check, gc);
        
        gc.gridx = 1;
        add(skipCheck, gc);
        
        gc.gridx = 2;
        gc.gridheight = 2;
        add(wild4Check, gc);
        
        //////////// FIFTH ROW //////////
        gc.gridy = 4;
        gc.gridx = 0;
        gc.gridheight = 1;
        add(revCheck, gc);
        
        gc.gridx = 1;
        add(wildCheck, gc);
        
        ////////// FINAL ROW //////////
        gc.gridy = 5;
        add(newGameButton, gc);
    }
    
    /**
     * Sets the custom NewGameListener to the member variable
     * 
     * @param listener NewGameListener 
     */
    public void setNewGameListener(NewGameListener listener) {
        newGameListener = listener;
    }
    
    /**
     * Implementation of the ActionListener interface, by overriding the
     * actionPerformed method.  Takes the data in the swing components 
     * representing the user's preferences and calls the newGame method of the
     * NewGameListener interface, which then creates a New UnoGame with the 
     * selected preferences.
     * 
     * @param ae Action Event from the button click
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int numDecksInt = numDecks.getSelectedIndex() + 1;
        int shufflePrefInt = shufflePref.getSelectedIndex();
        int outPrefInt = outputPref.getSelectedIndex();
        System.out.println(numDecksInt + " " + shufflePrefInt + outPrefInt);
        
        // Construct ArrayList of cards to omit
        ArrayList<Card> omit = new ArrayList<Card>();
        
        if (!draw2Check.isSelected()) {
            Card draw2Blue = new Card(CardNum.DRAW_2, CardColor.BLUE);
            Card draw2Red = new Card(CardNum.DRAW_2, CardColor.RED);
            Card draw2Yellow = new Card(CardNum.DRAW_2, CardColor.YELLOW);
            Card draw2Green = new Card(CardNum.DRAW_2, CardColor.GREEN);
            omit.add(draw2Blue);
            omit.add(draw2Red);
            omit.add(draw2Yellow);
            omit.add(draw2Green);
        }
        if (!skipCheck.isSelected()) {
            Card skipBlue = new Card(CardNum.SKIP, CardColor.BLUE);
            Card skipRed = new Card(CardNum.SKIP, CardColor.RED);
            Card skipYellow = new Card(CardNum.SKIP, CardColor.YELLOW);
            Card skipGreen = new Card(CardNum.SKIP, CardColor.GREEN);
            omit.add(skipBlue);
            omit.add(skipRed);
            omit.add(skipYellow);
            omit.add(skipGreen);
        }
        if (!revCheck.isSelected()) {
            Card revBlue = new Card(CardNum.REVERSE, CardColor.BLUE);
            Card revRed = new Card(CardNum.REVERSE, CardColor.RED);
            Card revYellow = new Card(CardNum.REVERSE, CardColor.YELLOW);
            Card revGreen = new Card(CardNum.REVERSE, CardColor.GREEN);
            omit.add(revBlue);
            omit.add(revRed);
            omit.add(revYellow);
            omit.add(revGreen);
        }
        if (!wildCheck.isSelected()) {
            Card wildBlue = new Card(CardNum.WILD, CardColor.BLUE);
            Card wildRed = new Card(CardNum.WILD, CardColor.RED);
            Card wildYellow = new Card(CardNum.WILD, CardColor.YELLOW);
            Card wildGreen = new Card(CardNum.WILD, CardColor.GREEN);
            omit.add(wildBlue);
            omit.add(wildRed);
            omit.add(wildYellow);
            omit.add(wildGreen);
        }
        if (!wild4Check.isSelected()) {
            Card wild4Blue = new Card(CardNum.WILD_D_4, CardColor.BLUE);
            Card wild4Red = new Card(CardNum.WILD_D_4, CardColor.RED);
            Card wild4Yellow = new Card(CardNum.WILD_D_4, CardColor.YELLOW);
            Card wild4Green = new Card(CardNum.WILD_D_4, CardColor.GREEN);
            omit.add(wild4Blue);
            omit.add(wild4Red);
            omit.add(wild4Yellow);
            omit.add(wild4Green);
        }
        System.out.println(omit.toString());
        
        newGameListener.newGame(numDecksInt, shufflePrefInt, outPrefInt, omit);
    }
}
