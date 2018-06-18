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
import View.TablePanel;
import GameComponents.*;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MainController implements GameConstants {

    private Game game;
    private TablePanel mainPanel;
    private Card cardPointerCard;//save card which just have been played

    public MainController() {
        game = new Game();
        mainPanel = new TablePanel(game.getPlayers());
        game.getPlayers()[0].toggleturn();//player go first, with 10 cards in hand
    }

    public TablePanel getPanel() {
        return mainPanel;
    }

    public void playThisCard(Card clickedCard) {

        if (game.getPlayers()[0].getAllCards().size() == 9) {
            JOptionPane.showMessageDialog(mainPanel, "Please draw", "Message", JOptionPane.OK_OPTION);
            return;
        } else {
            cardPointerCard = clickedCard;
            clickedCard.setPlayedCard();
            game.playCard(clickedCard);
            mainPanel.updatePanel();
            if (game.getPlayers()[3].getPlayedCards().size() > 0) {
                game.getPlayers()[3].getPlayedCard().removeMouseListener(CARDLISTENER);

            }
            game.switchTurn();
        }

        for (int i = 1; i < 4; i++) {
            if (!game.checkGameOver()) {
                if (!game.check(game.getPlayers()[i], cardPointerCard)) {
                    drawNewCard();
                } else {
                    getThisCard(cardPointerCard);
                }

                game.playCard(autoPlayCard(game.getPlayers()[i]));
                mainPanel.updatePanel();
                game.switchTurn();

            }
        }
        if (game.checkGameOver()) {
            game.showAllCards();
            mainPanel.updatePanel();
            Player p = game.isWinner();
            JOptionPane.showMessageDialog(mainPanel, "Game over, "
                    + p.getPlayerName() + " is win with score "
                    + p.getScore(), "Message", JOptionPane.OK_OPTION);
            System.exit(0);
        }
    }

    public void getThisCard(Card clickedCard) {
        if (game.checkGameOver()) {
            game.showAllCards();
            mainPanel.updatePanel();
            Player p = game.isWinner();
            JOptionPane.showMessageDialog(mainPanel, "Game over, "
                    + p.getPlayerName() + " is win with score "
                    + p.getScore(), "Message", JOptionPane.OK_OPTION);
            System.exit(0);
        } else {
            clickedCard.removeMouseListener(CARDLISTENER);
            game.getCard(clickedCard);

            mainPanel.updatePanel();
        }
    }

    public void drawNewCard() {
        game.drawCard();
        mainPanel.setNumOfCards(game.getRemainingCards());

        mainPanel.updatePanel();
    }

    public void sortCards() {
        //sort player cards, not bot
        game.sort(game.getPlayers()[0]);
        mainPanel.updatePanel();
    }

    //for bot-playing
    private Card autoPlayCard(Player player) {
        //check, sort and play top card
        game.sort(player);
        cardPointerCard = player.getAllCards().get(player.getAllCards().size() - 1);
        return cardPointerCard;
    }

}
