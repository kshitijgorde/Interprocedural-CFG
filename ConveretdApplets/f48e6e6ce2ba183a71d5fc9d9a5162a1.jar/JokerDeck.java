// 
// Decompiled by Procyon v0.5.30
// 

public class JokerDeck extends Deck implements CardProducer
{
    protected int jokersInDeck;
    protected int numStartJokers;
    
    public JokerDeck() {
        this(1, 1);
    }
    
    public JokerDeck(final int n, final int numStartJokers) {
        super(n);
        this.numStartJokers = numStartJokers;
        this.jokersInDeck = this.numStartJokers;
    }
    
    public JokerDeck(final int n) {
        this(n, n);
    }
    
    public void shuffle() {
        super.shuffle();
        this.jokersInDeck = this.numStartJokers;
    }
    
    public int getNumUnusedCards() {
        return super.numUnusedCards + this.jokersInDeck;
    }
    
    public Card getNextCard() {
        if (this.getNumUnusedCards() == 0) {
            this.shuffle();
        }
        if ((int)(Math.random() * this.getNumUnusedCards()) + 1 > super.numUnusedCards) {
            --this.jokersInDeck;
            return new Card(53);
        }
        return super.getNextCard();
    }
}
