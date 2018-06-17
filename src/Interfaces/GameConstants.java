/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author H2PL
 */
import Controller.MyButtonListener;
import Controller.MyCardListener;

public interface GameConstants extends CardConstants {

    int TOTAL_CARDS = 52;
    int FIRSTHAND = 9;
    
    int HORIZONTAL_OFFSET = 48;
    int VERTICAL_OFFSET = 30;

    MyCardListener CARDLISTENER = new MyCardListener();
    MyButtonListener BUTTONLISTENER = new MyButtonListener();
}
