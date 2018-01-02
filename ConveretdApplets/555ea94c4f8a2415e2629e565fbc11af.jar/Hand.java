import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hand extends Panel implements Selection, MouseListener
{
    private static final int chipBottom = 150;
    private static final int chipApart = 32;
    private static final int chipLeft = 2;
    private static final int maxCards = 20;
    private static final int firstCardLeft = 5;
    private static final int firstCardTop = 5;
    private static final Color squareColor;
    private static final int squareHeight = 155;
    private static final int squareWidth = 135;
    private static final int nextCardLeft = 12;
    private static final int nextCardUp = 0;
    private static final int maxBetHeight = 100;
    private static final int actionButtonsLeft = 15;
    private static final int actionButtonsTop = 104;
    private static final int questionLeft = 5;
    private static final int questionTop = 104;
    private static final int insuranceBetXOffset = 40;
    private static final int insuranceBetYOffset = -10;
    static final int INACTIVE = 1;
    static final int WAITINGBET = 2;
    static final int BETMADE = 3;
    static final int GOTCARDS = 5;
    static final int ACTIVE = 6;
    static final int SPLITONE = 7;
    static final int HANDCOMPLETE = 8;
    static final int IWIN = 9;
    static final int ILOSE = 10;
    static final int IPUSH = 11;
    static final int NOHAND = 12;
    private static final int SIDEXOFF = 5;
    private static final int SIDEYOFF = 35;
    private static CardProducer deck;
    private static BaseStack baseStack;
    private static Frame parent;
    private static BJOptions bjOptions;
    private static Hands handController;
    private static final int TOTALPANELLEFT = 5;
    private static final int TOTALPANELTOP = 35;
    private static boolean showTotal;
    private TotalPanel totalPanel;
    private int handStatus;
    private boolean soft;
    private int numberOfCards;
    private CardPanel[] cards;
    private boolean isSplitHand;
    private boolean noHit;
    private Bet win1;
    private Bet win2;
    private Bet bet1;
    private Bet doubleBet;
    private ActionButtons actionButtons;
    private MiniDlg question;
    private int whatQuestion;
    private InsuranceBet insuranceBet;
    private static int insuranceBetSeperation;
    private static final String[] actionStrings;
    
    public static void setup(final Frame parent, final BaseStack baseStack, final CardProducer deck, final BJOptions bjOptions, final Hands handController) {
        Hand.baseStack = baseStack;
        Hand.deck = deck;
        Hand.parent = parent;
        Hand.bjOptions = bjOptions;
        Hand.handController = handController;
    }
    
    Hand(final int n, final int n2) {
        this.totalPanel = new TotalPanel(5, 35);
        this.setLocation(n, n2);
        this.setSize(136, 156);
        this.setBackground(new Color(0, 128, 0));
        this.handStatus = 2;
        this.win1 = new Bet(66, 50, 100, Hand.baseStack);
        this.win2 = new Bet(98, 50, 100, Hand.baseStack);
        this.doubleBet = new Bet(2, 50, 100, Hand.baseStack);
        this.add(this.bet1 = new Bet(34, 50, 100, Hand.baseStack), 0);
        this.add(this.win1, 0);
        this.add(this.win2, 0);
        this.add(this.doubleBet, 0);
        (this.actionButtons = new ActionButtons(this)).setLocation(15, 104);
        this.add(this.actionButtons, 0);
        this.actionButtons.setVisible(false);
        this.isSplitHand = false;
        this.cards = new CardPanel[20];
        this.setLayout(null);
    }
    
    private void dealCard(final boolean b) {
        ++this.numberOfCards;
        this.handStatus = 5;
        this.cards[this.numberOfCards] = new CardPanel(5 + (this.numberOfCards - 1) * 12, 5 + (this.numberOfCards - 1) * 0, Hand.deck.getNextCard(), 1, b);
        if (b) {
            this.cards[this.numberOfCards].setLocation(5, 35);
            this.remove(this.totalPanel);
        }
        this.add(this.cards[this.numberOfCards], 0);
        if (b) {
            this.add(this.totalPanel, 0);
        }
        String string = String.valueOf(this.getValue());
        if (this.isSoft()) {
            string = String.valueOf(string) + "s";
        }
        this.totalPanel.setString(string);
        if (this.numberOfCards == 2) {
            this.add(this.totalPanel, 0);
        }
        if (Hand.showTotal && this.numberOfCards >= 2) {
            this.totalPanel.setVisible(true);
        }
        else {
            this.totalPanel.setVisible(false);
        }
        this.cards[this.numberOfCards].addMouseListener(this);
    }
    
    public void dealCard() {
        this.dealCard(false);
    }
    
    public void placeBet(int n) {
        if (this.handStatus == 2 || this.handStatus == 3) {
            if (n + this.bet1.getAmount() > Hand.bjOptions.getMaxBet()) {
                n = Hand.bjOptions.getMaxBet() - this.bet1.getAmount();
            }
            if (n <= Hand.baseStack.countChips()) {
                this.bet1.setAmount(this.bet1.getAmount() + n);
                Hand.baseStack.removeChips(n);
                this.bet1.setBetCaption(" " + String.valueOf(this.bet1.getAmount()));
                if (this.bet1.getAmount() >= Hand.bjOptions.getMinBet()) {
                    this.handStatus = 3;
                    return;
                }
                this.handStatus = 2;
            }
        }
    }
    
    public int getBet() {
        return this.bet1.getAmount();
    }
    
    private void myTurnOver() {
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    public void myTurn() {
        this.handStatus = 6;
        this.actionButtons.setVisible(true);
        this.drawSquare();
        if (this.numberOfCards == 1) {
            this.dealSplitCard();
        }
        this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
    }
    
    private void drawSquare() {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(Hand.squareColor);
        graphics.drawRect(0, 0, 135, 155);
    }
    
    private void eraseSquare() {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(new Color(0, 128, 0));
        graphics.drawRect(0, 0, 135, 155);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        if (this.handStatus == 6) {
            graphics.setColor(Hand.squareColor);
            graphics.drawRect(0, 0, 135, 155);
        }
    }
    
    public int getHandStatus() {
        return this.handStatus;
    }
    
    public int getValue() {
        int n = 0;
        int n2 = 0;
        this.soft = false;
        for (int i = 1; i <= this.numberOfCards; ++i) {
            n2 += this.cards[i].getCard().getBJValue();
            if (this.cards[i].getCard().getRank() == 1) {
                ++n;
            }
        }
        while (n2 > 21 && n > 0) {
            n2 -= 10;
            --n;
        }
        if (n > 0) {
            this.soft = true;
        }
        return n2;
    }
    
    public boolean isSoft() {
        return this.soft;
    }
    
    public void checkBJ() {
        if (this.getValue() == 21 && this.handStatus != 9) {
            this.win2.setHalf();
            this.win2.setBetCaption("  Win");
            this.win2.setAmount(this.bet1.getAmount());
            this.win1.setBetCaption("  Win");
            this.win1.setAmount(this.bet1.getAmount());
            this.handStatus = 9;
            this.actionButtons.setVisible(false);
            this.update(this.getGraphics());
        }
    }
    
    public void win() {
        if (this.doubleBet.getAmount() != 0) {
            this.win2.setFull();
            this.win2.setBetCaption("  Win");
            this.win2.setAmount(this.doubleBet.getAmount());
        }
        this.win1.setBetCaption("  Win");
        this.win1.setAmount(this.bet1.getAmount());
        this.handStatus = 9;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    public void lose() {
        this.bet1.setAmount(0);
        if (this.getValue() > 21) {
            this.bet1.setBetCaption(" Bust");
        }
        else {
            this.bet1.setBetCaption("  Lose");
        }
        this.doubleBet.setAmount(0);
        this.doubleBet.setBetCaption("");
        this.handStatus = 10;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    public void push() {
        this.handStatus = 11;
        this.bet1.setBetCaption("  Push");
        if (this.doubleBet.getAmount() != 0) {
            this.doubleBet.setBetCaption("  Push");
        }
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    public void clearCards() {
        for (int i = 1; i <= this.numberOfCards; ++i) {
            this.remove(this.cards[i]);
            this.cards[i] = null;
        }
        this.numberOfCards = 0;
        this.isSplitHand = false;
        this.totalPanel.setVisible(false);
        this.bet1.clear();
        this.bet1.setFull();
        this.doubleBet.clear();
        this.win1.clear();
        this.win2.clear();
        this.win2.setFull();
        this.repaint();
        if (this.insuranceBet != null) {
            this.insuranceBet.clear();
            this.insuranceBet = null;
        }
        this.handStatus = 2;
    }
    
    public boolean getDoubleStatus() {
        return !this.noHit && this.numberOfCards == 2 && (!this.isSplitHand || Hand.bjOptions.doubleAfterSplitAllowed) && (!Hand.bjOptions.doubleOn_10_or_11_only || (this.getValue() == 10 || this.getValue() == 11));
    }
    
    public void doubleDown() {
        if (Hand.bjOptions.isChecking()) {
            final int correctAction = this.getCorrectAction();
            if (correctAction != 4) {
                this.ask(4, String.valueOf(Hand.actionStrings[correctAction]) + " is Correct", "Double", "Cancel");
                return;
            }
            this.continueDoubleDown();
        }
        else {
            if (this.getValue() > 11 && !this.isSoft()) {
                this.ask(4, "Double hard " + String.valueOf(this.getValue()), "Yes", "Cancel");
                return;
            }
            this.continueDoubleDown();
        }
    }
    
    private void continueDoubleDown() {
        if (this.getBet() > Hand.baseStack.countChips()) {
            Hand.baseStack.buyChips(this.getBet() - Hand.baseStack.countChips());
        }
        if (this.getBet() <= Hand.baseStack.countChips()) {
            this.doubleDownAmount(this.getBet());
            return;
        }
        this.doubleDownAmount(Hand.baseStack.countChips());
    }
    
    private void doubleDownAmount(final int amount) {
        if (this.handStatus == 6) {
            this.doubleBet.setAmount(amount);
            Hand.baseStack.removeChips(amount);
            this.doubleBet.setBetCaption("  Dbl");
            this.dealCard(true);
            this.handStatus = 8;
            if (this.getValue() > 21) {
                this.lose();
            }
            this.actionButtons.setVisible(false);
            this.update(this.getGraphics());
        }
    }
    
    public boolean getSplitStatus() {
        return !Hand.handController.atMaxNumHands() && (this.numberOfCards == 2 && this.cards[1].getCard().getBJValue() == this.cards[2].getCard().getBJValue()) && (!this.isSplitHand || Hand.bjOptions.resplitPairsAllowed);
    }
    
    public void split() {
        if (!Hand.bjOptions.isChecking()) {
            Hand.handController.split();
            return;
        }
        final int correctAction = this.getCorrectAction();
        if (correctAction != 3) {
            this.ask(3, String.valueOf(Hand.actionStrings[correctAction]) + " is Correct", "Split", "Cancel");
            return;
        }
        Hand.handController.split();
    }
    
    private void continueSplit() {
        Hand.handController.split();
    }
    
    public void createSplitHand(final Hand hand) {
        hand.numberOfCards = 1;
        hand.cards[1] = this.cards[2];
        this.remove(this.cards[2]);
        hand.add(this.cards[2], 0);
        hand.cards[1].setX(5);
        hand.bet1.setAmount(this.bet1.getAmount());
        hand.bet1.setBetCaption("  " + String.valueOf(hand.bet1.getAmount()));
        Hand.baseStack.removeChips(hand.bet1.getAmount());
        hand.handStatus = 5;
        hand.isSplitHand = true;
        this.numberOfCards = 1;
        this.noHit = false;
        this.isSplitHand = true;
        this.dealSplitCard();
    }
    
    private void dealSplitCard() {
        if (this.cards[1].getCard().getRank() != 1) {
            this.dealCard(false);
            this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
            return;
        }
        if (Hand.bjOptions.resplitAcesAllowed) {
            this.dealCard(false);
        }
        else {
            this.dealCard(true);
        }
        if (this.cards[2].getCard().getRank() == 1 && Hand.bjOptions.resplitAcesAllowed) {
            this.noHit = true;
            this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
            return;
        }
        this.handStatus = 8;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    private boolean getSurrenderStatus() {
        return this.handStatus == 6 && this.numberOfCards == 2 && Hand.bjOptions.surrenderAllowed && !this.isSplitHand;
    }
    
    public void surrender() {
        if (!Hand.bjOptions.isChecking()) {
            this.continueSurrender();
            return;
        }
        final int correctAction = this.getCorrectAction();
        if (correctAction != 5) {
            this.ask(5, String.valueOf(Hand.actionStrings[correctAction]) + " is Correct", "Surr.", "Cancel");
            return;
        }
        this.continueSurrender();
    }
    
    private void continueSurrender() {
        final int amount = this.bet1.getAmount();
        this.bet1.setHalf();
        this.bet1.setAmount(amount);
        this.bet1.setBetCaption("  1/2");
        this.handStatus = 10;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    private boolean getHitStatus() {
        return !this.noHit;
    }
    
    private int getCorrectAction() {
        return Hand.bjOptions.checkBasicStrategy(this.getValue(), this.soft, this.getDoubleStatus(), this.getSplitStatus(), this.handStatus == 6 && this.numberOfCards == 2 && Hand.bjOptions.surrenderAllowed && !this.isSplitHand);
    }
    
    public void hit() {
        if (Hand.bjOptions.isChecking()) {
            final int correctAction = this.getCorrectAction();
            if (correctAction != 1) {
                this.ask(1, String.valueOf(Hand.actionStrings[correctAction]) + " is Correct", "Hit", "Cancel");
                return;
            }
            this.dealCard();
            if (this.getValue() > 21) {
                this.lose();
                return;
            }
            this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
        }
        else {
            if (this.getValue() >= 17 && !this.soft) {
                this.ask(1, "Hit Hard " + String.valueOf(this.getValue()) + "?", "Hit", "Cancel");
                return;
            }
            if (this.getValue() >= 20) {
                this.ask(1, "Hit Soft " + String.valueOf(this.getValue()) + "?", "Yes", "Cancel");
                return;
            }
            this.dealCard();
            if (this.getValue() > 21) {
                this.lose();
                return;
            }
            this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
        }
    }
    
    private void continueHit() {
        this.dealCard();
        if (this.getValue() > 21) {
            this.lose();
            return;
        }
        this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
    }
    
    public void stand() {
        if (!Hand.bjOptions.isChecking()) {
            this.handStatus = 8;
            this.actionButtons.setVisible(false);
            this.update(this.getGraphics());
            return;
        }
        final int correctAction = this.getCorrectAction();
        if (correctAction != 2) {
            this.ask(2, String.valueOf(Hand.actionStrings[correctAction]) + " is Correct", "Stand", "Cancel");
            return;
        }
        this.handStatus = 8;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    private void continueStand() {
        this.handStatus = 8;
        this.actionButtons.setVisible(false);
        this.update(this.getGraphics());
    }
    
    private void setActionButtons() {
        this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
    }
    
    public void done() {
        this.handStatus = 8;
    }
    
    public static void setShowTotal(final boolean showTotal) {
        Hand.showTotal = showTotal;
    }
    
    public void resetShowTotal() {
        if (Hand.showTotal && this.numberOfCards >= 2) {
            this.totalPanel.setVisible(true);
            return;
        }
        this.totalPanel.setVisible(false);
    }
    
    public int getX() {
        return this.getLocation().x;
    }
    
    public int getY() {
        return this.getLocation().y;
    }
    
    public void askForInsurance() {
        this.ask(6, "Insurance?", (this.getValue() == 21) ? "Even $" : "Yes", "No");
    }
    
    public void continueInsurance() {
        if (this.getValue() == 21) {
            this.win();
            return;
        }
        this.insuranceBet = new InsuranceBet(this.getLocation().x + 40, this.getLocation().y - 10, Hand.insuranceBetSeperation, Hand.baseStack);
        if (this.getBet() > Hand.baseStack.countHalfChips()) {
            Hand.baseStack.buyChips((this.getBet() - Hand.baseStack.countHalfChips() + 1) / 2);
        }
        this.insuranceBet.setBet(this.getBet());
        Hand.baseStack.removeHalf(this.insuranceBet.getBet());
        Hand.parent.repaint();
    }
    
    public boolean isInsuranceDone() {
        return this.question == null;
    }
    
    public void dealerHasBJ(final boolean b) {
        if (this.insuranceBet != null) {
            if (b && this.insuranceBet != null) {
                this.insuranceBet.win();
            }
            else {
                this.insuranceBet.lose();
            }
            Hand.parent.repaint();
        }
    }
    
    public void paintInsuranceBet(final Graphics graphics) {
        if (this.insuranceBet != null) {
            this.insuranceBet.paint(graphics);
        }
    }
    
    public boolean insuranceBetMade() {
        return this.insuranceBet != null;
    }
    
    private void ask(final int whatQuestion, final String s, final String s2, final String s3) {
        this.whatQuestion = whatQuestion;
        (this.question = new MiniDlg(s, s2, s3, this)).setLocation(5, 104);
        this.add(this.question, 0);
    }
    
    public void selectYes() {
        this.remove(this.question);
        this.question = null;
        switch (this.whatQuestion) {
            case 1: {
                this.dealCard();
                if (this.getValue() > 21) {
                    this.lose();
                    return;
                }
                this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
            }
            case 2: {
                this.handStatus = 8;
                this.actionButtons.setVisible(false);
                this.update(this.getGraphics());
            }
            case 4: {
                this.continueDoubleDown();
            }
            case 3: {
                Hand.handController.split();
            }
            case 5: {
                this.continueSurrender();
            }
            case 6: {
                this.continueInsurance();
            }
            default: {}
        }
    }
    
    public void selectNo() {
        this.remove(this.question);
        this.question = null;
        if (this.handStatus == 6) {
            this.actionButtons.setButtons(!this.noHit, true, this.getSplitStatus(), this.getDoubleStatus());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0 && this.handStatus == 6 && this.numberOfCards == 2 && Hand.bjOptions.surrenderAllowed && !this.isSplitHand) {
            this.surrender();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static {
        squareColor = Color.white;
        Hand.insuranceBetSeperation = 38;
        actionStrings = new String[] { "", "Hit", "Stand", "Split", "Double", "Surrender" };
    }
}
