import java.awt.Event;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class Timer implements Runnable
{
    Component target;
    int eventType;
    boolean repeat;
    boolean repeating;
    boolean execute;
    Thread thread;
    int delay;
    
    public Timer(final Component t) {
        this(t, 1000);
    }
    
    public Timer(final Component t, final int d) {
        this(t, d, false);
    }
    
    public Timer(final Component t, final int d, final boolean r) {
        this(t, d, r, 1001);
    }
    
    public Timer(final Component t, final int d, final boolean r, final int e) {
        this.target = t;
        this.delay = d;
        this.repeat = r;
        this.execute = false;
        this.thread = new Thread(this);
        this.eventType = e;
        this.thread.start();
    }
    
    public void setEventType(final int type) {
        this.eventType = type;
    }
    
    public int getEventType() {
        return this.eventType;
    }
    
    public void setTarget(final Component t) {
        this.target = t;
    }
    
    public Component getTarget() {
        return this.target;
    }
    
    public void setDelay(final int d) {
        this.delay = d;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public void start() {
        this.execute = true;
        this.thread.resume();
    }
    
    public void setRepeat(final boolean f) {
        this.repeat = f;
    }
    
    public boolean getRepeat() {
        return this.repeat;
    }
    
    public void start(final int d) {
        this.delay = d;
        this.start();
    }
    
    public void start(final boolean r) {
        this.repeat = r;
        this.start();
    }
    
    public void start(final int d, final boolean r) {
        this.delay = d;
        this.repeat = r;
        this.start();
    }
    
    public void stop() {
        this.execute = false;
        this.repeating = false;
    }
    
    public void run() {
        Label_0014: {
            if (this.execute) {
                break Label_0014;
            }
            this.thread.suspend();
            try {
                while (true) {
                    this.repeating = this.repeat;
                    do {
                        Thread.sleep(this.delay);
                        if (this.execute) {
                            this.target.handleEvent(new Event(this, this.eventType, null));
                        }
                    } while (this.repeating);
                    this.thread.suspend();
                }
            }
            catch (InterruptedException ex) {}
        }
    }
}
