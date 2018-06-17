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
import Interfaces.GameConstants;
import GameComponents.*;
import GameComponents.Card;
import GameComponents.Dealer;
import GameComponents.Player;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Random;

public class Game implements GameConstants {

    private Player[] players;
    private final Dealer dealer;

    public Game() {
        Player p1 = new Player("You");
        Player p2 = new Player("Bot 1");
        Player p3 = new Player("Bot 2");
        Player p4 = new Player("Bot 3");
        players = new Player[]{p1, p2, p3, p4};

        dealer = new Dealer();
        dealer.shuffle();
        dealer.spreadOut(players);

        for (Card c : players[0].getAllCards()) {
            c.setState(true);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean checkGameOver() {
        if (players[0].getPlayedCards().size() == 4
                && players[1].getPlayedCards().size() == 4
                && players[2].getPlayedCards().size() == 4
                && players[3].getPlayedCards().size() == 4) {
            return true;
        } else if (players[0].getNumOfCombos() == 3
                || players[1].getNumOfCombos() == 3
                || players[2].getNumOfCombos() == 3
                || players[3].getNumOfCombos() == 3) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getRemainingCards(){
        return dealer.getRemainingCards();
    }

    public void drawCard() {
        for (Player p : players) {
            if (p.isMyTurn() && p.getTotalCards() < 10) {
                p.obtainCard(dealer.getCard());
                if (p == players[0]) {
                    p.getAllCards().get(p.getTotalCards() - 1).setState(true);
                }
                return;
            }
        }
    }

    public void playCard(Card card) {
        for (Player p : players) {
            if (p.isMyTurn()) {
                p.addPlayedCard(card);
                p.removeCard(card);
                if (p != players[3]) {
                    card.removeMouseListener(CARDLISTENER);
                }
                card.setState(true);
                card.setPlayedCard();
                return;
            }
        }
    }

    public void getCard(Card card) {
        for (Player p : players) {
            if (p.isMyTurn()) {
                p.getCard(card);
                if (p == players[0]) {
                    players[3].removePlayedCard(card);
                }
                if (p == players[1]) {
                    players[0].removePlayedCard(card);
                }
                if (p == players[2]) {
                    players[1].removePlayedCard(card);
                }
                if (p == players[3]) {
                    players[2].removePlayedCard(card);
                }
                switchPlayedCard();
            }
        }
    }

    public void switchTurn() {
        if (players[0].isMyTurn()) {
            players[0].toggleturn();
            players[1].toggleturn();
            return;
        }
        if (players[1].isMyTurn()) {
            players[1].toggleturn();
            players[2].toggleturn();
            return;
        }
        if (players[2].isMyTurn()) {
            players[2].toggleturn();
            players[3].toggleturn();
            return;
        }
        if (players[3].isMyTurn()) {
            players[3].toggleturn();
            players[0].toggleturn();
            return;
        }
    }

    //not complete, remember to remove mouselistener of before played card
    public boolean checkFlush() {
        return true;
    }

    public boolean checkKind() {
        return true;
    }
    
    public boolean check(){
        return false;
    }

    public void sort() {
        //main sort method
    }

    private void switchPlayedCard() {
        ArrayList<Card> list0, list1, list2, list3; 
        list0 = new ArrayList<Card>(players[0].getPlayedCards());
        list1 = new ArrayList<Card>(players[1].getPlayedCards());
        list2 = new ArrayList<Card>(players[2].getPlayedCards());
        list3 = new ArrayList<Card>(players[3].getPlayedCards());
        int floor = Math.min(list0.size(), Math.min(list1.size(), Math.min(list2.size(), list3.size())));
        
        if (list0.size() > floor) {
            list0.remove(list0.size()-1);
        }        
        if (list1.size() > floor) {
            list1.remove(list1.size()-1);
        }
        if (list2.size() > floor) {
            list2.remove(list2.size()-1);
        }
        if (list3.size() > floor) {
            list3.remove(list3.size()-1);
        }

        if (players[3].getPlayedCards().size() > floor) {
            list0.add(players[3].getPlayedCard());
        }
        if (players[0].getPlayedCards().size() > floor) {
            list1.add(players[0].getPlayedCard());
        }
        if (players[1].getPlayedCards().size() > floor) {
            list2.add(players[1].getPlayedCard());
        }
        if (players[2].getPlayedCards().size() > floor) {
            list3.add(players[2].getPlayedCard());
        }
        
        players[0].setPlayedCards(list0);
        players[1].setPlayedCards(list1);
        players[2].setPlayedCards(list2);
        players[3].setPlayedCards(list3);
    }
}
