/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author H2PL
 */
import GameComponents.Card;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyCardListener extends MouseAdapter {

    private Card sourceCard;
    private MainController mainController;

    public void setController(MainController obj) {
        mainController = obj;
    }

    public void mousePressed(MouseEvent e) {
        sourceCard = (Card) e.getSource();

        try {           
            
            if (sourceCard.isPlayedCard() == true && sourceCard.getState() == true) {
                mainController.getThisCard(sourceCard);
            } else if(sourceCard.isPlayedCard() == false && sourceCard.getState() == true){
                mainController.playThisCard(sourceCard);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);

        sourceCard = (Card) e.getSource();
        Point p = sourceCard.getLocation();
        p.y -= 10;
        sourceCard.setLocation(p);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        sourceCard = (Card) e.getSource();
        Point p = sourceCard.getLocation();
        p.y += 10;
        sourceCard.setLocation(p);
    }
}
