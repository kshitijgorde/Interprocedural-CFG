// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.games.bjack;

import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;
import com.jafar.util.card.Card;
import com.jafar.util.card.Deck;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.Applet;

public class BlackJack extends Applet
{
    protected static final int DEAL = 0;
    protected static final int HIT = 1;
    protected static final int STAND = 2;
    protected static final int DOUBLE = 3;
    protected static final int RESET = 4;
    protected static final int WAGER = 5;
    protected static final int BANKRUPT = 6;
    protected static final int BJTABLE = 7;
    protected static final int BJACK = 8;
    protected static final int DEALERWIN = 9;
    protected static final int PUSH = 10;
    protected static final int YOUWIN = 11;
    protected static final int NUM_IMAGES = 12;
    private static final int PLAYER = 0;
    private static final int DEALER = 1;
    private static final int BOTH = 2;
    private static final int BLACKJACK = 3;
    private static final int NONE = 4;
    private static final String[] imgNames;
    protected Controls controls;
    private Graphics offscreen;
    private Rectangle r;
    protected Font font;
    protected Font smFont;
    protected Font msgFont;
    protected FontMetrics fm;
    protected FontMetrics mfm;
    protected FontMetrics sfm;
    protected int fontHeight;
    protected int msgFontHeight;
    protected Image[][] cardImg;
    private Image im;
    private Image blankCard;
    protected Image[] buttonImg;
    MediaTracker tracker;
    private boolean Active;
    private boolean showBlank;
    private boolean canHit;
    private boolean canStand;
    private boolean canDeal;
    private boolean canDouble;
    private boolean showStartUpImage;
    private boolean showStatMsg;
    private boolean linkActive;
    private String resultMsg;
    private int whoWon;
    private Color bgColor;
    private Color urlColor;
    private Color fgColor;
    private Deck deck;
    private Card card;
    private Card faceless;
    protected Vector dealer;
    protected Vector player;
    private String plScoremsg;
    private String dScoremsg;
    protected int msgImage;
    protected int width;
    protected int height;
    private int rX;
    private int rY;
    private int dealerScore;
    private int playerScore;
    protected int dollars;
    protected int playerBet;
    private int jstW;
    private int stW;
    private int bankWidth;
    private Rectangle urlRegion;
    private int pMsgW;
    private String tempStr;
    private static final String errMsg = "AdBanner was not found. BlackJack not started.";
    private boolean showErrMsg;
    
    public void init() {
        int n = 0;
        this.width = Integer.parseInt(this.getParameter("width"));
        this.height = Integer.parseInt(this.getParameter("height"));
        Applet applet = this.getAppletContext().getApplet("bjadbanner");
        boolean b = false;
        while (!b && n < 3) {
            if (applet == null) {
                try {
                    Thread.sleep(3000L);
                }
                catch (InterruptedException ex) {}
                applet = this.getAppletContext().getApplet("bjadbanner");
                ++n;
            }
            else {
                b = true;
            }
        }
        if (applet == null) {
            this.showErrMsg = true;
            this.repaint();
            System.err.println("Could not find required adBanner applet on this page.");
            System.exit(0);
        }
        this.tracker = new MediaTracker(this);
        this.setLayout(new BorderLayout(0, 0));
        this.Active = false;
        this.tempStr = "/bjackres/images/00.gif";
        this.blankCard = this.getImageResource(this.tempStr);
        for (int i = 0; i < 12; ++i) {
            this.tempStr = "/bjackres/images/" + BlackJack.imgNames[i] + ".gif";
            this.buttonImg[i] = this.getImageResource(this.tempStr);
            this.tracker.addImage(this.buttonImg[i], i);
            this.tracker.checkID(i, true);
        }
        for (int j = 0; j < 12; ++j) {
            try {
                if (!this.tracker.checkID(j)) {
                    this.tracker.waitForID(j);
                }
            }
            catch (InterruptedException ex2) {}
        }
        this.add("South", this.controls = new Controls(this));
        this.start();
        for (int k = 0; k < 4; ++k) {
            for (int l = 1; l < 14; ++l) {
                this.tempStr = "/bjackres/images/" + k + "-" + l + ".gif";
                this.cardImg[k][l] = this.getImageResource(this.tempStr);
                if (this.cardImg[k][l] == null) {
                    System.err.println("Image " + k + "-" + l + " not found.");
                }
                this.tracker.addImage(this.cardImg[k][l], k * 13 + l + 12);
                this.tracker.checkID(k * 13 + l + 12, true);
            }
        }
        for (int n2 = 12; n2 < 64; ++n2) {
            try {
                if (!this.tracker.checkID(n2)) {
                    this.tracker.waitForID(n2);
                }
            }
            catch (InterruptedException ex3) {}
        }
        this.showStartUpImage = true;
        this.repaint();
        this.font = new Font("Courier", 0, 14);
        this.msgFont = new Font("Helvetica", 1, 18);
        this.smFont = new Font("Courier", 0, 12);
        this.fm = this.getFontMetrics(this.font);
        this.mfm = this.getFontMetrics(this.msgFont);
        this.fontHeight = this.fm.getHeight();
        this.msgFontHeight = this.mfm.getHeight();
        this.resetGame();
    }
    
