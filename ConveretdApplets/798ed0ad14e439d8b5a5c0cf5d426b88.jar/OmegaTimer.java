import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmegaTimer extends Thread implements ActionListener
{
    int currentThread;
    Object err;
    ActionListener notify;
    Object println;
    String sleep;
    boolean start;
    boolean stopTimer;
    static int test;
    
    public OmegaTimer(final int currentThread, final ActionListener notify, final Object println, final String sleep) {
        this.err = new Object();
        this.currentThread = currentThread;
        this.notify = notify;
        this.println = println;
        this.sleep = sleep;
    }
    
    public final void start() {
        synchronized (this.err) {
            super.start();
            try {
                this.err.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void run() {
        try {
            synchronized (this.err) {
                this.err.notify();
                this.err.wait(this.currentThread);
                if (!this.start) {
                    if (this.notify != null) {
                        this.notify.actionPerformed(new ActionEvent(this.println, 1001, this.sleep));
                    }
                    this.stopTimer = true;
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public final boolean stopTimer() {
        synchronized (this.err) {
            this.start = true;
            this.err.notify();
            return this.stopTimer;
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        ++OmegaTimer.test;
    }
    
    public static final void main(final String[] array) {
        new OmegaTimer(0, null, null, null).test();
    }
    
    public final void test() {
        int n = 0;
        while (true) {
            synchronized (this) {
                ++n;
                final OmegaTimer omegaTimer = new OmegaTimer(1000, this, this, null);
                omegaTimer.start();
                if (!omegaTimer.stopTimer()) {
                    this.actionPerformed(null);
                }
                if (n != OmegaTimer.test) {
                    System.err.println("error1");
                }
                ++n;
                final OmegaTimer omegaTimer2 = new OmegaTimer(1, this, this, null);
                omegaTimer2.start();
                try {
                    Thread.currentThread();
                    Thread.sleep(2L);
                }
                catch (InterruptedException ex) {}
                if (!omegaTimer2.stopTimer()) {
                    this.actionPerformed(null);
                }
                if (n != OmegaTimer.test) {
                    System.err.println("error2");
                }
                if (n % 1000 != 0) {
                    continue;
                }
                System.err.println(n);
            }
        }
    }
    
    static {
        OmegaTimer.test = 0;
    }
}
