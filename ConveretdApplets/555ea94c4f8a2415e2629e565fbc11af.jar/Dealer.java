import java.awt.Component;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class Dealer
{
    private static final int NewCardLeft = 75;
    public static final int NOTDONE = 1;
    public static final int BUST = 0;
    private static final int maxLeft = 625;
    private Container parent;
    private int top;
    private int left;
    private BJOptions bjOptions;
    private CardProducer deck;
    private CardPanel[] cards;
    private int numCards;
    private TotalPanel totalPanel;
    private boolean showTotal;
    private boolean soft;
    
    Dealer(final Container parent, final int top, final int left, final BJOptions bjOptions, final CardProducer deck) {
        this.cards = new CardPanel[12];
        this.showTotal = false;
        this.parent = parent;
        this.top = top;
        this.left = left;
        this.bjOptions = bjOptions;
        this.deck = deck;
        this.showTotal = false;
        (this.totalPanel = new TotalPanel(left - 24, top + 81)).setVisible(this.showTotal);
        this.parent.add(this.totalPanel, 0);
    }
    
    public void firstCard() {
        this.cards[0] = new CardPanel(this.left, this.top, this.deck.getNextCard(), 2);
        this.parent.add(this.cards[0], 0);
        this.numCards = 1;
    }
    
    public int secondCard() {
        this.cards[1] = new CardPanel(this.cards[0].getX() + 75, this.top, this.deck.getNextCard(), 1);
        this.parent.add(this.cards[1], 0);
        this.totalPanel.setString(String.valueOf(this.cards[1].getCard().getBJValue()));
        this.totalPanel.setVisible(this.showTotal);
        ++this.numCards;
        return this.cards[1].getCard().getBJValue();
    }
    
    public int showFirstCard() {
        this.cards[0].showFront();
        this.totalPanel.setString(String.valueOf(this.getValueNI()));
        return this.getValue();
    }
    
    public int nextCard() {
        if (this.cards[this.numCards - 1].getX() + 75 > 625) {
            for (int i = 0; i < this.numCards; ++i) {
                this.cards[i].setLocation(this.cards[i].getLocation().x - 75, this.cards[i].getLocation().y);
            }
            this.totalPanel.setLocation(this.totalPanel.getLocation().x - 75, this.totalPanel.getLocation().y);
        }
        this.cards[this.numCards] = new CardPanel(this.cards[this.numCards - 1].getX() + 75, this.top, this.deck.getNextCard(), 1);
        this.parent.add(this.cards[this.numCards], 0);
        ++this.numCards;
        this.totalPanel.setString(String.valueOf(this.getValueNI()));
        return this.getValue();
    }
    
    private int getValueNI() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.numCards; ++i) {
            n2 += this.cards[i].getCard().getBJValue();
            if (this.cards[i].getCard().getRank() == 1) {
                ++n;
            }
            if (n2 > 21 && n > 0) {
                --n;
                n2 -= 10;
            }
        }
        this.soft = (n > 0);
        return n2;
    }
    
    public int getValue() {
        int valueNI = this.getValueNI();
        if (valueNI < 17) {
            valueNI = 1;
        }
        if (this.bjOptions.hitSoft17 && valueNI == 17 && this.soft) {
            valueNI = 1;
        }
        if (valueNI > 21) {
            valueNI = 0;
        }
        return valueNI;
    }
    
    public void clear() {
        for (int i = 0; i < this.numCards; ++i) {
            this.parent.remove(this.cards[i]);
            this.cards[i] = null;
        }
        this.numCards = 0;
        this.totalPanel.setVisible(false);
        this.totalPanel.setLocation(this.left - 24, this.top + 81);
        this.parent.repaint(this.left, this.top, this.parent.getSize().width - this.left, 96);
    }
    
    public void setShowTotal(final boolean showTotal) {
        this.showTotal = showTotal;
        if (this.numCards > 1) {
            this.totalPanel.setVisible(this.showTotal);
        }
    }
}
