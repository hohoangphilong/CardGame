/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author H2PL
 */
import Interfaces.GameConstants;
import Controller.MainController;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements GameConstants{
    private MainController controller;
    private TablePanel mainPanel;
    
    public MainFrame(){
        controller = new MainController();
        CARDLISTENER.setController(controller);
        BUTTONLISTENER.setController(controller);
        mainPanel = controller.getPanel();
        add(mainPanel);
    }
}
