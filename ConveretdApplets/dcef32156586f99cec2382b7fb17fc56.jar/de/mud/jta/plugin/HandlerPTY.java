// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

public class HandlerPTY
{
    private int fd;
    boolean good;
    
    public HandlerPTY() {
        this.good = false;
    }
    
    public native int start(final String p0);
    
    public native void close();
    
    public native int read(final byte[] p0);
    
    public native int write(final byte[] p0);
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.good) {
            this.close();
        }
    }
    
    static {
        System.loadLibrary("jtapty");
    }
}
