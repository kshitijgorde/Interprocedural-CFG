// 
// Decompiled by Procyon v0.5.30
// 

public interface VideoPokerGame
{
    String getName();
    
    int getNumJokers();
    
    int getNumPayoffLines();
    
    String getDescription(final int p0);
    
    int getPayoff(final int p0, final int p1);
    
    boolean isWild(final Card p0);
    
    int getPayline(final Card[] p0);
}
