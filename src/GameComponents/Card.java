/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameComponents;

/**
 *
 * @author H2PL
 */
import Interfaces.CardConstants;
import Interfaces.ICard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Card extends JPanel implements CardConstants, ICard {

    private int cardRank;
    private int cardSuit;
    private boolean cardState = false;//false means face down - true means face up
    private boolean isPlayedCard = false;
    private BufferedImage image;

    private final Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private final Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.blue, Color.gray);

    public Card(int rank, int suit) {
        this.cardRank = rank;
        this.cardSuit = suit;

        this.setPreferredSize(CARD_SIZE);
        this.setBorder(defaultBorder);
        this.addMouseListener(new MouseHandler());
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            if (!cardState) {
                image = ImageIO.read(new File("cards/b1fv.png"));
            } else {
                image = ImageIO.read(new File("cards/" + this.cardSuit + "" + this.cardRank + ".png"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loading Image Error" + e.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
        g.drawImage(image, 0, 0, this);
    }

    //My Mouse Listener
    class MouseHandler extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            setBorder(focusedBorder);
        }

        public void mouseExited(MouseEvent e) {
            setBorder(defaultBorder);
        }
    }

    public void setState(boolean newState) {
        this.cardState = newState;
        removeAll();
        revalidate();
        repaint();
    }

    public boolean getState() {
        return this.cardState;
    }
    
    public void setPlayedCard(){
        this.isPlayedCard = true;
    }
    
    public boolean isPlayedCard(){
        return this.isPlayedCard;
    }

    @Override
    public void setRank(int newRank) {
        this.cardRank = newRank;
    }

    @Override
    public int getRank() {
        return this.cardRank;
    }

    @Override
    public void setSuit(int newSuit) {
        this.cardSuit = newSuit;
    }

    @Override
    public int getSuit() {
        return this.cardSuit;
    }
}