    protected void resetGame() {
        this.dollars = 500;
        this.deck = new Deck(6);
        this.controls.enableButton(0);
        this.controls.disableButton(1);
        this.controls.disableButton(2);
        this.controls.disableButton(3);
        this.repaint();
    }
    
    protected void deal() {
        if (!this.canDeal) {
            this.showMsg("Please finish this game first!");
        }
        else {
            this.canDeal = false;
            this.controls.disableButton(0);
            this.canDouble = true;
            this.controls.enableButton(3);
            if (this.dollars <= 0) {
                final boolean b = false;
                this.canDeal = b;
                this.canDouble = b;
                this.canStand = b;
                this.canHit = b;
                this.controls.disableButton(0);
                this.controls.disableButton(1);
                this.controls.disableButton(2);
                this.controls.disableButton(3);
            }
            else if (this.playerBet > this.dollars) {
                this.controls.enableBet();
                this.showMsg("Can't bet more than what you have!");
                final boolean canHit = false;
                this.canDouble = canHit;
                this.canStand = canHit;
                this.canHit = canHit;
                this.controls.disableButton(1);
                this.controls.disableButton(2);
                this.controls.disableButton(3);
                this.canDeal = true;
                this.controls.enableButton(0);
            }
            else {
                this.controls.disableBet();
                this.showStartUpImage = false;
                this.msgImage = 7;
                final boolean b2 = true;
                this.canStand = b2;
                this.canHit = b2;
                this.controls.enableButton(1);
                this.controls.enableButton(2);
                this.showBlank = false;
                final boolean b3 = false;
                this.playerScore = (b3 ? 1 : 0);
                this.dealerScore = (b3 ? 1 : 0);
                this.whoWon = 4;
                this.dealer = new Vector(10);
                this.player = new Vector(10);
                for (int i = 0; i < 2; ++i) {
                    if (this.deck.cardsLeft() != 0) {
                        this.dealer.addElement(this.deck.getNextCard());
                        this.player.addElement(this.deck.getNextCard());
                    }
                    else {
                        this.deck = new Deck(6);
                        this.player.addElement(this.deck.getNextCard());
                        this.dealer.addElement(this.deck.getNextCard());
                    }
                }
                this.playerScore = this.getScore(0);
                if (this.playerScore == 21 && this.whoWon == 3) {
                    final boolean canHit2 = false;
                    this.canDouble = canHit2;
                    this.canStand = canHit2;
                    this.canHit = canHit2;
                    this.controls.disableButton(1);
                    this.controls.disableButton(2);
                    this.controls.disableButton(3);
                    this.canDeal = true;
                    this.controls.enableButton(0);
                    this.dollars += 2 * this.playerBet;
                    this.msgImage = 8;
                    this.controls.enableBet();
                }
            }
        }
        this.repaint();
    }
    
