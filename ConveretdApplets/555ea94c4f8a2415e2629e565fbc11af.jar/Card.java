// 
// Decompiled by Procyon v0.5.30
// 

public class Card
{
    private static final int JOKER = 53;
    public static final int ACE = 1;
    public static final int DEUCE = 2;
    public static final int TREY = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int HIGHACE = 14;
    public static final int JOKERRANK = 1;
    public static final int BJ_ACE = 11;
    public static final int CLUB = 1;
    public static final int DIAMOND = 2;
    public static final int HEART = 3;
    public static final int SPADE = 4;
    public static final int JOKERSUIT = 5;
    private int cardNum;
    
    public Card() {
        this.cardNum = 1;
    }
    
    public Card(final int cardNum) {
        this.cardNum = cardNum;
    }
    
    public Card(final int n, final int n2) {
        this.setCard(n, n2);
    }
    
    public void setCard(final int n, final int n2) {
        this.cardNum = (n2 - 1) * 13 + n;
    }
    
    public int getRank() {
        return (this.cardNum - 1) % 13 + 1;
    }
    
    public int getRankAcesHigh() {
        if (this.getRank() == 1) {
            return 14;
        }
        return this.getRank();
    }
    
    public int getSuit() {
        return (this.cardNum - 1) / 13 + 1;
    }
    
    public boolean equals(final Card card) {
        return this.getRank() == card.getRank() && this.getSuit() == card.getSuit();
    }
    
    public boolean isJoker() {
        return this.cardNum == 53;
    }
    
    public static Card createJoker() {
        return new Card(53);
    }
    
    public int getBJValue() {
        int rank = this.getRank();
        if (rank > 10) {
            rank = 10;
        }
        if (this.getRank() == 1) {
            rank = 11;
        }
        return rank;
    }
}
