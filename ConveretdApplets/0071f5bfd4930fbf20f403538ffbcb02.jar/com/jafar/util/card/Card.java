// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.util.card;

public class Card
{
    int deckNum;
    int cardNum;
    
    public Card(final int deckNum, final int cardNum) {
        this.deckNum = deckNum;
        this.cardNum = cardNum;
    }
    
    public int getSuite() {
        return this.cardNum / 13;
    }
    
    public int getCardNum() {
        return this.cardNum % 13;
    }
    
    public int getCardValue() {
        final int n = this.cardNum % 13;
        if (n == 0) {
            return 11;
        }
        if (n > 0 && n < 10) {
            return n + 1;
        }
        return 10;
    }
}
