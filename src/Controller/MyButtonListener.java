/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author H2PL
 */
public class MyButtonListener implements ActionListener{
    private MainController mainController;
    
    public void setController(MainController obj){
        mainController = obj;
    }
    
    public void sort(){
        mainController.sortCards();
    }
    
    public void draw(){
        mainController.drawNewCard();
    }   

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
    
}
