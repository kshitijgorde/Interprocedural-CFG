import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Blackjack extends Frame implements ActionListener, ItemListener, Runnable, Bank
{
    final int START = 0;
    final int WAITINGBET = 1;
    final int BETTING = 2;
    final int PLAYERS_FIRST_CARD = 3;
    final int DEALERS_FIRST_CARD = 4;
    final int PLAYERS_SECOND_CARD = 5;
    final int DEALERS_SECOND_CARD = 6;
    final int ASK_FOR_INSURANCE = 7;
    final int CHECK_DEALERS_BJ = 8;
    final int CLEAR_INSURANCE_BET = 85;
    final int CHECK_PLAYERS_BJ = 9;
    final int PLAY_PLAYERS_HANDS = 10;
    final int SHOW_DEALERS_SECOND_CARD = 11;
    final int PLAY_DEALERS_HAND = 12;
    final int DETERMINE_RESULTS = 13;
    final int RESULT_PAUSE = 14;
    final int CLEAR_HANDS = 15;
    final int SHUFFLE = 16;
    final int FRAMEWIDTH = 700;
    final int FRAMEHEIGHT = 480;
    final int betDelay = 2;
    final int clearDelay = 3;
    final int slowSpeed = 300;
    final int shuffleDelay = 3;
    final int numStartChips = 1000;
    private int handControl;
    private BJOptions bjOptions;
    private int secondCard;
    private boolean optionsEnabled;
    private Bank bank;
    private BaseStack baseStack;
    private int delay;
    private Dealer dealerHand;
    private int dealerValue;
    private ShoeDeck deck;
    private Hands hands;
    private int pause;
    private boolean continueGame;
    private Thread t;
    private boolean alreadyStarted;
    boolean fComponentsAdjusted;
    Button HelpButton;
    Button OptionsButton;
    Checkbox play2HandsCheck;
    Checkbox showTotalCheck;
    Label ShufflingLabel;
    LinePanel placard;
    Label minLabel;
    Label maxLabel;
    Label doubleLabel;
    Label DASLabel;
    Label resplitLabel;
    Label surrenderLabel;
    Label chipsBoughtLabel;
    Label chipsOnhandLabel;
    Label netLabel;
    Label chipsBoughtAmountLabel;
    Label chipsOnhandAmountLabel;
    Checkbox basicStrategyCheckbox;
    Label netAmountLabel;
    
    public Blackjack() {
        this.bjOptions = new BJOptions();
        this.continueGame = true;
        this.t = new Thread(this);
        this.alreadyStarted = false;
        this.fComponentsAdjusted = false;
        this.HelpButton = new Button();
        this.OptionsButton = new Button();
        this.play2HandsCheck = new Checkbox();
        this.showTotalCheck = new Checkbox();
        this.ShufflingLabel = new Label();
        this.placard = new LinePanel();
        this.minLabel = new Label();
        this.maxLabel = new Label();
        this.doubleLabel = new Label();
        this.DASLabel = new Label();
        this.resplitLabel = new Label();
        this.surrenderLabel = new Label();
        this.chipsBoughtLabel = new Label();
        this.chipsOnhandLabel = new Label();
        this.netLabel = new Label();
        this.chipsBoughtAmountLabel = new Label();
        this.chipsOnhandAmountLabel = new Label();
        this.basicStrategyCheckbox = new Checkbox();
        this.netAmountLabel = new Label();
        this.setVisible(false);
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(700, 480);
        this.setVisible(false);
        this.setResizable(false);
        this.HelpButton.setLabel("Help");
        this.add(this.HelpButton);
        this.HelpButton.setFont(new Font("Dialog", 1, 12));
        this.HelpButton.setBounds(25, 400, 80, 25);
        this.OptionsButton.setLabel("Options");
        this.add(this.OptionsButton);
        this.OptionsButton.setFont(new Font("Dialog", 0, 12));
        this.OptionsButton.setBounds(25, 430, 80, 25);
        this.play2HandsCheck.setLabel("Play 2 Hands");
        this.play2HandsCheck.setLocation(115, 400);
        this.add(this.play2HandsCheck);
        this.play2HandsCheck.setBounds(115, 400, 100, 12);
        this.showTotalCheck.setLabel("Show Totals");
        this.showTotalCheck.setLocation(115, 430);
        this.add(this.showTotalCheck);
        this.showTotalCheck.setBounds(115, 420, 100, 12);
        this.ShufflingLabel.setText("Shuffling");
        this.add(this.ShufflingLabel);
        this.ShufflingLabel.setForeground(Color.black);
        this.ShufflingLabel.setFont(new Font("Dialog", 1, 20));
        this.ShufflingLabel.setBounds(288, 36, 132, 40);
        this.placard.setLayout(null);
        this.placard.setBorderColor(Color.white);
        this.add(this.placard);
        this.placard.setBackground(Color.lightGray);
        this.placard.setBounds(0, 0, 168, 96);
        this.placard.add(this.minLabel);
        this.minLabel.setForeground(Color.white);
        this.minLabel.setFont(new Font("Dialog", 1, 14));
        this.minLabel.setBounds(6, 4, 155, 16);
        this.placard.add(this.maxLabel);
        this.maxLabel.setForeground(Color.white);
        this.maxLabel.setFont(new Font("Dialog", 1, 14));
        this.maxLabel.setBounds(6, 21, 155, 16);
        this.placard.add(this.doubleLabel);
        this.doubleLabel.setForeground(Color.white);
        this.doubleLabel.setFont(new Font("Dialog", 0, 12));
        this.doubleLabel.setBounds(6, 40, 155, 12);
        this.placard.add(this.DASLabel);
        this.DASLabel.setForeground(Color.white);
        this.DASLabel.setFont(new Font("Dialog", 0, 12));
        this.DASLabel.setBounds(6, 53, 155, 12);
        this.placard.add(this.resplitLabel);
        this.resplitLabel.setForeground(Color.white);
        this.resplitLabel.setFont(new Font("Dialog", 0, 12));
        this.resplitLabel.setBounds(6, 66, 155, 12);
        this.placard.add(this.surrenderLabel);
        this.surrenderLabel.setForeground(Color.white);
        this.surrenderLabel.setFont(new Font("Dialog", 0, 12));
        this.surrenderLabel.setBounds(6, 79, 155, 12);
        this.chipsBoughtLabel.setForeground(Color.black);
        this.chipsBoughtLabel.setFont(new Font("Dialog", 0, 12));
        this.chipsBoughtLabel.setBounds(25, 336, 50, 18);
        this.chipsBoughtLabel.setText("Bought");
        this.add(this.chipsBoughtLabel);
        this.chipsOnhandLabel.setForeground(Color.black);
        this.chipsOnhandLabel.setFont(new Font("Dialog", 0, 12));
        this.chipsOnhandLabel.setBounds(25, 356, 50, 18);
        this.chipsOnhandLabel.setText("On Hand");
        this.add(this.chipsOnhandLabel);
        this.netLabel.setForeground(Color.black);
        this.netLabel.setFont(new Font("Dialog", 0, 12));
        this.netLabel.setBounds(25, 376, 50, 18);
        this.netLabel.setText("Winning");
        this.add(this.netLabel);
        this.chipsBoughtAmountLabel.setForeground(Color.black);
        this.chipsBoughtAmountLabel.setFont(new Font("Dialog", 0, 12));
        this.chipsBoughtAmountLabel.setBounds(75, 336, 70, 18);
        this.chipsBoughtAmountLabel.setText("$1000.00");
        this.chipsBoughtAmountLabel.setAlignment(2);
        this.add(this.chipsBoughtAmountLabel);
        this.chipsOnhandAmountLabel.setForeground(Color.black);
        this.chipsOnhandAmountLabel.setFont(new Font("Dialog", 0, 12));
        this.chipsOnhandAmountLabel.setBounds(75, 356, 70, 18);
        this.chipsOnhandAmountLabel.setText("$1000.00");
        this.chipsOnhandAmountLabel.setAlignment(2);
        this.add(this.chipsOnhandAmountLabel);
        this.netAmountLabel.setForeground(Color.black);
        this.netAmountLabel.setFont(new Font("Dialog", 0, 12));
        this.netAmountLabel.setBounds(75, 376, 70, 18);
        this.basicStrategyCheckbox.setLabel("Strategy");
        this.add(this.basicStrategyCheckbox);
        this.basicStrategyCheckbox.setBounds(115, 440, 100, 16);
        this.netAmountLabel.setText("$0.00");
        this.netAmountLabel.setAlignment(2);
        this.add(this.netAmountLabel);
        this.OptionsButton.addActionListener(this);
        this.HelpButton.addActionListener(this);
        this.showTotalCheck.addItemListener(this);
        this.addWindowListener(new SymWindow());
    }
    
    public Blackjack(final String title) {
        this();
        this.setTitle(title);
    }
    
    public void startGame(final Bank bank) {
        this.setVisible(true);
        if (this.alreadyStarted) {
            return;
        }
        this.alreadyStarted = true;
        this.handControl = 0;
        (this.deck = new ShoeDeck()).reset(this.bjOptions.numberDecks, this.bjOptions.penetration);
        this.add(this.deck);
        this.deck.setLocation(700 - this.deck.getSize().width + this.getInsets().left, this.getInsets().top);
        (this.baseStack = new BaseStack()).add(this);
        this.bank = bank;
        this.baseStack.setBank(this);
        this.buyChips();
        this.hands = new Hands(this, 215, 330, this.baseStack, this.deck, this.bjOptions);
        this.dealerHand = new Dealer(this, 25, 250, this.bjOptions, this.deck);
        this.optionsEnabled = true;
        this.OptionsButton.setLabel("Options");
        this.HelpButton.setEnabled(true);
        this.play2HandsCheck.setState(true);
        this.hands.setNumStartHands(2);
        this.setPlacard();
        this.showTotalCheck.setState(false);
        this.hands.setShowTotal(this.showTotalCheck.getState());
        this.basicStrategyCheckbox.setState(false);
        this.ShufflingLabel.setVisible(false);
        this.pause = 300;
        this.t.start();
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.setLocation(50, 50);
        }
        super.setVisible(visible);
    }
    
    public static void main(final String[] array) {
        new Blackjack().setVisible(true);
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
    
    public void run() {
        while (this.continueGame) {
            switch (this.handControl) {
                case 0: {
                    this.optionsEnabled = true;
                    this.OptionsButton.setLabel("Options");
                    this.handControl = 1;
                    break;
                }
                case 1: {
                    if (this.play2HandsCheck.getState()) {
                        this.hands.setNumStartHands(2);
                    }
                    else {
                        this.hands.setNumStartHands(1);
                    }
                    if (this.hands.betsMade()) {
                        this.optionsEnabled = false;
                        this.OptionsButton.setLabel("Stragegy");
                        this.delay = 2;
                        this.handControl = 2;
                        this.pause = 300;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (!this.hands.betsMade()) {
                        this.handControl = 0;
                        this.pause = 1;
                    }
                    --this.delay;
                    if (this.hands.isNewBetMade()) {
                        this.delay = 2;
                    }
                    if (this.delay == 0) {
                        this.handControl = 3;
                        break;
                    }
                    break;
                }
                case 3: {
                    if (!this.hands.betsMade()) {
                        this.handControl = 0;
                        this.pause = 1;
                        break;
                    }
                    if (this.hands.isNewBetMade()) {
                        this.delay = 2;
                        this.handControl = 2;
                        break;
                    }
                    if (this.hands.dealCard()) {
                        this.handControl = 4;
                        break;
                    }
                    break;
                }
                case 4: {
                    this.dealerHand.firstCard();
                    this.handControl = 5;
                    break;
                }
                case 5: {
                    if (this.hands.dealCard()) {
                        this.handControl = 6;
                        break;
                    }
                    break;
                }
                case 6: {
                    this.pause = 1;
                    this.secondCard = this.dealerHand.secondCard();
                    if (this.secondCard == 11) {
                        this.hands.askForInsurance();
                        this.handControl = 7;
                        break;
                    }
                    this.handControl = 8;
                    break;
                }
                case 7: {
                    if (this.hands.isInsuranceDone()) {
                        this.handControl = 8;
                        break;
                    }
                    break;
                }
                case 8: {
                    if (this.dealerHand.getValue() == 21) {
                        this.hands.dealerHasBJ(true);
                        this.dealerValue = this.dealerHand.showFirstCard();
                        this.hands.done();
                        this.pause = 300;
                        this.handControl = 13;
                        break;
                    }
                    if (this.hands.insuranceBetMade()) {
                        this.pause = 900;
                        this.handControl = 85;
                        break;
                    }
                    this.handControl = 9;
                    break;
                }
                case 85: {
                    this.hands.dealerHasBJ(false);
                    this.pause = 1;
                    this.handControl = 9;
                    break;
                }
                case 9: {
                    this.hands.checkBJs();
                    this.handControl = 10;
                    break;
                }
                case 10: {
                    this.bjOptions.setBasicStrategy(this.basicStrategyCheckbox.getState(), this.secondCard);
                    if (this.hands.handsComplete()) {
                        this.handControl = 11;
                        this.pause = 300;
                        break;
                    }
                    break;
                }
                case 11: {
                    this.dealerValue = this.dealerHand.showFirstCard();
                    if (this.hands.allHandsDetermined()) {
                        this.handControl = 13;
                        break;
                    }
                    this.handControl = 12;
                    if (this.dealerValue >= 17) {
                        this.handControl = 13;
                        break;
                    }
                    break;
                }
                case 12: {
                    this.dealerValue = this.dealerHand.nextCard();
                    if (this.dealerValue == 0 || this.dealerValue >= 17) {
                        this.handControl = 13;
                        break;
                    }
                    break;
                }
                case 13: {
                    this.delay = 3;
                    this.handControl = 14;
                    this.hands.determineResults(this.dealerValue);
                    break;
                }
                case 14: {
                    --this.delay;
                    if (this.delay == 0) {
                        this.handControl = 15;
                        break;
                    }
                    break;
                }
                case 15: {
                    this.dealerHand.clear();
                    this.hands.clear();
                    this.update(this.getGraphics());
                    if (this.deck.getNeedsShuffled()) {
                        this.deck.shuffle();
                        this.deck.setVisible(false);
                        this.ShufflingLabel.setVisible(true);
                        this.handControl = 16;
                        this.delay = 3;
                    }
                    else {
                        this.handControl = 0;
                        this.pause = 1;
                    }
                    this.optionsEnabled = true;
                    this.OptionsButton.setLabel("Options");
                    break;
                }
                case 16: {
                    --this.delay;
                    if (this.delay == 0) {
                        this.ShufflingLabel.setVisible(false);
                        this.deck.setVisible(true);
                        this.handControl = 0;
                        this.pause = 1;
                        break;
                    }
                    break;
                }
            }
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private String convertToDollarString(int n) {
        if (n < 0) {
            n = -n;
        }
        final String s = new String("$" + String.valueOf(n / 2));
        String s2;
        if (n % 2 == 0) {
            s2 = String.valueOf(s) + ".00";
        }
        else {
            s2 = String.valueOf(s) + ".50";
        }
        return s2;
    }
    
    private void setPlacard() {
        if (this.bjOptions.limitIndex == 0) {
            final Color background = new Color(128, 128, 128);
            this.placard.setBackground(background);
            this.minLabel.setBackground(background);
            this.maxLabel.setBackground(background);
            this.doubleLabel.setBackground(background);
            this.DASLabel.setBackground(background);
            this.resplitLabel.setBackground(background);
            this.surrenderLabel.setBackground(background);
        }
        if (this.bjOptions.limitIndex == 1) {
            final Color background2 = new Color(128, 0, 0);
            this.placard.setBackground(background2);
            this.minLabel.setBackground(background2);
            this.maxLabel.setBackground(background2);
            this.doubleLabel.setBackground(background2);
            this.DASLabel.setBackground(background2);
            this.resplitLabel.setBackground(background2);
            this.surrenderLabel.setBackground(background2);
            this.placard.repaint();
        }
        if (this.bjOptions.limitIndex == 2) {
            final Color background3 = new Color(0, 128, 0);
            this.placard.setBackground(background3);
            this.minLabel.setBackground(background3);
            this.maxLabel.setBackground(background3);
            this.doubleLabel.setBackground(background3);
            this.DASLabel.setBackground(background3);
            this.resplitLabel.setBackground(background3);
            this.surrenderLabel.setBackground(background3);
        }
        if (this.bjOptions.limitIndex == 3) {
            final Color black = Color.black;
            this.placard.setBackground(black);
            this.minLabel.setBackground(black);
            this.maxLabel.setBackground(black);
            this.doubleLabel.setBackground(black);
            this.DASLabel.setBackground(black);
            this.resplitLabel.setBackground(black);
            this.surrenderLabel.setBackground(black);
        }
        int minBet;
        if (this.play2HandsCheck.getState()) {
            minBet = this.bjOptions.getMinBet() / 2;
        }
        else {
            minBet = this.bjOptions.getMinBet();
        }
        this.minLabel.setText("Min:  " + String.valueOf(minBet));
        this.maxLabel.setText("Max:  " + String.valueOf(this.bjOptions.getMaxBet()));
        if (this.bjOptions.doubleOn_10_or_11_only) {
            this.doubleLabel.setText("Double On 10 or 11 Only");
        }
        else {
            this.doubleLabel.setText("Double on Any Two Cards");
        }
        if (this.bjOptions.doubleAfterSplitAllowed) {
            this.DASLabel.setText("Double After Split");
        }
        else {
            this.DASLabel.setText("No Double After Split");
        }
        if (this.bjOptions.resplitPairsAllowed) {
            if (this.bjOptions.resplitAcesAllowed) {
                this.resplitLabel.setText("Resplit Aces and Pairs");
            }
            else {
                this.resplitLabel.setText("Resplit Pairs, Except Aces");
            }
        }
        else if (this.bjOptions.resplitAcesAllowed) {
            this.resplitLabel.setText("Resplit Aces Only");
        }
        else {
            this.resplitLabel.setText("No Respliting");
        }
        if (this.bjOptions.surrenderAllowed) {
            this.surrenderLabel.setText("Late Surrender Allowed");
            return;
        }
        this.surrenderLabel.setText("No Surrender");
    }
    
    private void buyChips() {
        for (int i = 1; i <= 9; ++i) {
            this.baseStack.buyChips(100);
        }
        this.baseStack.removeChips(5);
        this.baseStack.addChips(5);
        this.baseStack.buyChips(100);
        this.bank.addToBank(-100000);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        if (this.isVisible()) {
            final String s = new String();
            String s2;
            if (this.bjOptions.hitSoft17) {
                s2 = "Dealer Must Hit Soft 17";
            }
            else {
                s2 = "Dealer Must Stand On All 17's";
            }
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 1, 24));
            graphics.drawString(s2, (700 - graphics.getFontMetrics().stringWidth(s2)) / 2, 140);
            graphics.fillRect(this.getInsets().left, 145, 700, 4);
            graphics.fillRect(this.getInsets().left, 170, 700, 4);
            final String s3 = "Insurance Pays 2 to 1";
            graphics.drawString(s3, (700 - graphics.getFontMetrics().stringWidth(s3)) / 2, 168);
            if (this.hands != null) {
                this.hands.paintInsuranceBet(graphics);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.OptionsButton) {
            this.OptionsButton_actionPerformed(actionEvent);
            return;
        }
        if (source == this.HelpButton) {
            this.HelpButton_actionPerformed(actionEvent);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.dealerHand.setShowTotal(this.showTotalCheck.getState());
        this.hands.setShowTotal(this.showTotalCheck.getState());
    }
    
    void OptionsButton_actionPerformed(final ActionEvent actionEvent) {
        if (this.optionsEnabled) {
            this.OptionsButton.setEnabled(false);
            new OptionsDlg(this, "Options", true, this.bjOptions).setVisible(true);
            if (this.bjOptions.isChanged()) {
                this.deck.reset(this.bjOptions.numberDecks, this.bjOptions.penetration);
                this.ShufflingLabel.setVisible(true);
                this.pause = 300;
                this.handControl = 16;
                this.delay = 3;
                this.deck.setVisible(false);
                this.setPlacard();
            }
            this.OptionsButton.setEnabled(true);
            return;
        }
        new BasicStrategyChart(this, "Strategy", this.bjOptions, this.bjOptions.doubleOn_10_or_11_only, this.bjOptions.doubleAfterSplitAllowed, this.bjOptions.surrenderAllowed, this.bjOptions.numberDecks != 1).setVisible(true);
    }
    
    void HelpButton_actionPerformed(final ActionEvent actionEvent) {
        new HelpFrame(this, "Help").setVisible(true);
    }
    
    void Blackjack_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void addToBank(final int n) {
        final int n2 = this.baseStack.getChipsBought() * 2;
        final int countHalfChips = this.baseStack.countHalfChips();
        final int n3 = countHalfChips - n2;
        this.chipsBoughtAmountLabel.setText(this.convertToDollarString(n2));
        this.chipsOnhandAmountLabel.setText(this.convertToDollarString(countHalfChips));
        this.netAmountLabel.setText(this.convertToDollarString(n3));
        if (n3 < 0) {
            this.netLabel.setText("Losing");
        }
        else {
            this.netLabel.setText("Winning");
        }
        this.bank.addToBank(n * 50);
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        try {
            this.t.join();
        }
        catch (InterruptedException ex) {}
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == Blackjack.this) {
                Blackjack.this.Blackjack_WindowClosing(windowEvent);
            }
        }
    }
}
