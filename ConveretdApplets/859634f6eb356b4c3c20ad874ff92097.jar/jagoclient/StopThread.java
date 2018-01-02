// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

public class StopThread extends Thread
{
    boolean Stopped;
    
    public boolean stopped() {
        return this.Stopped;
    }
    
    public void stopit() {
        this.Stopped = true;
    }
    
    public StopThread() {
        this.Stopped = false;
    }
}
