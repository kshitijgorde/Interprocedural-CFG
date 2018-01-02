// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

public class SSHSCPStdoutIndicator implements SSHSCPIndicator
{
    public void connected(final String server) {
        System.out.println("Connected to " + server + "...");
    }
    
    public void startFile(final String file, final int size) {
        System.out.print("Transfering " + file + " (" + size + " bytes)...");
    }
    
    public void startDir(final String file) {
        System.out.println("Entering directory " + file);
    }
    
    public void endFile() {
        System.out.println("done");
    }
    
    public void endDir() {
    }
    
    public void progress(final int size) {
    }
}
