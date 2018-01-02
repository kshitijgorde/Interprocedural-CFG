// 
// Decompiled by Procyon v0.5.30
// 

public class Timer extends Thread
{
    private Speelveld mySpeelveld;
    private long interval;
    private boolean enabled;
    
    public Timer(final Speelveld mySpeelveld) {
        this.setPriority(1);
        this.mySpeelveld = mySpeelveld;
        this.enabled = true;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setInterval(final long interval) {
        this.interval = interval;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.interval);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.enabled) {
                this.mySpeelveld.timerTimer();
            }
        }
    }
}
