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
import GameComponents.Card;
import GameComponents.Dealer;
import GameComponents.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Game implements GameConstants {

    private Player[] players;
    private final Dealer dealer;

    private ArrayList<Integer> tempList;

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

        tempList = new ArrayList<Integer>();
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

    public int getRemainingCards() {
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
                if (p == players[3]) {
                    if(check(p, card)){
                        card.addMouseListener(CARDLISTENER);
                    }
                }else{
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
    public boolean check(Player p, Card c) {
        ArrayList<Card> temp = new ArrayList<>(p.getAllCards());
        temp.add(c);
        for(int i = 0; i < p.getNumOfCombos()*3; i++){
            temp.remove(0);
        }
        if (hasKind(temp)) {
            return true;
        }
        if (hasFlush(temp)) {
            return true;
        }
        return false;
    }

    private int[] flushPos;
    private int[] kindPos;
    private int numCardsInCombos, countCombos;

    public void sort(Player p) {
        numCardsInCombos = 0;
        countCombos = 0;
        p.setAllCards(split(p.getAllCards()));
        p.setNumOfCombos(countCombos);
        int score = 0;
        for (int i = numCardsInCombos; i < p.getTotalCards(); i++) {
            score += p.getAllCards().get(i).getRank();
        }
        p.setScore(score);
    }

    private void sortRank(ArrayList<Card> list) {
        Collections.sort(list, (Card o1, Card o2) -> o2.getRank() - o1.getRank());
    }

    private void sortSuit(ArrayList<Card> list) {
        sortRank(list);
        Collections.sort(list, (Card o1, Card o2) -> o2.getSuit() - o1.getSuit());
    }

    private ArrayList<Card> split(ArrayList<Card> list) {
        ArrayList<Card> temp = new ArrayList<>();

        if (hasKind(list)) {
            if (hasFlush(list)) {
                if (isConflict(list)) {
                    sortRank(list);
                    int m = calculate(list, kindPos);
                    sortSuit(list);
                    int n = calculate(list, flushPos);

                    if (m < n) {
                        for (int i = 0; i < flushPos.length; i++) {
                            Card c = new Card(list.get(flushPos[i]));
                            temp.add(c);
                        }
                        for (int i = 0; i < flushPos.length; i++) {
                            list.remove(list.get(flushPos[i] - i));
                        }
                        sortRank(temp);
                        countCombos++;
                        numCardsInCombos += flushPos.length;
                        return append(temp, split(list));
                    } else {
                        sortRank(temp);
                        for (int i = 0; i < kindPos.length; i++) {
                            Card c = new Card(list.get(kindPos[i]));
                            temp.add(c);
                        }
                        for (int i = 0; i < kindPos.length; i++) {
                            list.remove(list.get(kindPos[i] - i));
                        }
                        countCombos++;
                        numCardsInCombos += kindPos.length;
                        return append(temp, split(list));
                    }
                } else {
                    for (int i = 0; i < flushPos.length; i++) {
                        Card c = new Card(list.get(flushPos[i]));
                        temp.add(c);
                    }
                    for (int i = 0; i < flushPos.length; i++) {
                        list.remove(list.get(flushPos[i] - i));
                    }
                    sortRank(temp);
                    countCombos++;
                    numCardsInCombos += flushPos.length;
                    return append(temp, split(list));
                }
            } else {
                sortRank(temp);
                for (int i = 0; i < kindPos.length; i++) {
                    Card c = new Card(list.get(kindPos[i]));
                    temp.add(c);
                }
                for (int i = 0; i < kindPos.length; i++) {
                    list.remove(list.get(kindPos[i] - i));
                }
                countCombos++;
                numCardsInCombos += kindPos.length;
                return append(temp, split(list));
            }
        } else {
            if (hasFlush(list)) {
                for (int i = 0; i < flushPos.length; i++) {
                    Card c = new Card(list.get(flushPos[i]));
                    temp.add(c);
                }
                for (int i = 0; i < flushPos.length; i++) {
                    list.remove(list.get(flushPos[i] - i));
                  
                }
                sortRank(temp);
                countCombos++;
                numCardsInCombos += flushPos.length;
                return append(temp, split(list));
            } else {
                sortRank(list);
                return list;
            }
        }
    }

    private ArrayList<Card> append(ArrayList<Card> src, ArrayList<Card> dst) {
        dst.forEach((c) -> {
            Card card = new Card(c);
            src.add(card);//check if not point correctly
        });
        return src;
    }

    private int calculate(ArrayList<Card> list, int[] pos) {
        int sum = 0;
        for (int i = 0; i < pos.length; i++) {
            sum += list.get(pos[i]).getRank();
        }
        return sum;
    }

    private boolean isConflict(ArrayList<Card> list) {
        for (int i = 0; i < flushPos.length; i++) {
            sortSuit(list);
            Card c1 = new Card(list.get(i));
            for (int j = 0; j < kindPos.length; j++) {
                sortRank(list);
                Card c2 = new Card(list.get(j));
                if (c1.getRank() == c2.getRank() && c1.getSuit() == c2.getSuit()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasFlush(ArrayList<Card> list) {
        sortSuit(list);
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(i).getSuit() == list.get(i + 1).getSuit() && list.get(i).getSuit() == list.get(i + 2).getSuit()) {
                if (list.get(i).getRank() == list.get(i + 1).getRank() + 1 && list.get(i).getRank() == list.get(i + 2).getRank() + 2) {
                    flushPos = new int[3];
                    for (int n = 0; n < 3; n++) {
                        flushPos[n] = i + n;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasKind(ArrayList<Card> list) {
        sortRank(list);
        for (int i = 0; i < list.size() - 2; i++) {
//            if (i < list.size() - 3
//                    && (list.get(i).getRank() == list.get(i + 1).getRank()
//                    && list.get(i).getRank() == list.get(i + 2).getRank()
//                    && list.get(i).getRank() == list.get(i + 3).getRank())) {
//                kindPos = new int[4];
//                for (int n = 0; n < 4; n++) {
//                    kindPos[n] = i + n;
//                }
//                return true;
//            } else 
            if (list.get(i).getRank() == list.get(i + 1).getRank() && list.get(i).getRank() == list.get(i + 2).getRank()) {
                kindPos = new int[3];
                for (int n = 0; n < 3; n++) {
                    kindPos[n] = i + n;
                }
                return true;
            }
        }
        return false;
    }

    private void switchPlayedCard() {
        ArrayList<Card> list0, list1, list2, list3;
        list0 = new ArrayList<Card>(players[0].getPlayedCards());
        list1 = new ArrayList<Card>(players[1].getPlayedCards());
        list2 = new ArrayList<Card>(players[2].getPlayedCards());
        list3 = new ArrayList<Card>(players[3].getPlayedCards());
        int floor = Math.min(list0.size(), Math.min(list1.size(), Math.min(list2.size(), list3.size())));

        if (list0.size() > floor) {
            list0.remove(list0.size() - 1);
        }
        if (list1.size() > floor) {
            list1.remove(list1.size() - 1);
        }
        if (list2.size() > floor) {
            list2.remove(list2.size() - 1);
        }
        if (list3.size() > floor) {
            list3.remove(list3.size() - 1);
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

    public Player isWinner() {
        int maxScore = (Math.min(Math.min(Math.min(players[0].getScore(), players[1].getScore()), players[2].getScore()), players[3].getScore()));
        if (players[0].getScore() == maxScore) {
            return players[0];
        }
        if (players[1].getScore() == maxScore) {
            return players[1];
        }
        if (players[2].getScore() == maxScore) {
            return players[2];
        }
        if (players[3].getScore() == maxScore) {
            return players[3];
        }
        return null;
    }

    public void showAllCards(){
        for(int i = 0; i < 4; i++){
            players[i].getAllCards().forEach((card) -> {
                card.setState(true);
            });
        }
    }
}