    protected void hit() {
        if (this.canHit && this.dealer != null && this.player != null) {
            this.controls.disableBet();
            this.msgImage = 7;
            this.canDouble = false;
            this.dealNextCard(0);
            this.playerScore = this.getScore(0);
            if (this.playerScore > 21) {
                this.canDeal = true;
                this.controls.enableButton(0);
                final boolean b = false;
                this.canStand = b;
                this.canHit = b;
                this.controls.disableButton(1);
                this.controls.disableButton(2);
                this.whoWon = 1;
                this.msgImage = 9;
                this.controls.enableBet();
            }
            this.adjustAmounts(1);
        }
        this.repaint();
    }
    
    protected void playDouble() {
        if (this.canDouble && this.player != null) {
            this.controls.disableBet();
            final boolean canStand = false;
            this.canDouble = canStand;
            this.canHit = canStand;
            this.canStand = canStand;
            this.controls.disableButton(1);
            this.controls.disableButton(2);
            this.controls.disableButton(3);
            this.showBlank = true;
            this.msgImage = 7;
            this.dealNextCard(0);
            this.playerScore = this.getScore(0);
            if (this.playerScore > 21) {
                this.whoWon = 1;
                this.msgImage = 9;
                this.controls.enableBet();
            }
            while (true) {
                this.dealerScore = this.getScore(1);
                if ((this.dealerScore > this.playerScore && this.dealerScore > 17) || this.dealerScore >= 17) {
                    break;
                }
                this.dealNextCard(1);
            }
            if (this.dealerScore < this.playerScore && this.playerScore <= 21) {
                this.msgImage = 11;
                this.whoWon = 0;
                this.controls.enableBet();
            }
            else if (this.dealerScore == this.playerScore) {
                this.msgImage = 10;
                this.controls.enableBet();
            }
            else if (this.dealerScore > 21) {
                this.msgImage = 11;
                this.whoWon = 0;
                this.controls.enableBet();
            }
            else if (this.dealerScore > this.playerScore) {
                this.msgImage = 9;
                this.whoWon = 1;
                this.controls.enableBet();
            }
            else if (this.playerScore > 21) {
                this.whoWon = 1;
                this.msgImage = 9;
                this.controls.enableBet();
            }
            this.adjustAmounts(2);
            this.canDeal = true;
            this.controls.enableButton(0);
        }
        this.repaint();
    }
    
    protected void stand() {
        if (this.canStand && this.player != null) {
            this.controls.disableBet();
            final boolean canHit = false;
            this.canDouble = canHit;
            this.canStand = canHit;
            this.canHit = canHit;
            this.controls.disableButton(1);
            this.controls.disableButton(2);
            this.controls.disableButton(3);
            this.showBlank = true;
            while (true) {
                this.dealerScore = this.getScore(1);
                if ((this.dealerScore > this.playerScore && this.dealerScore > 17) || this.dealerScore >= 17) {
                    break;
                }
                this.dealNextCard(1);
            }
            if (this.dealerScore < this.playerScore) {
                this.msgImage = 11;
                this.whoWon = 0;
                this.controls.enableBet();
            }
            else if (this.dealerScore == this.playerScore) {
                this.msgImage = 10;
                this.controls.enableBet();
            }
            else if (this.dealerScore > 21) {
                this.msgImage = 11;
                this.whoWon = 0;
                this.controls.enableBet();
            }
            else if (this.dealerScore > this.playerScore) {
                this.msgImage = 9;
                this.whoWon = 1;
                this.controls.enableBet();
            }
            this.adjustAmounts(1);
            this.canDeal = true;
            this.controls.enableButton(0);
        }
        this.repaint();
    }
    
    private void dealNextCard(final int n) {
        if (this.deck.cardsLeft() != 0) {
            if (n == 0) {
                this.player.addElement(this.deck.getNextCard());
                return;
            }
            this.dealer.addElement(this.deck.getNextCard());
        }
        else {
            this.deck = new Deck(6);
            if (n == 0) {
                this.player.addElement(this.deck.getNextCard());
                return;
            }
            this.dealer.addElement(this.deck.getNextCard());
        }
    }
    
