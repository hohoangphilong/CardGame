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
import Interfaces.GameConstants;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Dealer implements GameConstants {

    private Deck deck;
    private Stack<Card> cardStack;

    public Dealer() {
        this.deck = new Deck();
    }

    public void shuffle() {
        ArrayList<Card> DeckOfCards = deck.getCards();
        ArrayList<Card> shuffledCards = new ArrayList<Card>();

        while (!DeckOfCards.isEmpty()) {
            int totalCards = DeckOfCards.size();

            Random random = new Random();
            int pos = (Math.abs(random.nextInt())) % totalCards;

            Card randomCard = DeckOfCards.get(pos);
            DeckOfCards.remove(pos);
            shuffledCards.add(randomCard);
        }

        cardStack = new Stack<Card>();
        for (Card card : shuffledCards) {
            cardStack.add(card);
        }

    }
    
    public void spreadOut(Player[] players){
        for(int i = 0; i < FIRSTHAND; i++){
            for(Player player : players){
                player.obtainCard(cardStack.pop());
            }
        }
        players[0].obtainCard(cardStack.pop());
    }
    
    public Card getCard(){
        return cardStack.pop();
    }
    
    public Stack<Card> getCardStack(){
        return cardStack;
    }
    
    public int getRemainingCards(){
        return cardStack.size();
    }
}
