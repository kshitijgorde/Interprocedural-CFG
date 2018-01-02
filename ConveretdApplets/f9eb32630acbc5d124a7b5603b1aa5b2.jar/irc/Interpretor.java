// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface Interpretor
{
    void sendString(final Source p0, final String p1);
    
    void setNextInterpretor(final Interpretor p0);
    
    Interpretor getNextInterpretor();
    
    boolean isInside(final Interpretor p0);
    
    void addLast(final Interpretor p0);
}
