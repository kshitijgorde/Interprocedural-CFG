// 
// Decompiled by Procyon v0.5.30
// 

public interface CardProducer
{
    public static final int cardsInOneDeck = 52;
    
    void shuffle();
    
    Card getNextCard();
}
