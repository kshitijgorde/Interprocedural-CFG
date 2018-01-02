// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.games.bjack;

import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Insets;
import com.jafar.ui.ImageButton;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class Controls extends Panel implements ActionListener
{
    protected static final int NUM_BUTTONS = 6;
    private BlackJack bj;
    private TextField bet;
    private ImageButton[] buttons;
    private Insets insets0;
    
    public Controls(final BlackJack bj) {
        this.insets0 = new Insets(0, 0, 0, 0);
        this.bj = bj;
        this.setLayout(new FlowLayout(1, 5, 3));
        this.setBackground(Color.black);
        this.buttons = new ImageButton[6];
        for (int i = 0; i < 6; ++i) {
            this.add(this.buttons[i] = new ImageButton(bj.buttonImg[i]));
            if (i != 5) {
                this.buttons[i].addActionListener(this);
            }
            this.buttons[i].setMargin(this.insets0);
            this.buttons[i].setBorderWidth(1);
        }
        this.buttons[5].setToggle(true);
        this.buttons[5].setBorderType(0);
        (this.bet = new TextField("10", 5)).setFont(bj.smFont);
        this.bet.setBackground(Color.white);
        this.bet.setForeground(Color.black);
        this.add(this.bet);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.white);
        graphics.draw3DRect(0, 0, bounds.width - 1, bounds.height - 1, true);
    }
    
    public void enableButton(final int n) {
        this.buttons[n].setEnabled(true);
    }
    
    public void disableButton(final int n) {
        this.buttons[n].setEnabled(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.bj.dollars <= 0) {
            this.bj.msgImage = 6;
            this.bj.repaint();
            return;
        }
        final Object source = actionEvent.getSource();
        int int1 = 10;
        try {
            int1 = Integer.parseInt(this.bet.getText().trim());
        }
        catch (NumberFormatException ex) {
            System.err.println("Invalid amount.");
        }
        if (int1 % 10 != 0 || int1 < 0) {
            this.bj.showMsg("Illegal Wager");
            return;
        }
        if (int1 > this.bj.dollars) {
            this.bj.showMsg("Can't bet more than what you have!");
            return;
        }
        this.bj.playerBet = int1;
        if (source == this.buttons[0]) {
            this.bj.deal();
            return;
        }
        if (source == this.buttons[1]) {
            this.bj.hit();
            return;
        }
        if (source == this.buttons[2]) {
            this.bj.stand();
            return;
        }
        if (source == this.buttons[3]) {
            this.bj.playDouble();
            return;
        }
        if (source == this.buttons[4]) {
            this.bj.resetGame();
        }
    }
    
    public void disableBet() {
        this.bet.setEnabled(false);
    }
    
    public void enableBet() {
        this.bet.setEnabled(true);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.bj.getBounds().width, this.bj.buttonImg[0].getHeight(this.bj) + 10);
    }
}
