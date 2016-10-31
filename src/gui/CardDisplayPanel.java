/*
 * Kyle Blauer - Project 3
 * CS 2365 - 001 Spring 2014
 */

package gui;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CardDisplayPanel is the class that contains all the gui components for the 
 * display of pictures representing the cards in the user's hand.  The images
 * are loaded in locally, resized, and then displayed on the screen to the user.
 * 
 * @author Kyle Blauer
 * @version 1.1
 */
public class CardDisplayPanel extends JPanel {
    private static final int s_imgX = 100;
    private static final int s_imgY = 200;
    
    JLabel card1;
    JLabel card2;
    JLabel card3;
    JLabel card4;
    JLabel card5;
    JLabel card6;
    JLabel card7;
    
    /**
     * Creates the CardDisplayPanel by initializing 7 JLabels and choosing
     * pictures of the 'back' of the uno card.  This indicates the user has
     * not chosen their settings and initialized the game.
     */
    public CardDisplayPanel() {
        layoutPanel();
    }
    
    private void layoutPanel() {
        /////// Create swing components
        ImageIcon cardIcon = new ImageIcon("unoCards/back.png");
        cardIcon = resizeIcon(cardIcon);
        card1 = new JLabel(cardIcon);
        card2 = new JLabel(cardIcon);
        card3 = new JLabel(cardIcon);
        card4 = new JLabel(cardIcon);
        card5 = new JLabel(cardIcon);
        card6 = new JLabel(cardIcon);
        card7 = new JLabel(cardIcon);

        ////// Add components to the layout
        GridBagConstraints gc = new GridBagConstraints();

        //////////// FIRST ROW  //////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.PAGE_END;
        add(card1, gc);

        gc.gridx = 1;
        add(card2, gc);

        gc.gridx = 2;
        add(card3, gc);

        gc.gridx = 3;
        add(card4, gc);

        gc.gridx = 4;
        add(card5, gc);

        gc.gridx = 5;
        add(card6, gc);

        gc.gridx = 6;
        add(card7, gc);
    }

    private ImageIcon resizeIcon(ImageIcon icon) {
        int newW = s_imgX;
        int newH = s_imgY;
        
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dimg.createGraphics();
        g.scale(.5, .5);
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        
        return new ImageIcon(dimg);
    }
    
    /**
     * Sets the images associated with the JLabels to the corresponding 
     * ImageIcon within the param ArrayList. Allows the user to visually
     * see his/her hand.
     * 
     * @param images ArrayList containing seven ImageIcons, one for each 
     *               of the cards in the player's hand at any given time.
     */
    public void setCardImages(ArrayList<ImageIcon> images) {
        // Resize array of images
        for (int index=0; index<images.size(); index++) {
            ImageIcon tempImage = images.get(index);
            tempImage = resizeIcon(tempImage);
            
            images.set(index, tempImage);
        }
        
        card1.setIcon(images.get(0));
        card2.setIcon(images.get(1));
        card3.setIcon(images.get(2));
        card4.setIcon(images.get(3));
        card5.setIcon(images.get(4));
        card6.setIcon(images.get(5));
        card7.setIcon(images.get(6));
    }
    
    
}
