// 
// Decompiled by Procyon v0.5.30
// 

package gravity;

public interface ILauncher
{
    public static final int OFFLINE = -1;
    public static final int READY = 0;
    public static final int LAUNCHING = 1;
    public static final int STOPPED = 2;
    
    void setAngle(final double p0);
    
    void launch();
    
    void abort();
    
    void reset();
    
    void setReady();
    
    int getState();
    
    void shutDown();
}
