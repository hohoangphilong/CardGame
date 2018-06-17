/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author H2PL
 */
public class Main {

    public static void main(String[] args) {

        //Create Frame and invoke it.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
