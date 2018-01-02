// 
// Decompiled by Procyon v0.5.30
// 

package com.josesandoval.magic;

import java.awt.Insets;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.beans.Beans;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.MediaTracker;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Image;
import java.applet.Applet;

public class MagicTrick extends Applet
{
    Image[] cardImages;
    private Image deckImage;
    private Panel ivjPanelCards;
    private Panel ivjPanelMenu;
    IvjEventHandler ivjEventHandler;
    private Button ivjButtonPile1;
    private Button ivjButtonPile2;
    private Button ivjButtonPile3;
    private Panel ivjPanelBegin;
    private FlowLayout ivjPanelBeginFlowLayout;
    private Deck deck;
    private Card[] playCards;
    private Card[] pile1;
    private Card[] pile2;
    private Card[] pile3;
    private Hashtable cardTable;
    private int countShuffles;
    private static final int xBegin = 10;
    private static final int yBegin = 10;
    private static final int cardWidth = 73;
    private static final int cardHeight = 107;
    private Button ivjButtonStart;
    private Image backCard;
    private Image offScreenBuffer;
    
    public MagicTrick() {
        this.ivjPanelCards = null;
        this.ivjPanelMenu = null;
        this.ivjEventHandler = new IvjEventHandler();
        this.ivjButtonPile1 = null;
        this.ivjButtonPile2 = null;
        this.ivjButtonPile3 = null;
        this.ivjPanelBegin = null;
        this.ivjPanelBeginFlowLayout = null;
        this.ivjButtonStart = null;
    }
    
    public void buttonPile1_ActionPerformed(final ActionEvent actionEvent) {
        this.doSelectPile1();
        this.doDisplayPiles(this.getPanelCards().getGraphics());
        this.doCheckCountShuffles();
    }
    
    public void buttonPile2_ActionPerformed(final ActionEvent actionEvent) {
        this.doSelectPile2();
        this.doDisplayPiles(this.getPanelCards().getGraphics());
        this.doCheckCountShuffles();
    }
    
    public void buttonPile3_ActionPerformed(final ActionEvent actionEvent) {
        this.doSelectPile3();
        this.doDisplayPiles(this.getPanelCards().getGraphics());
        this.doCheckCountShuffles();
    }
    
