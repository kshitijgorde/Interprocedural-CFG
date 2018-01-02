// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;

public class SSHInteractorAdapter implements SSHInteractor
{
    public void startNewSession(final SSHClient client) {
    }
    
    public void sessionStarted(final SSHClient client) {
    }
    
    public void connected(final SSHClient client) {
    }
    
    public void open(final SSHClient client) {
    }
    
    public void disconnected(final SSHClient client, final boolean graceful) {
    }
    
    public void report(final String msg) {
    }
    
    public void alert(final String msg) {
    }
    
    public void propsStateChanged(final SSHPropertyHandler props) {
    }
    
    public boolean askConfirmation(final String message, final boolean defAnswer) {
        return defAnswer;
    }
    
    public boolean quietPrompts() {
        return true;
    }
    
    public String promptLine(final String prompt, final String defaultVal) throws IOException {
        return null;
    }
    
    public String promptPassword(final String prompt) throws IOException {
        return null;
    }
    
    public boolean isVerbose() {
        return false;
    }
}