    private void adjustAmounts(final int n) {
        if (this.whoWon == 0) {
            this.dollars += n * this.playerBet;
            return;
        }
        if (this.whoWon == 1) {
            this.dollars -= n * this.playerBet;
        }
    }
    
    private int getScore(final int n) {
        int n2 = 0;
        int n3 = 0;
        if (n == 0) {
            for (int i = 0; i < this.player.size(); ++i) {
                this.card = (Card)this.player.elementAt(i);
                if (this.card.getCardNum() == 0) {
                    ++n2;
                }
                n3 += this.card.getCardValue();
            }
            if (n3 == 21 && this.player.size() == 2) {
                this.whoWon = 3;
            }
        }
        else {
            for (int j = 0; j < this.dealer.size(); ++j) {
                this.card = (Card)this.dealer.elementAt(j);
                if (this.card.getCardNum() == 0) {
                    ++n2;
                }
                n3 += this.card.getCardValue();
            }
        }
        while (n3 > 21 && n2 > 0) {
            n3 -= 10;
            --n2;
        }
        return n3;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.im == null) {
            this.r = this.getBounds();
            if (this.r.width <= 0 || this.r.height <= 0) {
                return;
            }
            try {
                this.im = this.createImage(this.r.width, this.r.height - this.controls.getBounds().height);
                this.offscreen = this.im.getGraphics();
                return;
            }
            catch (Exception ex) {
                this.offscreen = null;
                return;
            }
        }
        if (this.offscreen != null) {
            this.real_paint(this.offscreen);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.real_paint(graphics);
    }
    
    protected void showMsg(final String resultMsg) {
        this.resultMsg = resultMsg;
        this.showStatMsg = true;
        this.repaint();
    }
    
