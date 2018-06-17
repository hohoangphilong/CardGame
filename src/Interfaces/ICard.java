/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Dimension;

/**
 *
 * @author H2PL
 */
public interface ICard {
    int HEIGHT = 96;
    int WIDTH = 72;
    
    Dimension CARD_SIZE = new Dimension(WIDTH,HEIGHT);
    
    void setRank(int newRank);
    int getRank();
    
    void setSuit(int newSuit);
    int getSuit();
}
