import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hands implements MouseListener
{
    final int maxHands = 5;
    final int halfHandWidth = -70;
    final int handHeight = 155;
    Frame parent;
    BJOptions bjOptions;
    int top;
    int center;
    int numHands;
    int numStartHands;
    Hand[] hand;
    int currentHand;
    BaseStack baseStack;
    boolean newBetMade;
    
    Hands(final Frame parent, final int center, final int n, final BaseStack baseStack, final CardProducer cardProducer, final BJOptions bjOptions) {
        Hand.setup(this.parent = parent, baseStack, cardProducer, bjOptions, this);
        this.baseStack = baseStack;
        this.top = n - 155 - 1;
        this.center = center;
        this.bjOptions = bjOptions;
        this.numHands = 0;
        this.currentHand = 0;
        this.hand = new Hand[6];
        for (int i = 1; i <= 5; ++i) {
            (this.hand[i] = new Hand(this.center + 70, this.top)).setVisible(false);
            this.parent.add(this.hand[i]);
        }
        this.baseStack.addMouseListener(this);
    }
    
    public int getTotalBet() {
        int n = 0;
        for (int i = 1; i <= this.numHands; ++i) {
            n += this.hand[i].getBet();
        }
        return n;
    }
    
    public boolean atMaxNumHands() {
        return this.numHands == 5;
    }
    
    public void split() {
        if (this.numHands < 5) {
            if (this.hand[this.currentHand].getBet() > this.baseStack.countChips()) {
                this.baseStack.buyChips(this.hand[this.currentHand].getBet() - this.baseStack.countChips());
            }
            if (this.hand[this.currentHand].getBet() <= this.baseStack.countChips()) {
                final Hand hand = this.hand[this.numHands + 1];
                for (int i = 1; i <= this.currentHand; ++i) {
                    this.hand[i].setLocation(this.hand[i].getX() + 70, this.hand[i].getY());
                }
                for (int j = this.numHands; j >= this.currentHand + 1; --j) {
                    this.hand[j].setLocation(this.hand[j].getX() - 70, this.hand[j].getY());
                    this.hand[j + 1] = this.hand[j];
                }
                ++this.numHands;
                this.hand[this.currentHand + 1] = hand;
                this.hand[this.currentHand].createSplitHand(this.hand[this.currentHand + 1]);
                this.hand[this.currentHand + 1].setLocation(this.hand[this.currentHand].getLocation().x - 140, this.top);
                this.hand[this.currentHand + 1].setVisible(true);
            }
        }
    }
    
    public void increaseBets(int n) {
        this.newBetMade = true;
        if (n + ((this.numHands == 0) ? 0 : this.hand[1].getBet()) > this.bjOptions.getMaxBet()) {
            n = this.bjOptions.getMaxBet() - ((this.numHands == 0) ? 0 : this.hand[1].getBet());
        }
        if (n + ((this.numHands == 0) ? 0 : this.hand[1].getBet()) < this.bjOptions.getMinBet()) {
            n = this.bjOptions.getMinBet() - ((this.numHands == 0) ? 0 : this.hand[1].getBet());
        }
        if (n * this.numStartHands > this.baseStack.countChips() && !this.baseStack.buyChips(n * this.numStartHands - this.baseStack.countChips())) {
            return;
        }
        final int n2 = this.center - this.numStartHands * -70;
        for (int i = 1; i <= this.numStartHands; ++i) {
            if (!this.hand[i].isVisible()) {
                this.hand[i].setLocation(n2 + (i - 1) * 2 * -70, this.top);
                this.hand[i].setVisible(true);
            }
            this.hand[i].placeBet(n);
        }
        this.numHands = this.numStartHands;
    }
    
    public void setNumStartHands(final int numStartHands) {
        if (this.numStartHands != numStartHands) {
            this.clear();
            this.numStartHands = numStartHands;
            this.bjOptions.setnumHandsPlayed(numStartHands);
        }
    }
    
    public boolean dealCard() {
        if (this.currentHand == 0) {
            this.currentHand = 1;
        }
        this.hand[this.currentHand].dealCard();
        ++this.currentHand;
        if (this.currentHand > this.numHands) {
            this.currentHand = 0;
            return true;
        }
        return false;
    }
    
    public void done() {
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].done();
        }
    }
    
    public void checkBJs() {
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].checkBJ();
        }
    }
    
    public boolean handsComplete() {
        if (this.currentHand == 0) {
            this.currentHand = 1;
        }
        boolean b = false;
        switch (this.hand[this.currentHand].getHandStatus()) {
            case 5: {
                this.hand[this.currentHand].myTurn();
                break;
            }
            case 8:
            case 9:
            case 10: {
                ++this.currentHand;
                if (this.currentHand > this.numHands) {
                    b = true;
                    this.currentHand = 0;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    public boolean allHandsDetermined() {
        for (int i = 1; i <= this.numHands; ++i) {
            if (this.hand[i].getHandStatus() == 8) {
                return false;
            }
        }
        return true;
    }
    
    public void determineResults(final int n) {
        for (int i = 1; i <= this.numHands; ++i) {
            if (this.hand[i].getHandStatus() == 8) {
                if (this.hand[i].getValue() > n) {
                    this.hand[i].win();
                }
                if (this.hand[i].getValue() == n) {
                    this.hand[i].push();
                }
                if (this.hand[i].getValue() < n) {
                    this.hand[i].lose();
                }
            }
        }
    }
    
    public void clear() {
        this.currentHand = 0;
        this.numHands = 0;
        this.newBetMade = true;
        for (int i = 1; i <= 5; ++i) {
            this.hand[i].setVisible(false);
            this.hand[i].clearCards();
        }
    }
    
    public boolean isNewBetMade() {
        final boolean newBetMade = this.newBetMade;
        this.newBetMade = false;
        return newBetMade;
    }
    
    public boolean betsMade() {
        if (this.numHands == 0) {
            return false;
        }
        for (int i = 1; i <= this.numHands; ++i) {
            if (this.hand[i].getBet() < this.bjOptions.getMinBet()) {
                return false;
            }
        }
        return true;
    }
    
    private int getCurrentBet() {
        if (this.numHands == 0) {
            return 0;
        }
        return this.hand[1].getBet();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            final int chipValue = ((ChipStack)mouseEvent.getComponent()).getChipValue();
            if (this.numHands == 0) {
                this.increaseBets(chipValue);
                this.newBetMade = true;
            }
            else if (this.hand[1].getHandStatus() == 3 || this.hand[1].getHandStatus() == 2) {
                this.increaseBets(chipValue);
                this.newBetMade = true;
            }
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0 && this.numHands != 0 && (this.hand[1].getHandStatus() == 3 || this.hand[1].getHandStatus() == 2)) {
            this.clear();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void setShowTotal(final boolean showTotal) {
        Hand.setShowTotal(showTotal);
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].resetShowTotal();
        }
    }
    
    public void askForInsurance() {
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].askForInsurance();
        }
    }
    
    public boolean isInsuranceDone() {
        for (int i = 1; i <= this.numHands; ++i) {
            if (!this.hand[i].isInsuranceDone()) {
                return false;
            }
        }
        return true;
    }
    
    public void dealerHasBJ(final boolean b) {
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].dealerHasBJ(b);
        }
    }
    
    public void paintInsuranceBet(final Graphics graphics) {
        for (int i = 1; i <= this.numHands; ++i) {
            this.hand[i].paintInsuranceBet(graphics);
        }
    }
    
    public synchronized boolean insuranceBetMade() {
        for (int i = 1; i <= this.numHands; ++i) {
            if (this.hand[i].insuranceBetMade()) {
                return true;
            }
        }
        return false;
    }
}
