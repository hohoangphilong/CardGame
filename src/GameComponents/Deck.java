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
import GameComponents.Card;
import Controller.MyCardListener;
import Interfaces.GameConstants;

import java.util.ArrayList;

public class Deck implements GameConstants{
    private ArrayList<Card> Cards;
    
    public Deck(){
        Cards = new ArrayList<Card>();
        
        addCards();
        addCardListener(CARDLISTENER);
    }

    private void addCards() {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Cards.add(new Card(j, i));
            }
        }
    }

    private void addCardListener(MyCardListener listener) {
        for(Card card : Cards){
            card.addMouseListener(listener);
        }
    }
    
    public ArrayList<Card> getCards(){
        return Cards;
    }
}