    public void buttonStart_ActionPerformed(final ActionEvent actionEvent) {
        this.doResetToDeal();
        this.doDisplayPiles(this.getPanelCards().getGraphics());
        this.getButtonStart().setEnabled(false);
        this.getButtonPile1().setEnabled(true);
        this.getButtonPile2().setEnabled(true);
        this.getButtonPile3().setEnabled(true);
        this.doDisplayStatus(this.getPanelCards().getGraphics(), "Pick a card and click the button beside the pile it is in.");
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            ++this.countShuffles;
            this.buttonPile1_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC3(final ActionEvent actionEvent) {
        try {
            ++this.countShuffles;
            this.buttonPile2_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC4(final ActionEvent actionEvent) {
        try {
            ++this.countShuffles;
            this.buttonPile3_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC5(final ActionEvent actionEvent) {
        try {
            this.buttonStart_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void doCheckCountShuffles() {
        if (this.countShuffles == 3) {
            this.countShuffles = 0;
            this.doDisplayAllBack(this.getPanelCards().getGraphics());
            this.doDisplayChoosenCard(this.getPanelCards().getGraphics());
            this.doDisplayStatus(this.getPanelCards().getGraphics(), "This is the card you picked.");
            this.getButtonStart().setEnabled(true);
            this.getButtonPile1().setEnabled(false);
            this.getButtonPile2().setEnabled(false);
            this.getButtonPile3().setEnabled(false);
            this.initTrick();
        }
        else {
            this.doDisplayStatus(this.getPanelCards().getGraphics(), "Which pile is your card in?");
        }
    }
    
    private void doDisplayAllBack(final Graphics graphics) {
        graphics.clearRect(0, 0, this.getPanelCards().getSize().width, this.getPanelCards().getSize().height);
        for (int i = 0; i < 7; ++i) {
            graphics.drawImage(this.backCard, 10 + 73 * i, 10, null);
            graphics.drawImage(this.backCard, 10 + 73 * i, 117, null);
            graphics.drawImage(this.backCard, 10 + 73 * i, 224, null);
        }
    }
    
    private void doDisplayChoosenCard(final Graphics graphics) {
        graphics.drawImage(this.cardTable.get("" + this.playCards[10].getSuit() + "_" + this.playCards[10].getValue()), 10, 10, null);
    }
    
    private void doDisplayIntro(final Graphics graphics) {
    }
    
    private void doDisplayPiles(final Graphics graphics) {
        graphics.clearRect(0, 0, this.getPanelCards().getSize().width, this.getPanelCards().getSize().height);
        for (int i = 0; i < 7; ++i) {
            graphics.drawImage((Image)this.cardTable.get("" + this.pile1[i].getSuit() + "_" + this.pile1[i].getValue()), 10 + 73 * i, 10, null);
            graphics.drawImage((Image)this.cardTable.get("" + this.pile2[i].getSuit() + "_" + this.pile2[i].getValue()), 10 + 73 * i, 117, null);
            graphics.drawImage((Image)this.cardTable.get("" + this.pile3[i].getSuit() + "_" + this.pile3[i].getValue()), 10 + 73 * i, 224, null);
        }
    }
    
    private void doDisplayStatus(final Graphics graphics, final String s) {
        graphics.setFont(new Font("Helvetica", 1, 16));
        graphics.setColor(Color.orange);
        graphics.drawString(s, 40, 360);
    }
    
    private void doResetToDeal() {
        for (int i = 0; i < 7; ++i) {
            this.playCards[i] = this.pile1[i];
            this.playCards[i + 7] = this.pile2[i];
            this.playCards[i + 14] = this.pile3[i];
        }
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int j = this.playCards.length - 1; j >= 0; --j) {
            switch ((j + 1) % 3) {
                case 0: {
                    this.pile1[n] = this.playCards[j];
                    ++n;
                    break;
                }
                case 1: {
                    this.pile3[n2] = this.playCards[j];
                    ++n2;
                    break;
                }
                case 2: {
                    this.pile2[n3] = this.playCards[j];
                    ++n3;
                    break;
                }
            }
        }
    }
    
    private void doSelectPile1() {
        final Card[] array = new Card[7];
        final Card[] pile1 = this.pile1;
        this.pile1 = this.pile2;
        this.pile2 = pile1;
        this.doResetToDeal();
    }
    
    private void doSelectPile2() {
        this.doResetToDeal();
    }
    
    private void doSelectPile3() {
        final Card[] array = new Card[7];
        final Card[] pile2 = this.pile2;
        this.pile2 = this.pile3;
        this.pile3 = pile2;
        this.doResetToDeal();
    }
    
    public String getAppletInfo() {
        return "MagicTrick\n\nThis trick was taught to me, when I was around 10 years old, by my father.\nCreation date: (25/12/2002 3:47:30 PM)\n@author: Jose Sandoval\n";
    }
    
    private Button getButtonPile1() {
        if (this.ivjButtonPile1 == null) {
            try {
                (this.ivjButtonPile1 = new Button()).setName("ButtonPile1");
                this.ivjButtonPile1.setBounds(540, 50, 45, 23);
                this.ivjButtonPile1.setEnabled(false);
                this.ivjButtonPile1.setLabel("Pile 1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonPile1;
    }
    
    private Button getButtonPile2() {
        if (this.ivjButtonPile2 == null) {
            try {
                (this.ivjButtonPile2 = new Button()).setName("ButtonPile2");
                this.ivjButtonPile2.setBounds(540, 155, 45, 23);
                this.ivjButtonPile2.setEnabled(false);
                this.ivjButtonPile2.setLabel("Pile 2");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonPile2;
    }
    
    private Button getButtonPile3() {
        if (this.ivjButtonPile3 == null) {
            try {
                (this.ivjButtonPile3 = new Button()).setName("ButtonPile3");
                this.ivjButtonPile3.setBounds(540, 260, 45, 23);
                this.ivjButtonPile3.setEnabled(false);
                this.ivjButtonPile3.setLabel("Pile 3");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonPile3;
    }
    
    private Button getButtonStart() {
        if (this.ivjButtonStart == null) {
            try {
                (this.ivjButtonStart = new Button()).setName("ButtonStart");
                this.ivjButtonStart.setLabel("Play Game");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonStart;
    }
    
    private Panel getPanelBegin() {
        if (this.ivjPanelBegin == null) {
            try {
                (this.ivjPanelBegin = new Panel()).setName("PanelBegin");
                this.ivjPanelBegin.setLayout(this.getPanelBeginFlowLayout());
                this.ivjPanelBegin.setBackground(Color.gray);
                this.getPanelBegin().add(this.getButtonStart(), this.getButtonStart().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelBegin;
    }
    
    private FlowLayout getPanelBeginFlowLayout() {
        FlowLayout flowLayout = null;
        try {
            flowLayout = new FlowLayout();
            flowLayout.setAlignment(2);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return flowLayout;
    }
    
    private Panel getPanelCards() {
        if (this.ivjPanelCards == null) {
            try {
                (this.ivjPanelCards = new Panel()).setName("PanelCards");
                this.ivjPanelCards.setLayout(null);
                this.ivjPanelCards.setBackground(new Color(25, 105, 18));
                this.getPanelCards().add(this.getButtonPile1(), this.getButtonPile1().getName());
                this.getPanelCards().add(this.getButtonPile2(), this.getButtonPile2().getName());
                this.getPanelCards().add(this.getButtonPile3(), this.getButtonPile3().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelCards;
    }
    
    private Panel getPanelMenu() {
        if (this.ivjPanelMenu == null) {
            try {
                (this.ivjPanelMenu = new Panel()).setName("PanelMenu");
                this.ivjPanelMenu.setLayout(new GridBagLayout());
                final GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 13;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                this.getPanelMenu().add(this.getPanelBegin(), gridBagConstraints);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelMenu;
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void init() {
        try {
            super.init();
            this.setName("MagicTrick");
            this.setLayout(new BorderLayout());
            this.setSize(600, 420);
            this.add(this.getPanelCards(), "Center");
            this.add(this.getPanelMenu(), "South");
            this.initConnections();
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.playCards = new Card[21];
            this.pile1 = new Card[7];
            this.pile2 = new Card[7];
            this.pile3 = new Card[7];
            this.deck = new Deck();
            this.initTrick();
            final Image image = this.getImage(this.getCodeBase(), "images/cards.gif");
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.cardTable = new Hashtable();
            int n = 0;
            for (int i = 0; i < 4; ++i) {
                for (int j = 1; j <= 13; ++j) {
                    final Image image2 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(73 * j, 97 * i, 73, 97)));
                    mediaTracker.addImage(image2, ++n);
                    this.cardTable.put("" + i + "_" + j, image2);
                }
            }
            mediaTracker.addImage(this.backCard = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(0, 97, 73, 97))), ++n);
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void initConnections() throws Exception {
        this.getButtonPile1().addActionListener(this.ivjEventHandler);
        this.getButtonPile2().addActionListener(this.ivjEventHandler);
        this.getButtonPile3().addActionListener(this.ivjEventHandler);
        this.getButtonStart().addActionListener(this.ivjEventHandler);
    }
    
    private void initTrick() {
        this.countShuffles = 0;
        this.deck.shuffle();
        for (int i = 0; i < 7; ++i) {
            this.pile1[i] = this.deck.getDeck()[i];
            this.pile2[i] = this.deck.getDeck()[i + 7];
            this.pile3[i] = this.deck.getDeck()[i + 14];
        }
    }
    
    public static void main(final String[] array) {
        try {
            final Frame frame = new Frame();
            final MagicTrick magicTrick = (MagicTrick)Beans.instantiate(Class.forName("com.josesandoval.magic.MagicTrick").getClassLoader(), "com.josesandoval.magic.MagicTrick");
            frame.add("Center", magicTrick);
            frame.setSize(magicTrick.getSize());
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
            frame.show();
            final Insets insets = frame.getInsets();
            frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
            frame.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
            t.printStackTrace(System.out);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class IvjEventHandler implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == MagicTrick.this.getButtonPile1()) {
                MagicTrick.this.connEtoC2(actionEvent);
            }
            if (actionEvent.getSource() == MagicTrick.this.getButtonPile2()) {
                MagicTrick.this.connEtoC3(actionEvent);
            }
            if (actionEvent.getSource() == MagicTrick.this.getButtonPile3()) {
                MagicTrick.this.connEtoC4(actionEvent);
            }
            if (actionEvent.getSource() == MagicTrick.this.getButtonStart()) {
                MagicTrick.this.connEtoC5(actionEvent);
            }
        }
    }
}
