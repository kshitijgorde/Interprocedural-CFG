// 
// Decompiled by Procyon v0.5.30
// 

package com.josesandoval.magic;

public class Card
{
    public static final int SPADES = 1;
    public static final int HEARTS = 3;
    public static final int DIAMONDS = 2;
    public static final int CLUBS = 0;
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    private final int suit;
    private final int value;
    
    public Card(final int value, final int suit) {
        this.value = value;
        this.suit = suit;
    }
    
    public int getSuit() {
        return this.suit;
    }
    
    public String getSuitAsString() {
        switch (this.suit) {
            case 1: {
                return "Spades";
            }
            case 3: {
                return "Hearts";
            }
            case 2: {
                return "Diamonds";
            }
            case 0: {
                return "Clubs";
            }
            default: {
                return "??";
            }
        }
    }
    
    public int getValue() {
        return this.value;
    }
    
    public String getValueAsString() {
        switch (this.value) {
            case 1: {
                return "Ace";
            }
            case 2: {
                return "2";
            }
            case 3: {
                return "3";
            }
            case 4: {
                return "4";
            }
            case 5: {
                return "5";
            }
            case 6: {
                return "6";
            }
            case 7: {
                return "7";
            }
            case 8: {
                return "8";
            }
            case 9: {
                return "9";
            }
            case 10: {
                return "10";
            }
            case 11: {
                return "Jack";
            }
            case 12: {
                return "Queen";
            }
            case 13: {
                return "King";
            }
            default: {
                return "??";
            }
        }
    }
    
    public String toString() {
        return String.valueOf(this.getValueAsString()) + " of " + this.getSuitAsString();
    }
}
