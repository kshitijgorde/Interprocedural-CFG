import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBAnimationTimer implements Runnable
{
    private int clockFreq;
    private Thread timer;
    private UBBComponent notifyComponent;
    private int pause;
    private Vector animatedComponents;
    private UBBComponent[] componentsToClock;
    public static final int RESUME = 0;
    public static final int PAUSE = 1;
    public static final int SYNC = 2;
    
    public void stop() {
        this.timer = null;
    }
    
    public void control(final int clockFreq) {
        if (clockFreq == 1) {
            this.pause = -2;
            return;
        }
        if (clockFreq == 2) {
            this.pause = -1;
            return;
        }
        if (clockFreq == 0) {
            this.pause = 0;
            return;
        }
        if (clockFreq < 33) {
            this.clockFreq = 33;
            return;
        }
        this.clockFreq = clockFreq;
    }
    
    public void addAnimatedComponent(final UBBComponent ubbComponent) {
        if (this.animatedComponents == null) {
            this.animatedComponents = new Vector(5);
        }
        this.animatedComponents.addElement(ubbComponent);
    }
    
    public UBBAnimationTimer(int clockFreq) {
        this.clockFreq = clockFreq;
        if (clockFreq < 33) {
            clockFreq = 33;
        }
    }
    
    public void destroy() {
        this.timer = null;
        this.notifyComponent = null;
        this.animatedComponents = null;
        this.componentsToClock = null;
    }
    
    public void start() {
        if (this.animatedComponents != null) {
            this.componentsToClock = new UBBComponent[this.animatedComponents.size()];
            this.animatedComponents.copyInto(this.componentsToClock);
            this.animatedComponents.removeAllElements();
            this.animatedComponents = null;
        }
        if (this.componentsToClock != null && this.notifyComponent != null && this.clockFreq > 0) {
            (this.timer = new Thread(this)).start();
        }
    }
    
    public void run() {
        final Thread timer = this.timer;
        long n = this.clockFreq;
        try {
            while (timer == this.timer) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {}
                if (this.pause <= 0) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    boolean b = false;
                    for (int i = 0; i < this.componentsToClock.length; ++i) {
                        try {
                            if (this.componentsToClock[i].clockEvent(this.pause < 0)) {
                                b = true;
                            }
                        }
                        catch (Exception ex2) {}
                    }
                    if (b) {
                        this.notifyComponent.animationUpdate();
                        this.notifyComponent.updateUBBListeners(false);
                    }
                    final long n2 = System.currentTimeMillis() - currentTimeMillis;
                    n = ((n2 >= this.clockFreq) ? 33L : (this.clockFreq - n2));
                    if (this.pause >= 0) {
                        continue;
                    }
                    if (this.pause == -2) {
                        this.pause = 1;
                    }
                    else {
                        this.pause = 0;
                    }
                }
                else {
                    n = this.clockFreq;
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    public void notify(final UBBComponent notifyComponent) {
        this.notifyComponent = notifyComponent;
    }
}
