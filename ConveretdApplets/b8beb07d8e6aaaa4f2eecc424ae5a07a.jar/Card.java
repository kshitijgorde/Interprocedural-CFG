// 
// Decompiled by Procyon v0.5.30
// 

final class Card
{
    private int face;
    private char suit;
    private boolean taken;
    
    public Card() {
        this.taken = false;
    }
    
    public int getFace() {
        return this.face;
    }
    
    public void setFace(final int face) {
        this.face = face;
    }
    
    public char getSuit() {
        return this.suit;
    }
    
    public void setSuit(final char suit) {
        this.suit = suit;
    }
    
    public boolean getDealt() {
        return this.taken;
    }
    
    public void setDealt(final boolean taken) {
        this.taken = taken;
    }
}