    private void real_paint(final Graphics graphics) {
        final int width = this.blankCard.getWidth(this);
        final int height = this.blankCard.getHeight(this);
        final int width2 = this.buttonImg[7].getWidth(this);
        final int height2 = this.buttonImg[7].getHeight(this);
        this.r = this.getBounds();
        this.rX = this.r.width;
        this.rY = this.r.height - this.controls.getBounds().height;
        int n = 10;
        int n2 = 10;
        final int n3 = this.rY - height - 40;
        final int n4 = this.rY / 2 - height - 40;
        final int n5 = this.rY - this.pMsgW;
        final int n6 = this.rY / 2 - this.pMsgW;
        graphics.setColor(Color.black);
        graphics.draw3DRect(0, 0, this.rX - 2, this.rY - 2, false);
        graphics.setColor(this.bgColor);
        graphics.fillRect(2, 2, this.rX - 5, this.rY - 5);
        final int n7 = this.rY / 2;
        if (this.showErrMsg) {
            this.showErrMsg = false;
            this.stW = this.mfm.stringWidth("AdBanner was not found. BlackJack not started.");
            graphics.setFont(this.msgFont);
            graphics.drawString("AdBanner was not found. BlackJack not started.", (this.rX - this.stW) / 2, (this.rY + this.msgFontHeight) / 2 - 5);
            final int n8 = this.rY / 2;
            graphics.drawLine(10, n8, (this.rX - this.stW) / 2 - 10, n8);
            graphics.drawLine((this.rX + this.stW) / 2 + 10, n8, this.rX - 10, n8);
            return;
        }
        if (this.showStartUpImage) {
            graphics.setColor(this.fgColor);
            graphics.drawLine(10, n7, this.rX - 10, n7);
            graphics.drawImage(this.buttonImg[7], (this.rX - width2) / 2, n7 - height2 / 2, this);
        }
        if (this.dealer != null && this.player != null) {
            graphics.setFont(this.font);
            if (!this.showBlank) {
                this.faceless = this.dealer.elementAt(0);
                graphics.drawImage(this.blankCard, n, n4, this);
                graphics.setColor(Color.white);
                this.card = this.dealer.elementAt(1);
                graphics.drawString("Dealer shows " + String.valueOf(this.card.getCardValue()), 10, n6);
            }
            else {
                graphics.setColor(Color.white);
                graphics.drawString("Dealer shows " + String.valueOf(this.dealerScore), 10, n6);
                graphics.drawImage(this.cardImg[this.faceless.getSuite()][this.faceless.getCardNum() + 1], n, n4, this);
            }
            for (int i = 1; i < this.dealer.size(); ++i) {
                this.card = (Card)this.dealer.elementAt(i);
                final int suite = this.card.getSuite();
                n += width + 8;
                graphics.drawImage(this.cardImg[suite][this.card.getCardNum() + 1], n, n4, this);
            }
            for (int j = 0; j < this.player.size(); ++j) {
                this.card = (Card)this.player.elementAt(j);
                graphics.drawImage(this.cardImg[this.card.getSuite()][this.card.getCardNum() + 1], n2, n3, this);
                n2 += width + 8;
            }
            graphics.setColor(Color.white);
            graphics.drawString("You hold " + String.valueOf(this.playerScore), 10, n5);
            final int n9 = n5 - this.fontHeight + 2;
            this.stW = this.fm.stringWidth(" Bank  $" + String.valueOf(this.dollars) + " ");
            final int n10 = this.rX - this.stW - 10;
            this.bankWidth = this.fm.stringWidth(" Bank ");
            graphics.setColor(Color.black);
            graphics.fillRect(n10, n9 + 1, this.bankWidth, 20);
            graphics.setColor(this.fgColor);
            graphics.fillRect(n10 + this.bankWidth, n9 + 1, this.stW - this.bankWidth, 20);
            graphics.drawString(" Bank ", n10, n5);
            graphics.setColor(Color.black);
            graphics.drawString(" $" + String.valueOf(this.dollars) + " ", n10 + this.bankWidth, n5);
            graphics.setColor(this.fgColor);
            if (this.showStatMsg) {
                this.showStatMsg = false;
                this.stW = this.mfm.stringWidth(this.resultMsg);
                graphics.setFont(this.msgFont);
                graphics.drawString(this.resultMsg, (this.rX - this.stW) / 2, (this.rY + this.msgFontHeight) / 2 - 5);
                final int n11 = this.rY / 2;
                graphics.drawLine(10, n11, (this.rX - this.stW) / 2 - 10, n11);
                graphics.drawLine((this.rX + this.stW) / 2 + 10, n11, this.rX - 10, n11);
                return;
            }
            final int n12 = this.rY / 2;
            graphics.drawLine(10, n12, this.rX - 10, n12);
            graphics.drawImage(this.buttonImg[this.msgImage], (this.rX - width2) / 2, n12 - height2 / 2, this);
        }
    }
    
    private Image getImageResource(final String s) {
        Image image = null;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream == null) {
                System.err.println("Image " + s + " not found.");
                return null;
            }
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {
            System.err.println("Unable to read image: " + s);
            ex.printStackTrace();
        }
        return image;
    }
    
    public BlackJack() {
        this.cardImg = new Image[4][14];
        this.buttonImg = new Image[12];
        this.showBlank = false;
        this.canHit = true;
        this.canStand = true;
        this.canDeal = true;
        this.canDouble = true;
        this.showStartUpImage = false;
        this.showStatMsg = false;
        this.linkActive = false;
        this.whoWon = 4;
        this.bgColor = new Color(0, 99, 0);
        this.urlColor = new Color(33, 142, 33);
        this.fgColor = new Color(153, 204, 0);
        this.dollars = 500;
        this.urlRegion = new Rectangle(0, 0, 0, 0);
        this.pMsgW = 20;
        this.tempStr = "";
        this.showErrMsg = false;
    }
    
    static {
        imgNames = new String[] { "deal", "hitme", "stand", "double", "reset", "wager", "bankrupt", "bjtable", "blakjack", "dealerw", "push", "youwin" };
    }
}
