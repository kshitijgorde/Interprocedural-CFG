// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.io.IOException;

public interface TerminalListener
{
    void typedChar(final char p0) throws IOException;
    
    void sendBytes(final byte[] p0) throws IOException;
    
    void signalWindowChanged(final int p0, final int p1, final int p2, final int p3);
}
