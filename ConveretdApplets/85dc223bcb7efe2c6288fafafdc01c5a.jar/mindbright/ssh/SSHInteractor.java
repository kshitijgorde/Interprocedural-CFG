// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;

public interface SSHInteractor
{
    void startNewSession(final SSHClient p0);
    
    void sessionStarted(final SSHClient p0);
    
    void connected(final SSHClient p0);
    
    void open(final SSHClient p0);
    
    void disconnected(final SSHClient p0, final boolean p1);
    
    void report(final String p0);
    
    void alert(final String p0);
    
    void propsStateChanged(final SSHPropertyHandler p0);
    
    boolean askConfirmation(final String p0, final boolean p1);
    
    boolean quietPrompts();
    
    String promptLine(final String p0, final String p1) throws IOException;
    
    String promptPassword(final String p0) throws IOException;
    
    boolean isVerbose();
}
