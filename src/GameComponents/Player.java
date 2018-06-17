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
import java.util.ArrayList;
import Interfaces.GameConstants;
import java.awt.event.MouseEvent;

public class Player implements GameConstants {

    private String playerName;
    private boolean isMyTurn;
    private int numOfCombos;
    private ArrayList<Card> myPlayedCards;
    private ArrayList<Card> myCards;

    public Player(String name) {
        myCards = new ArrayList<Card>();
        myPlayedCards = new ArrayList<Card>();
        playerName = name;
        isMyTurn = false;
        numOfCombos = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void obtainCard(Card newCard) {
        myCards.add(newCard);
    }

    public void getCard(Card playedCard) {
        myCards.add(0, playedCard);
    }

    public void removeCard(Card thisCard) {
        myCards.remove(thisCard);
    }

    public void setAllCards(ArrayList<Card> newCards) {
        myCards = newCards;
    }

    public ArrayList<Card> getAllCards() {
        return myCards;
    }

    public int getTotalCards() {
        return myCards.size();
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void toggleturn() {
        isMyTurn = (isMyTurn) ? false : true;
    }

    public void setNumOfCombos(int num) {
        numOfCombos = num;
    }

    public int getNumOfCombos() {
        return numOfCombos;
    }

    public void addPlayedCard(Card card) {
        myPlayedCards.add(card);
    }

    public void removePlayedCard(Card card) {
        myPlayedCards.remove(card);
    }

    public Card getPlayedCard() {
        return myPlayedCards.get(myPlayedCards.size() - 1);
    }

    public ArrayList<Card> getPlayedCards() {
        return myPlayedCards;
    }

    public void setPlayedCards(ArrayList<Card> list) {
        myPlayedCards = list;
    }
}
