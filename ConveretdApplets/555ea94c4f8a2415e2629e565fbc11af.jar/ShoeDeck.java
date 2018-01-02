import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShoeDeck extends Panel implements CardProducer
{
    private boolean needsShuffled;
    private int shufflePoint;
    private Deck deck;
    
    public void reset(final int n, final int n2) {
        this.deck = new Deck(n);
        this.needsShuffled = false;
        this.shufflePoint = n * 52 / n2;
        this.setSize(71, n * 52);
        this.setBackground(Color.gray);
    }
    
    public boolean getNeedsShuffled() {
        return this.needsShuffled;
    }
    
    public void shuffle() {
        this.deck.shuffle();
        this.needsShuffled = false;
        this.repaint();
    }
    
    public Card getNextCard() {
        if (this.getParent() != null) {
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this.getBackground());
            graphics.drawLine(0, this.getHeight() - this.deck.getNumUnusedCards(), this.getWidth(), this.getHeight() - this.deck.getNumUnusedCards());
        }
        if (this.shufflePoint >= this.deck.getNumUnusedCards() - 1) {
            this.needsShuffled = true;
        }
        return this.deck.getNextCard();
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        graphics.setColor(new Color(128, 0, 0));
        graphics.fillRect(0, this.getHeight() - this.deck.getNumUnusedCards(), this.getWidth(), this.deck.getNumUnusedCards());
        if (this.shufflePoint < this.deck.getNumUnusedCards()) {
            graphics.setColor(Color.yellow);
            graphics.drawLine(0, this.getHeight() - this.shufflePoint, this.getWidth(), this.getHeight() - this.shufflePoint);
        }
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
}
