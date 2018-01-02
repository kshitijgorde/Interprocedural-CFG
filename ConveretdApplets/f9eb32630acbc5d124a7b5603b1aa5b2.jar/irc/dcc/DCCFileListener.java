// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc;

public interface DCCFileListener
{
    void transmitted(final Integer p0, final DCCFile p1);
    
    void finished(final DCCFile p0);
    
    void failed(final DCCFile p0);
}
