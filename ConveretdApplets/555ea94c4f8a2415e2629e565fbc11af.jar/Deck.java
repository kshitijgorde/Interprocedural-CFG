// 
// Decompiled by Procyon v0.5.30
// 

public class Deck implements CardProducer
{
    private long[] unusedCards;
    protected int numberOfDecks;
    protected int numUnusedCards;
    
    public Deck() {
        this(1);
    }
    
    public Deck(final int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        this.unusedCards = new long[this.numberOfDecks + 1];
        this.shuffle();
    }
    
    public void shuffle() {
        for (int i = 1; i <= this.numberOfDecks; ++i) {
            this.unusedCards[i] = 0L;
        }
        this.numUnusedCards = this.numberOfDecks * 52;
    }
    
    public Card getNextCard() {
        if (this.numUnusedCards == 0) {
            this.shuffle();
        }
        int i = (int)(Math.random() * this.numUnusedCards + 1.0);
        --this.numUnusedCards;
        int n = 1;
        int n2 = 0;
        do {
            if (++n2 > 52) {
                ++n;
                n2 = 1;
            }
            if ((1L << n2 - 1 & this.unusedCards[n]) == 0x0L) {
                --i;
            }
        } while (i != 0);
        this.unusedCards[n] |= 1L << n2 - 1;
        return new Card(n2);
    }
    
    public int getNumUnusedCards() {
        return this.numUnusedCards;
    }
}
