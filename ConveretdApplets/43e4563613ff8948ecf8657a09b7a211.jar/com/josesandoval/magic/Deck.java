// 
// Decompiled by Procyon v0.5.30
// 

package com.josesandoval.magic;

public class Deck
{
    private Card[] deck;
    private int cardsUsed;
    
    public Deck() {
        this.deck = new Card[52];
        int n = 0;
        for (int i = 0; i <= 3; ++i) {
            for (int j = 1; j <= 13; ++j) {
                this.deck[n] = new Card(j, i);
                ++n;
            }
        }
        this.cardsUsed = 0;
    }
    
    public int cardsLeft() {
        return 52 - this.cardsUsed;
    }
    
    public Card dealCard() {
        if (this.cardsUsed == 52) {
            this.shuffle();
        }
        ++this.cardsUsed;
        return this.deck[this.cardsUsed - 1];
    }
    
    public Card[] getDeck() {
        return this.deck;
    }
    
    public void shuffle() {
        for (int i = 51; i > 0; --i) {
            final int n = (int)(Math.random() * (i + 1));
            final Card card = this.deck[i];
            this.deck[i] = this.deck[n];
            this.deck[n] = card;
        }
        this.cardsUsed = 0;
    }
}
