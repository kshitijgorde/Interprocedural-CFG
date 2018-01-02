// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface QueryListener extends SourceListener
{
    void nickChanged(final String p0, final Query p1);
    
    void whoisChanged(final String p0, final Query p1);
}
