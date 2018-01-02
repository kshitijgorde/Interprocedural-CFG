import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.MouseListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class VideoPoker extends Frame implements MouseListener
{
    public static final int MAXBET = 5;
    private static final int PAUSEBETWEENCARDS = 200;
    private static final int TIMEBETWEENBETS = 50;
    private CardProducer deck;
    private PayChart payChart;
    private int credit;
    private int coinIn;
    private boolean gameOver;
    private boolean canSelect;
    private VideoPokerGame game;
    private int amount;
    private String[] amountString;
    private int[] amountMultipliers;
    private static Bank bank;
    private CardPanel[] cards;
    private boolean cardsAdded;
    boolean fComponentsAdjusted;
    Button bet1Button;
    Button dealButton;
    Button betMaxButton;
    Label infoLabel;
    Button aboutButton;
    Label coinInLabel;
    Label winLabel;
    Label creditLabel;
    KLabel KLabel1;
    KLabel KLabel2;
    KLabel KLabel3;
    KLabel KLabel4;
    KLabel KLabel5;
    KLabel creditAmountLabel;
    KLabel coinInAmountLabel;
    KLabel winAmountLabel;
    KLabel[] holdLabels;
    
    public VideoPoker() {
        this.amountString = new String[] { "", "$.05", "$.25", "$1", "$5" };
        this.amountMultipliers = new int[] { 0, 5, 25, 100, 500 };
        this.cards = new CardPanel[5];
        this.cardsAdded = false;
        this.fComponentsAdjusted = false;
        this.bet1Button = new Button();
        this.dealButton = new Button();
        this.betMaxButton = new Button();
        this.infoLabel = new Label();
        this.aboutButton = new Button();
        this.coinInLabel = new Label();
        this.winLabel = new Label();
        this.creditLabel = new Label();
        this.KLabel1 = new KLabel();
        this.KLabel2 = new KLabel();
        this.KLabel3 = new KLabel();
        this.KLabel4 = new KLabel();
        this.KLabel5 = new KLabel();
        this.creditAmountLabel = new KLabel();
        this.coinInAmountLabel = new KLabel();
        this.winAmountLabel = new KLabel();
        this.holdLabels = new KLabel[] { this.KLabel1, this.KLabel2, this.KLabel3, this.KLabel4, this.KLabel5 };
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(433, 423);
        this.setVisible(false);
        this.bet1Button.setLabel("Bet 1");
        this.add(this.bet1Button);
        this.bet1Button.setBackground(Color.lightGray);
        this.bet1Button.setBounds(360, 372, 60, 40);
        this.dealButton.setLabel("Deal");
        this.dealButton.setEnabled(false);
        this.add(this.dealButton);
        this.dealButton.setBackground(Color.lightGray);
        this.dealButton.setBounds(288, 324, 60, 36);
        this.betMaxButton.setLabel("Bet Max");
        this.add(this.betMaxButton);
        this.betMaxButton.setBackground(Color.lightGray);
        this.betMaxButton.setBounds(360, 324, 60, 40);
        this.infoLabel.setAlignment(1);
        this.add(this.infoLabel);
        this.infoLabel.setFont(new Font("Dialog", 1, 12));
        this.infoLabel.setBounds(155, 312, 120, 24);
        this.aboutButton.setLabel("About");
        this.add(this.aboutButton);
        this.aboutButton.setBackground(Color.lightGray);
        this.aboutButton.setBounds(12, 372, 60, 40);
        this.coinInLabel.setText("Coin In");
        this.coinInLabel.setAlignment(2);
        this.add(this.coinInLabel);
        this.coinInLabel.setFont(new Font("MonoSpaced", 1, 20));
        this.coinInLabel.setBounds(84, 360, 108, 24);
        this.winLabel.setText("Win");
        this.winLabel.setAlignment(2);
        this.add(this.winLabel);
        this.winLabel.setFont(new Font("MonoSpaced", 1, 20));
        this.winLabel.setBounds(84, 384, 108, 24);
        this.creditLabel.setText("Credit");
        this.creditLabel.setAlignment(2);
        this.add(this.creditLabel);
        this.creditLabel.setFont(new Font("MonoSpaced", 1, 20));
        this.creditLabel.setBounds(84, 336, 108, 24);
        this.add(this.KLabel1);
        this.KLabel1.setFont(new Font("Dialog", 1, 20));
        this.KLabel1.setBounds(12, 192, 72, 24);
        this.add(this.KLabel2);
        this.KLabel2.setFont(new Font("Dialog", 1, 20));
        this.KLabel2.setBounds(96, 192, 72, 24);
        this.add(this.KLabel3);
        this.KLabel3.setFont(new Font("Dialog", 1, 20));
        this.KLabel3.setBounds(180, 192, 72, 24);
        this.add(this.KLabel4);
        this.KLabel4.setFont(new Font("Dialog", 1, 20));
        this.KLabel4.setBounds(264, 192, 72, 24);
        this.add(this.KLabel5);
        this.KLabel5.setFont(new Font("Dialog", 1, 20));
        this.KLabel5.setBounds(348, 192, 72, 24);
        this.add(this.creditAmountLabel);
        this.creditAmountLabel.setFont(new Font("Dialog", 1, 20));
        this.creditAmountLabel.setBounds(204, 335, 84, 22);
        this.add(this.coinInAmountLabel);
        this.coinInAmountLabel.setFont(new Font("Dialog", 1, 20));
        this.coinInAmountLabel.setBounds(204, 359, 84, 22);
        this.add(this.winAmountLabel);
        this.winAmountLabel.setFont(new Font("Dialog", 1, 20));
        this.winAmountLabel.setBounds(204, 383, 84, 22);
        this.setTitle("Video Poker");
        this.setResizable(false);
        for (int i = 0; i < 5; ++i) {
            this.holdLabels[i].setAlignment(1);
            (this.cards[i] = new CardPanel(15 + i * 84, 238, new Card(), 2)).addMouseListener(this);
        }
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        this.dealButton.addActionListener(symAction);
        this.bet1Button.addActionListener(symAction);
        this.betMaxButton.addActionListener(symAction);
        this.aboutButton.addActionListener(symAction);
    }
    
    public VideoPoker(final String title) {
        this();
        this.setTitle(title);
    }
    
    public static void setBank(final Bank bank) {
        VideoPoker.bank = bank;
    }
    
    public void setGame(final VideoPokerGame game, final int amount) {
        this.setVisible(true);
        if (game == this.game && amount == this.amount) {
            return;
        }
        this.game = game;
        this.amount = amount;
        this.reset();
    }
    
    private void reset() {
        this.credit = 0;
        this.coinIn = 0;
        this.creditAmountLabel.setText("0");
        this.winAmountLabel.setText("0");
        this.coinInAmountLabel.setText("0");
        this.setTitle(String.valueOf(this.game.getName()) + " " + this.amountString[this.amount]);
        this.deck = new JokerDeck(1, this.game.getNumJokers());
        this.gameOver = true;
        for (int i = 0; i < 5; ++i) {
            this.holdLabels[i].setText("");
            this.cards[i].showBack();
        }
        if (this.payChart != null) {
            this.remove(this.payChart);
        }
        (this.payChart = new PayChart(this.game)).setBounds(5, 5, 420, 190);
        this.add(this.payChart);
        this.payChart.deHighlightPayline();
        this.dealButton.setEnabled(false);
        this.bet1Button.setEnabled(true);
        this.betMaxButton.setEnabled(true);
        this.canSelect = false;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible && !this.cardsAdded) {
            this.setLocation(50, 50);
            this.add(this.cards[0]);
            this.add(this.cards[1]);
            this.add(this.cards[2]);
            this.add(this.cards[3]);
            this.add(this.cards[4]);
            this.cardsAdded = true;
        }
    }
    
    public static void main(final String[] array) {
        new VideoPoker().setVisible(true);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && this.canSelect) {
            for (int i = 0; i < 5; ++i) {
                if (source == this.cards[i]) {
                    if (this.holdLabels[i].getText().equals("")) {
                        this.holdLabels[i].setText("Held");
                    }
                    else {
                        this.holdLabels[i].setText("");
                    }
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    void VideoPoker_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void dealButton_ActionPerformed(final ActionEvent actionEvent) {
        this.dealButton.setEnabled(false);
        this.bet1Button.setEnabled(false);
        this.betMaxButton.setEnabled(false);
        this.deal();
    }
    
    void bet1Button_ActionPerformed(final ActionEvent actionEvent) {
        this.dealButton.setEnabled(false);
        this.bet1Button.setEnabled(false);
        this.betMaxButton.setEnabled(false);
        this.bet1(true);
    }
    
    void betMaxButton_ActionPerformed(final ActionEvent actionEvent) {
        this.dealButton.setEnabled(false);
        this.bet1Button.setEnabled(false);
        this.betMaxButton.setEnabled(false);
        this.betMax();
    }
    
    private synchronized void betMax() {
        int coinIn;
        if (this.gameOver) {
            coinIn = 0;
        }
        else {
            coinIn = this.coinIn;
        }
        for (int i = coinIn; i < 5; ++i) {
            this.dealButton.setEnabled(false);
            this.bet1Button.setEnabled(false);
            this.betMaxButton.setEnabled(false);
            this.bet1(false);
            try {
                this.wait(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void bet1(final boolean b) {
        if (this.gameOver) {
            this.infoLabel.setText("");
            this.dealButton.setEnabled(true);
            this.gameOver = false;
            this.coinIn = 0;
            this.payChart.deHighlightPayline();
            this.winAmountLabel.setText("0");
        }
        this.payChart.deHighlightPayColumn();
        --this.credit;
        if (VideoPoker.bank != null) {
            VideoPoker.bank.addToBank(-this.amountMultipliers[this.amount]);
        }
        ++this.coinIn;
        this.creditAmountLabel.setText(Integer.toString(this.credit));
        this.coinInAmountLabel.setText(Integer.toString(this.coinIn));
        this.payChart.highlightPayColumn(this.coinIn);
        if (this.coinIn == 5) {
            this.deal();
            return;
        }
        if (b) {
            this.dealButton.setEnabled(true);
            this.betMaxButton.setEnabled(true);
            this.bet1Button.setEnabled(true);
        }
    }
    
    private synchronized void deal() {
        if (this.canSelect) {
            this.payChart.deHighlightPayline();
            for (int i = 0; i < 5; ++i) {
                if (this.holdLabels[i].getText().equals("")) {
                    this.cards[i].showBack();
                }
            }
            for (int j = 0; j < 5; ++j) {
                if (this.holdLabels[j].getText().equals("")) {
                    try {
                        this.wait(200L);
                    }
                    catch (InterruptedException ex) {}
                    this.cards[j].setCard(this.deck.getNextCard());
                    if (this.game.isWild(this.cards[j].getCard())) {
                        this.cards[j].markWild();
                    }
                    else {
                        this.cards[j].unMarkWild();
                    }
                    Sounds.playSound(1);
                    this.cards[j].showFront();
                }
            }
            this.canSelect = false;
            final int payline = this.game.getPayline(this.getHand());
            this.payChart.highlightPayline(payline);
            final int payoff = this.game.getPayoff(payline, this.coinIn);
            this.winAmountLabel.setText(String.valueOf(payoff));
            this.credit += payoff;
            if (VideoPoker.bank != null) {
                VideoPoker.bank.addToBank(payoff * this.amountMultipliers[this.amount]);
            }
            this.creditAmountLabel.setText(String.valueOf(this.credit));
            this.dealButton.setEnabled(false);
            this.bet1Button.setEnabled(true);
            this.betMaxButton.setEnabled(true);
            this.infoLabel.setText("Game Over");
            this.gameOver = true;
        }
        else {
            this.deck.shuffle();
            for (int k = 0; k < 5; ++k) {
                this.cards[k].showBack();
                this.holdLabels[k].setText("");
            }
            for (int l = 0; l < 5; ++l) {
                try {
                    this.wait(200L);
                }
                catch (InterruptedException ex2) {}
                this.cards[l].setCard(this.deck.getNextCard());
                if (this.game.isWild(this.cards[l].getCard())) {
                    this.cards[l].markWild();
                }
                else {
                    this.cards[l].unMarkWild();
                }
                this.cards[l].showFront();
            }
            this.payChart.highlightPayline(this.game.getPayline(this.getHand()));
            this.dealButton.setEnabled(true);
            this.betMaxButton.setEnabled(false);
            this.bet1Button.setEnabled(false);
            this.infoLabel.setText("Click Card to Hold");
            this.canSelect = true;
        }
        System.gc();
    }
    
    private Card[] getHand() {
        final Card[] array = new Card[5];
        for (int i = 0; i < 5; ++i) {
            array[i] = this.cards[i].getCard();
        }
        return array;
    }
    
    void aboutButton_ActionPerformed(final ActionEvent actionEvent) {
        new AboutFrame("About").setVisible(true);
    }
    
    public void showStatus(final String text) {
        this.infoLabel.setText(text);
        this.infoLabel.update(this.infoLabel.getGraphics());
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == VideoPoker.this) {
                VideoPoker.this.VideoPoker_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == VideoPoker.this.dealButton) {
                VideoPoker.this.dealButton_ActionPerformed(actionEvent);
                return;
            }
            if (source == VideoPoker.this.bet1Button) {
                VideoPoker.this.bet1Button_ActionPerformed(actionEvent);
                return;
            }
            if (source == VideoPoker.this.betMaxButton) {
                VideoPoker.this.betMaxButton_ActionPerformed(actionEvent);
                return;
            }
            if (source == VideoPoker.this.aboutButton) {
                VideoPoker.this.aboutButton_ActionPerformed(actionEvent);
            }
        }
    }
}
