import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Label;
import java.awt.Cursor;
import java.awt.Button;
import java.util.Random;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VJpoker extends Applet
{
    private Graphics gBuffer;
    public MediaTracker tracker;
    public Image Buffer;
    public Image[] Card_img;
    private Font fnt_Game;
    private Random rand;
    public Card[] card_Deck;
    public Card[] hand;
    public CardImage[] img_Card;
    public Button btn_Deal;
    public Button btn_Betone;
    public Button btn_Betmax;
    public Button btn_Pass;
    private Cursor cur_Hand;
    private VJlabel[] pay_Cats;
    private ButtonHandler Bhandler;
    public VJbutton[] btn_Bet;
    private int[] val_Cats;
    public int draw_Num;
    public int bet_Column;
    public int bet_Value;
    public int balance;
    public int paid_Out;
    public Label lbl_Balance;
    public Label lbl_Paid;
    public Label lbl_Bet;
    public AudioClip win;
    public AudioClip sh;
    public AudioClip click;
    
    public VJpoker() {
        this.val_Cats = new int[] { 250, 50, 25, 8, 6, 4, 3, 2, 1 };
        this.draw_Num = 1;
        this.bet_Column = 1;
        this.bet_Value = 1;
        this.balance = 1000;
        this.lbl_Balance = new Label();
        this.lbl_Paid = new Label();
        this.lbl_Bet = new Label();
        this.fnt_Game = new Font("Courier", 1, 14);
        this.cur_Hand = new Cursor(12);
        this.card_Deck = new Card[52];
        this.pay_Cats = new VJlabel[45];
        this.hand = new Card[5];
        this.Card_img = new Image[53];
        this.btn_Bet = new VJbutton[3];
        this.img_Card = new CardImage[5];
        this.btn_Deal = new Button("DEAL");
        this.btn_Betone = new Button("BET ONE");
        this.btn_Betmax = new Button("BET MAX");
        this.btn_Pass = new Button("TAKE");
        this.rand = new Random();
        this.Bhandler = new ButtonHandler(this);
    }
    
    public void init() {
        this.Buffer = this.createImage(this.getSize().width, this.getSize().height);
        this.gBuffer = this.Buffer.getGraphics();
        this.load_Images();
        this.load_Sounds();
        this.lbl_Balance.setBackground(Color.black);
        this.lbl_Paid.setBackground(Color.black);
        this.lbl_Bet.setBackground(Color.black);
        this.lbl_Balance.setForeground(Color.cyan);
        this.lbl_Paid.setForeground(Color.cyan);
        this.lbl_Bet.setForeground(Color.cyan);
        this.lbl_Balance.setFont(this.fnt_Game);
        this.lbl_Bet.setFont(this.fnt_Game);
        this.lbl_Paid.setFont(this.fnt_Game);
        for (int i = 0; i < this.img_Card.length; ++i) {
            (this.img_Card[i] = new CardImage(this)).addMouseListener(this.img_Card[i]);
            this.img_Card[i].setCursor(this.cur_Hand);
            this.img_Card[i].setVisible(true);
            this.add(this.img_Card[i]);
        }
        (this.btn_Bet[0] = new VJbutton(this, 1, true)).addMouseListener(this.btn_Bet[0]);
        this.add(this.btn_Bet[0]);
        (this.btn_Bet[1] = new VJbutton(this, 5, false)).addMouseListener(this.btn_Bet[1]);
        this.add(this.btn_Bet[1]);
        (this.btn_Bet[2] = new VJbutton(this, 10, false)).addMouseListener(this.btn_Bet[2]);
        this.add(this.btn_Bet[2]);
        for (int j = 0; j < this.pay_Cats.length; ++j) {
            (this.pay_Cats[j] = new VJlabel()).setFont(this.fnt_Game);
            if (j < 9) {
                this.pay_Cats[j].setColumn(1);
            }
            else if (j < 18) {
                this.pay_Cats[j].setColumn(2);
            }
            else if (j < 27) {
                this.pay_Cats[j].setColumn(3);
            }
            else if (j < 36) {
                this.pay_Cats[j].setColumn(4);
            }
            else {
                this.pay_Cats[j].setColumn(5);
            }
            this.add(this.pay_Cats[j]);
        }
        for (int k = 0; k < this.pay_Cats.length; ++k) {
            if (k % 2 == 1) {
                if (this.pay_Cats[k].getColumn() == 1) {
                    this.pay_Cats[k].setBackground(new Color(0, 255, 255));
                }
                else {
                    this.pay_Cats[k].setBackground(Color.white);
                }
            }
            else if (this.pay_Cats[k].getColumn() == 1) {
                this.pay_Cats[k].setBackground(new Color(0, 200, 255));
            }
            else {
                this.pay_Cats[k].setBackground(Color.lightGray);
            }
        }
        for (int l = 0; l < this.hand.length; ++l) {
            this.hand[l] = new Card();
        }
        for (int n = 0; n < this.card_Deck.length; ++n) {
            this.card_Deck[n] = new Card();
            if (n < 13) {
                this.card_Deck[n].setFace(n + 2);
                this.card_Deck[n].setSuit('H');
            }
            else if (n < 26) {
                this.card_Deck[n].setFace(n - 11);
                this.card_Deck[n].setSuit('S');
            }
            else if (n < 39) {
                this.card_Deck[n].setFace(n - 24);
                this.card_Deck[n].setSuit('D');
            }
            else {
                this.card_Deck[n].setFace(n - 37);
                this.card_Deck[n].setSuit('C');
            }
        }
        this.btn_Deal.addActionListener(this.Bhandler);
        this.btn_Betone.addActionListener(this.Bhandler);
        this.btn_Betmax.addActionListener(this.Bhandler);
        this.btn_Pass.addActionListener(this.Bhandler);
        this.btn_Pass.setEnabled(false);
        this.add(this.lbl_Balance);
        this.add(this.lbl_Bet);
        this.add(this.lbl_Paid);
        this.add(this.btn_Deal);
        this.add(this.btn_Betone);
        this.add(this.btn_Betmax);
        this.add(this.btn_Pass);
        this.setLayout(null);
        int n2 = 170;
        int n3 = 10;
        int n4 = 0;
        for (int n5 = 0; n5 < this.pay_Cats.length; ++n5) {
            int n6 = this.val_Cats[n4];
            this.pay_Cats[n5].setBounds(n2, n3, 50, 20);
            if (n5 > 8 && n5 < 18) {
                n6 *= 2;
            }
            else if (n5 > 17 && n5 < 27) {
                n6 *= 3;
            }
            else if (n5 > 26 && n5 < 36) {
                n6 *= 4;
            }
            else if (n5 > 35) {
                n6 *= 5;
            }
            if (n6 > 0 && n6 < 10) {
                this.pay_Cats[n5].setText("  " + String.valueOf(n6));
            }
            else if (n6 > 9 && n6 < 99) {
                this.pay_Cats[n5].setText("  " + String.valueOf(n6));
            }
            else if (n6 > 99 && n6 < 1000) {
                this.pay_Cats[n5].setText(" " + String.valueOf(n6));
            }
            else {
                this.pay_Cats[n5].setText(" " + String.valueOf(n6));
            }
            n3 += 21;
            ++n4;
            if (n5 == 8 || n5 == 17 || n5 == 26 || n5 == 35) {
                n2 += 51;
                n3 = 10;
                n4 = 0;
            }
        }
        this.lbl_Balance.setBounds(115, 225, 70, 20);
        this.lbl_Balance.setText("999");
        this.lbl_Paid.setBounds(274, 225, 50, 20);
        this.lbl_Paid.setText("0");
        this.lbl_Bet.setBounds(407, 225, 30, 20);
        this.lbl_Bet.setText("1");
        int n7 = 20;
        for (int n8 = 0; n8 < this.img_Card.length; ++n8) {
            this.img_Card[n8].setBounds(n7, 272, 75, 100);
            n7 += 91;
        }
        this.btn_Bet[0].setBounds(10, 398, 40, 30);
        this.btn_Bet[1].setBounds(55, 398, 40, 30);
        this.btn_Bet[2].setBounds(100, 398, 40, 30);
        this.btn_Betone.setBounds(215, 398, 60, 30);
        this.btn_Betmax.setBounds(280, 398, 60, 30);
        this.btn_Pass.setBounds(345, 398, 60, 30);
        this.btn_Deal.setBounds(410, 398, 60, 30);
        this.showStatus("Video Poker ver 1.1, Copyright© 2002 pschmandra@hotmail.com");
    }
    
    public boolean royalflush() {
        boolean b = true;
        for (int i = 1; i < this.hand.length; ++i) {
            if (this.hand[i].getSuit() != this.hand[0].getSuit()) {
                b = false;
            }
        }
        for (int j = 11; j < 15; ++j) {
            if (!this.findNum(j)) {
                b = false;
            }
        }
        return b;
    }
    
    public boolean straightflush() {
        boolean b = true;
        for (int i = 1; i < this.hand.length; ++i) {
            if (this.hand[i].getSuit() != this.hand[0].getSuit()) {
                b = false;
            }
        }
        this.Sort();
        if (this.hand[1].getFace() - 1 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[2].getFace() - 2 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[3].getFace() - 3 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[4].getFace() - 4 != this.hand[0].getFace()) {
            b = false;
        }
        return b;
    }
    
    public boolean fourofakind() {
        boolean b = false;
        for (int i = 0; i < 2; ++i) {
            if (this.hand[i].getFace() == this.hand[i + 1].getFace() && this.hand[i].getFace() == this.hand[i + 2].getFace() && this.hand[i].getFace() == this.hand[i + 3].getFace()) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean fullhouse() {
        boolean b = false;
        if (this.hand[0].getFace() == this.hand[1].getFace() && this.hand[2].getFace() == this.hand[3].getFace() && this.hand[2].getFace() == this.hand[4].getFace()) {
            b = true;
        }
        else if (this.hand[0].getFace() == this.hand[1].getFace() && this.hand[0].getFace() == this.hand[2].getFace() && this.hand[3].getFace() == this.hand[4].getFace()) {
            b = true;
        }
        return b;
    }
    
    public boolean flush() {
        boolean b = true;
        for (int i = 1; i < this.hand.length; ++i) {
            if (this.hand[i].getSuit() != this.hand[0].getSuit()) {
                b = false;
            }
        }
        return b;
    }
    
    public boolean straight() {
        boolean b = true;
        this.Sort();
        if (this.hand[1].getFace() - 1 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[2].getFace() - 2 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[3].getFace() - 3 != this.hand[0].getFace()) {
            b = false;
        }
        if (this.hand[4].getFace() - 4 != this.hand[0].getFace()) {
            b = false;
        }
        return b;
    }
    
    public boolean threeofakind() {
        boolean b = false;
        for (int i = 0; i < 3; ++i) {
            if (this.hand[i].getFace() == this.hand[i + 1].getFace() && this.hand[i].getFace() == this.hand[i + 2].getFace()) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean twopair() {
        boolean b = false;
        if (this.hand[0].getFace() == this.hand[1].getFace() && this.hand[2].getFace() == this.hand[3].getFace()) {
            b = true;
        }
        else if (this.hand[1].getFace() == this.hand[2].getFace() && this.hand[3].getFace() == this.hand[4].getFace()) {
            b = true;
        }
        else if (this.hand[0].getFace() == this.hand[1].getFace() && this.hand[3].getFace() == this.hand[4].getFace()) {
            b = true;
        }
        return b;
    }
    
    public boolean jacksorbetter() {
        boolean b = false;
        this.Sort();
        for (int i = 11; i < 15; ++i) {
            if (this.hand[0].getFace() == i && this.hand[1].getFace() == i) {
                b = true;
            }
            else if (this.hand[1].getFace() == i && this.hand[2].getFace() == i) {
                b = true;
            }
            else if (this.hand[2].getFace() == i && this.hand[3].getFace() == i) {
                b = true;
            }
            else if (this.hand[3].getFace() == i && this.hand[4].getFace() == i) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean findNum(final int n) {
        boolean b = false;
        for (int i = 0; i < this.hand.length; ++i) {
            if (this.hand[i].getFace() == n) {
                b = true;
            }
        }
        return b;
    }
    
    public void Sort() {
        for (int i = 1; i < this.hand.length; ++i) {
            for (int j = 0; j < this.hand.length - 1; ++j) {
                if (this.hand[j].getFace() > this.hand[j + 1].getFace()) {
                    final Card card = this.hand[j];
                    this.hand[j] = this.hand[j + 1];
                    this.hand[j + 1] = card;
                }
            }
        }
    }
    
    public void scoreHand() {
        if (this.royalflush()) {
            this.paid_Out = this.val_Cats[0] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n = 1;
            for (int i = 0; i < 37; i += 9) {
                if (n == this.bet_Column) {
                    this.pay_Cats[i].setBackground(Color.orange);
                }
                ++n;
            }
        }
        else if (this.straightflush()) {
            this.paid_Out = this.val_Cats[1] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n2 = 1;
            for (int j = 1; j < 38; j += 9) {
                if (n2 == this.bet_Column) {
                    this.pay_Cats[j].setBackground(Color.orange);
                }
                ++n2;
            }
        }
        else if (this.fourofakind()) {
            this.paid_Out = this.val_Cats[2] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n3 = 1;
            for (int k = 2; k < 39; k += 9) {
                if (n3 == this.bet_Column) {
                    this.pay_Cats[k].setBackground(Color.orange);
                }
                ++n3;
            }
        }
        else if (this.fullhouse()) {
            this.paid_Out = this.val_Cats[3] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n4 = 1;
            for (int l = 3; l < 40; l += 9) {
                if (n4 == this.bet_Column) {
                    this.pay_Cats[l].setBackground(Color.orange);
                }
                ++n4;
            }
        }
        else if (this.flush()) {
            this.paid_Out = this.val_Cats[4] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n5 = 1;
            for (int n6 = 4; n6 < 41; n6 += 9) {
                if (n5 == this.bet_Column) {
                    this.pay_Cats[n6].setBackground(Color.orange);
                }
                ++n5;
            }
        }
        else if (this.straight()) {
            this.paid_Out = this.val_Cats[5] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n7 = 1;
            for (int n8 = 5; n8 < 42; n8 += 9) {
                if (n7 == this.bet_Column) {
                    this.pay_Cats[n8].setBackground(Color.orange);
                }
                ++n7;
            }
        }
        else if (this.threeofakind()) {
            this.paid_Out = this.val_Cats[6] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n9 = 1;
            for (int n10 = 6; n10 < 43; n10 += 9) {
                if (n9 == this.bet_Column) {
                    this.pay_Cats[n10].setBackground(Color.orange);
                }
                ++n9;
            }
        }
        else if (this.twopair()) {
            this.paid_Out = this.val_Cats[7] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n11 = 1;
            for (int n12 = 7; n12 < 44; n12 += 9) {
                if (n11 == this.bet_Column) {
                    this.pay_Cats[n12].setBackground(Color.orange);
                }
                ++n11;
            }
        }
        else if (this.jacksorbetter()) {
            this.paid_Out = this.val_Cats[8] * this.bet_Column * this.bet_Value;
            this.lbl_Paid.setText(String.valueOf(this.paid_Out));
            this.balance += this.paid_Out;
            this.lbl_Balance.setText(String.valueOf(this.balance));
            int n13 = 1;
            for (int n14 = 8; n14 < 45; n14 += 9) {
                if (n13 == this.bet_Column) {
                    this.pay_Cats[n14].setBackground(Color.orange);
                }
                ++n13;
            }
        }
        else {
            this.balance -= this.bet_Column * this.bet_Value;
            this.lbl_Balance.setText(String.valueOf(this.balance));
        }
        this.btn_Deal.setLabel("DEAL");
        this.btn_Betone.setEnabled(true);
        this.btn_Betmax.setEnabled(true);
        this.btn_Pass.setEnabled(false);
        this.draw_Num = 1;
        for (int n15 = 0; n15 < this.btn_Bet.length; ++n15) {
            this.btn_Bet[n15].setEnabled(true);
        }
        for (int n16 = 0; n16 < this.card_Deck.length; ++n16) {
            this.card_Deck[n16].setDealt(false);
        }
        for (int n17 = 0; n17 < this.img_Card.length; ++n17) {
            this.img_Card[n17].setHold(false);
        }
        if (this.paid_Out > 0) {
            this.win.play();
        }
        this.paid_Out = 0;
    }
    
    public void Cut_Images(final Image image) {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 52; ++i) {
            this.Card_img[i] = this.createImage(71, 96);
            this.Card_img[i].getGraphics().drawImage(image, n, n2, this);
            if (i == 12 || i == 25 || i == 38) {
                n = 0;
                n2 -= 99;
            }
            else {
                n -= 73;
            }
        }
    }
    
    public void load_Sounds() {
        this.click = this.getAudioClip(this.getDocumentBase(), "audio/click.au");
        this.win = this.getAudioClip(this.getDocumentBase(), "audio/win.au");
        (this.sh = this.getAudioClip(this.getDocumentBase(), "audio/silence.au")).loop();
        this.click.play();
        this.click.stop();
        this.win.play();
        this.win.stop();
    }
    
    public void load_Images() {
        this.tracker = new MediaTracker(this);
        final Image image = this.getImage(this.getDocumentBase(), "images/cards.gif");
        this.tracker.addImage(image, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.Cut_Images(image);
        this.Card_img[52] = this.getImage(this.getDocumentBase(), "images/back.gif");
        this.tracker.addImage(image, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
    }
    
    public void draw_Cards() {
        this.set_column();
        this.btn_Deal.setLabel("DEAL 2");
        this.btn_Betone.setEnabled(false);
        this.btn_Betmax.setEnabled(false);
        this.lbl_Paid.setText("");
        for (int i = 0; i < this.btn_Bet.length; ++i) {
            this.btn_Bet[i].setEnabled(false);
        }
        for (int j = 0; j < this.hand.length; ++j) {
            if (!this.img_Card[j].getHold()) {
                int abs;
                do {
                    abs = Math.abs(this.rand.nextInt() % 52);
                } while (this.card_Deck[abs].getDealt());
                this.card_Deck[abs].setDealt(true);
                this.hand[j].setFace(this.card_Deck[abs].getFace());
                this.hand[j].setSuit(this.card_Deck[abs].getSuit());
                this.img_Card[j].setCard(abs);
            }
        }
        ++this.draw_Num;
        if (this.draw_Num > 1) {
            this.btn_Pass.setEnabled(true);
        }
        if (this.draw_Num > 2) {
            this.scoreHand();
        }
        this.btn_Deal.setEnabled(true);
        this.showStatus("Video Poker ver 1.1, Copyright© 2002 pschmandra@hotmail.com");
    }
    
    public void set_column() {
        for (int i = 0; i < this.pay_Cats.length; ++i) {
            if (i % 2 == 1) {
                if (this.pay_Cats[i].getColumn() == this.bet_Column) {
                    this.pay_Cats[i].setBackground(new Color(0, 255, 255));
                }
                else {
                    this.pay_Cats[i].setBackground(Color.white);
                }
            }
            else if (this.pay_Cats[i].getColumn() == this.bet_Column) {
                this.pay_Cats[i].setBackground(new Color(0, 200, 255));
            }
            else {
                this.pay_Cats[i].setBackground(Color.lightGray);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void draw() {
        final Font font = new Font("Helvetica", 1, 14);
        this.gBuffer.setColor(new Color(0, 40, 0));
        this.gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.gBuffer.setFont(font);
        this.gBuffer.setColor(Color.orange);
        this.gBuffer.drawRect(0, 0, 480, 208);
        this.gBuffer.drawRect(0, 215, 480, 40);
        this.gBuffer.drawRect(0, 262, 480, 120);
        this.gBuffer.drawRect(0, 389, 480, 48);
        this.gBuffer.drawString("Royal Flush", 70, 25);
        this.gBuffer.drawString("Straight Flush", 53, 46);
        this.gBuffer.drawString("4 of a kind", 78, 67);
        this.gBuffer.drawString("Full House", 76, 88);
        this.gBuffer.drawString("Flush", 112, 109);
        this.gBuffer.drawString("Straight", 95, 127);
        this.gBuffer.drawString("3 of a kind", 78, 151);
        this.gBuffer.drawString("2 pair", 110, 172);
        this.gBuffer.drawString("Jacks or better", 44, 193);
        this.gBuffer.drawString("Balance $", 44, 240);
        this.gBuffer.drawString("Paid $", 230, 240);
        this.gBuffer.drawString("Bet $", 368, 240);
        this.gBuffer.setColor(Color.black);
        this.gBuffer.fillRect(0, 210, 485, 4);
        this.gBuffer.fillRect(0, 257, 485, 4);
        this.gBuffer.fillRect(0, 384, 485, 4);
    }
    
    public void paint(final Graphics graphics) {
        this.draw();
        graphics.drawImage(this.Buffer, 0, 0, this);
    }
    
    public String getAppletInfo() {
        return "\nVideo Poker ver 1.0 18-09-2002\nPeter Schmandra\nE-Mail:pschmandra@hotmail.com\n";
    }
}
