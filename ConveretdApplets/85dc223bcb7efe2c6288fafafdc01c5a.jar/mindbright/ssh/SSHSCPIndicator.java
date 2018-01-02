// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

public interface SSHSCPIndicator
{
    void connected(final String p0);
    
    void startFile(final String p0, final int p1);
    
    void startDir(final String p0);
    
    void endFile();
    
    void endDir();
    
    void progress(final int p0);
}
