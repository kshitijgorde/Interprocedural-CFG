import java.awt.Event;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetTimer extends Panel implements Runnable
{
    private int Secounds;
    
    public IRCQNetTimer(final int secounds) {
        this.Secounds = 5;
        this.Secounds = secounds;
    }
    
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000 * this.Secounds);
                this.postEvent(new Event(this, 10021, new Integer(this.Secounds)));
            }
        }
        catch (InterruptedException ex) {}
    }
}
