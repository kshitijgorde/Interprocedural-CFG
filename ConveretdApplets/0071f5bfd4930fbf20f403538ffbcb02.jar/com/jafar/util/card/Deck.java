// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.util.card;

import java.util.Stack;

public class Deck
{
    Stack cards;
    int numDecks;
    
    public Deck(final int n) {
        this.init(n);
    }
    
    public void init(final int numDecks) {
        this.numDecks = numDecks;
        this.shuffleDeck();
    }
    
    public int cardsLeft() {
        if (this.cards == null) {
            return 0;
        }
        return this.cards.size();
    }
    
    public void shuffleDeck() {
        final int n = 52 * this.numDecks;
        final int[] array = new int[n];
        this.cards = new Stack();
        for (int i = 0; i < n; ++i) {
            array[i] = i;
        }
        for (int j = 0; j < n; ++j) {
            final int n2 = (int)Math.floor(Math.random() * 52.0);
            final int n3 = array[n2];
            array[n2] = array[j];
            array[j] = n3;
        }
        for (int k = 0; k < n; ++k) {
            this.cards.push(new Card(array[k] / 52, array[k] % 52));
        }
    }
    
    public Card getNextCard() {
        if (this.cards.empty()) {
            return null;
        }
        return this.cards.pop();
    }
}
