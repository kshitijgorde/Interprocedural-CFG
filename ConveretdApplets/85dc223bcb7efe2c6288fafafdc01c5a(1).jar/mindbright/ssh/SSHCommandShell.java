// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

public interface SSHCommandShell
{
    void setStdIO(final SSHStdIO p0);
    
    boolean doCommandShell();
    
    void launchCommandShell();
    
    boolean escapeSequenceTyped(final char p0);
    
    String escapeString();
}
