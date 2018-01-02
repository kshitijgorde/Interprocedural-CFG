// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.security.Cipher;
import mindbright.terminal.Terminal;

public interface SSHConsole
{
    Terminal getTerminal();
    
    void stdoutWriteString(final byte[] p0);
    
    void stderrWriteString(final byte[] p0);
    
    void print(final String p0);
    
    void println(final String p0);
    
    void serverConnect(final SSHChannelController p0, final Cipher p1);
    
    void serverDisconnect(final String p0);
}
