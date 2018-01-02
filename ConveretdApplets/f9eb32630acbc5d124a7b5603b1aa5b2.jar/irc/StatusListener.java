// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface StatusListener extends SourceListener
{
    void nickChanged(final String p0, final Status p1);
    
    void modeChanged(final String p0, final Status p1);
    
    void invited(final String p0, final String p1, final Status p2);
}
