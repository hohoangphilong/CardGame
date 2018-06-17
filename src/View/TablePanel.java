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
import GameComponents.Player;
import Interfaces.GameConstants;
import Controller.MyButtonListener;
import GameComponents.Card;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class TablePanel extends JPanel implements GameConstants {

    private Player sPlayer, ePlayer, nPlayer, wPlayer; //south, north, east, west
    private JLayeredPane sCardHolder, eCardHolder, nCardHolder, wCardHolder,
            sPlayedCardHolder, ePlayedCardHolder, nPlayedCardHolder, wPlayedCardHolder;
    private JLabel sPlayerName, ePlayerName, nPlayerName, wPlayerName;
    private JPanel sPlayerPanel, ePlayerPanel, nPlayerPanel, wPlayerPanel, centerPanel;
    private JButton btnDraw, btnSort, btnNumCards;

    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private MyButtonHandler myHandler;

    public TablePanel(Player[] players) {
        setOpaque(true);
        setPreferredSize(new Dimension(960, 720));
        setBackground(new Color(255, 153, 0));
        layout = new GridBagLayout();
        setLayout(layout);

        sPlayer = players[0];
        ePlayer = players[1];
        nPlayer = players[2];
        wPlayer = players[3];

        myHandler = new MyButtonHandler();

        sPlayerName = new JLabel();
        ePlayerName = new JLabel();
        nPlayerName = new JLabel();
        wPlayerName = new JLabel();
        btnNumCards = new JButton();

        sPlayerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("cards/image1.jpg"));
                    g.drawImage(image, 0, 0, this);
                } catch (Exception e) {
                }
            }
        };
        ePlayerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("cards/image2.jpg"));
                    g.drawImage(image, 0, 0, this);
                } catch (Exception e) {
                }
            }
        };
        nPlayerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("cards/image2.jpg"));
                    g.drawImage(image, 0, 0, this);
                } catch (Exception e) {
                }
            }
        };
        wPlayerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("cards/image2.jpg"));
                    g.drawImage(image, 0, 0, this);
                } catch (Exception e) {
                }
            }
        };
        centerPanel = new JPanel();

        sCardHolder = new JLayeredPane();
        eCardHolder = new JLayeredPane();
        nCardHolder = new JLayeredPane();
        wCardHolder = new JLayeredPane();

        sPlayedCardHolder = new JLayeredPane();
        ePlayedCardHolder = new JLayeredPane();
        nPlayedCardHolder = new JLayeredPane();
        wPlayedCardHolder = new JLayeredPane();

        btnDraw = new JButton("Draw");
        btnSort = new JButton("Sort");

        sPlayerName.setOpaque(false);
        ePlayerName.setOpaque(false);
        nPlayerName.setOpaque(false);
        wPlayerName.setOpaque(false);
        btnNumCards.setOpaque(true);
        btnNumCards.setEnabled(false);

        sPlayerPanel.setOpaque(true);
        ePlayerPanel.setOpaque(true);
        nPlayerPanel.setOpaque(true);
        wPlayerPanel.setOpaque(true);
        centerPanel.setOpaque(false);

        sCardHolder.setOpaque(false);
        eCardHolder.setOpaque(false);
        nCardHolder.setOpaque(false);
        wCardHolder.setOpaque(false);

        sPlayedCardHolder.setOpaque(false);
        ePlayedCardHolder.setOpaque(false);
        nPlayedCardHolder.setOpaque(false);
        wPlayedCardHolder.setOpaque(false);

        btnDraw.setOpaque(false);
        btnSort.setOpaque(false);

        centerPanel.setPreferredSize(new Dimension(480, 120));
        sPlayerPanel.setPreferredSize(new Dimension(120, 120));
        ePlayerPanel.setPreferredSize(new Dimension(120, 120));
        nPlayerPanel.setPreferredSize(new Dimension(120, 120));
        wPlayerPanel.setPreferredSize(new Dimension(120, 120));

        sCardHolder.setPreferredSize(new Dimension(480, 120));
        eCardHolder.setPreferredSize(new Dimension(120, 360));
        nCardHolder.setPreferredSize(new Dimension(480, 120));
        wCardHolder.setPreferredSize(new Dimension(120, 360));

        sPlayedCardHolder.setPreferredSize(new Dimension(240, 120));
        ePlayedCardHolder.setPreferredSize(new Dimension(120, 240));
        nPlayedCardHolder.setPreferredSize(new Dimension(240, 120));
        wPlayedCardHolder.setPreferredSize(new Dimension(120, 240));

        btnSort.setPreferredSize(new Dimension(80, 40));
        btnDraw.setPreferredSize(new Dimension(80, 40));

        sPlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        ePlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        nPlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        wPlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNumCards.setFont(new Font("Tahoma", Font.BOLD, 20));

        sPlayerName.setText(sPlayer.getPlayerName());
        ePlayerName.setText(ePlayer.getPlayerName());
        nPlayerName.setText(nPlayer.getPlayerName());
        wPlayerName.setText(wPlayer.getPlayerName());
        btnNumCards.setText("Remaining Cards: 15");
        sPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        ePlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        nPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        wPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        btnNumCards.setHorizontalAlignment(SwingConstants.CENTER);

        btnNumCards.setBackground(new Color(102, 51, 0));

        sPlayerPanel.add(sPlayerName, BorderLayout.CENTER);
        ePlayerPanel.add(ePlayerName, BorderLayout.CENTER);
        nPlayerPanel.add(nPlayerName, BorderLayout.CENTER);
        wPlayerPanel.add(wPlayerName, BorderLayout.CENTER);

        constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(sPlayerPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 7;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(ePlayerPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 6;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(nPlayerPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(wPlayerPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 6;
        constraints.gridheight = 4;
        add(centerPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        add(sCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 7;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        add(eCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        add(nCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        add(wCardHolder, constraints);

        centerPanel.setLayout(new GridBagLayout());
        sPlayedCardHolder.setBorder(BorderFactory.createLineBorder(Color.green));
        ePlayedCardHolder.setBorder(BorderFactory.createLineBorder(Color.green));
        nPlayedCardHolder.setBorder(BorderFactory.createLineBorder(Color.green));
        wPlayedCardHolder.setBorder(BorderFactory.createLineBorder(Color.green));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 2;
        centerPanel.add(btnNumCards, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        centerPanel.add(sPlayedCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 5;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        centerPanel.add(ePlayedCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        centerPanel.add(nPlayedCardHolder, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        centerPanel.add(wPlayedCardHolder, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 6;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(btnDraw, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 7;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(btnSort, constraints);

        btnSort.addActionListener(myHandler);
        btnDraw.addActionListener(myHandler);

        setCards();
    }

    public void setNumOfCards(int value) {
        btnNumCards.setText("Remaining Cards: " + String.valueOf(value));
    }

    public void updatePanel() {
        setCards();
        revalidate();
    }

    public final void setCards() {

        int sOffset = calculateOffet(sPlayer.getTotalCards(), HORIZONTAL_OFFSET);
        int eOffset = -calculateOffet(ePlayer.getTotalCards(), VERTICAL_OFFSET);
        int nOffset = -calculateOffet(nPlayer.getTotalCards(), HORIZONTAL_OFFSET);
        int wOffset = calculateOffet(wPlayer.getTotalCards(), VERTICAL_OFFSET);

        Point sPoint = new Point(0, 10);
        Point ePoint = new Point(20, 264);
        Point nPoint = new Point(408, 10);
        Point wPoint = new Point(20, 10);

        Point spPoint = new Point(0, 10);
        Point epPoint = new Point(20, 144);
        Point npPoint = new Point(168, 10);
        Point wpPoint = new Point(20, 10);

        setEachPlayerCards(sPlayer, sCardHolder, sPoint, sOffset);
        setEachPlayerCards(ePlayer, eCardHolder, ePoint, eOffset);
        setEachPlayerCards(nPlayer, nCardHolder, nPoint, nOffset);
        setEachPlayerCards(wPlayer, wCardHolder, wPoint, wOffset);

        setPlayedCards(sPlayer, sPlayedCardHolder, spPoint, sOffset);
        setPlayedCards(ePlayer, ePlayedCardHolder, epPoint, eOffset);
        setPlayedCards(nPlayer, nPlayedCardHolder, npPoint, nOffset);
        setPlayedCards(wPlayer, wPlayedCardHolder, wpPoint, wOffset);
    }

    public void setPlayedCards(Player player, JLayeredPane cardHolder, Point p, int offset) {
        cardHolder.removeAll();
        int i = 0;
        for (Card card : player.getPlayedCards()) {
            card.setBounds(p.x, p.y, card.CARD_SIZE.width,
                    card.CARD_SIZE.height);
            cardHolder.add(card, i++);
            cardHolder.moveToFront(card);
            if (Math.abs(offset) == VERTICAL_OFFSET) {
                p.y += offset;
            } else {
                p.x += offset;
            }

        }
        repaint();
    }

    private void setEachPlayerCards(Player player, JLayeredPane cardHolder, Point p, int offset) {
        cardHolder.removeAll();
        int i = 0;
        for (Card card : player.getAllCards()) {
            card.setBounds(p.x, p.y, card.CARD_SIZE.width,
                    card.CARD_SIZE.height);
            cardHolder.add(card, i++);
            cardHolder.moveToFront(card);
            if (Math.abs(offset) == VERTICAL_OFFSET) {
                p.y += offset;
            } else {
                p.x += offset;
            }
        }
        repaint();
    }

    private int calculateOffet(int totalCards, int offset) {
        if (offset == VERTICAL_OFFSET) {
            return offset;
        } else if (totalCards <= 9) {
            return offset;
        } else {
            return offset - 3;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage image = ImageIO.read(new File("cards/br.jpg"));
            g.drawImage(image, 0, 0, this);
        } catch (Exception e) {
        }
    }

    class MyButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (sPlayer.isMyTurn()) {

                if (e.getSource() == btnDraw) {
                    BUTTONLISTENER.draw();
                } else if (e.getSource() == btnSort) {
                    BUTTONLISTENER.sort();
                }
            }
        }
    }
}
