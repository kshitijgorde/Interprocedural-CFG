// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class a7
{
    private String for;
    private Thread a;
    private Throwable do;
    private int if;
    
    public a7(final String for1) {
        this.for = for1;
    }
    
    public synchronized boolean if() {
        return this.if > 0;
    }
    
    public synchronized boolean do() {
        final long currentTimeMillis = System.currentTimeMillis();
        while (!this.for()) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {}
            if (System.currentTimeMillis() - currentTimeMillis > 2000L) {
                try {
                    throw new RuntimeException(Thread.currentThread().toString() + " try to lock " + this);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        return true;
    }
    
    public synchronized boolean a() {
        final long currentTimeMillis = System.currentTimeMillis();
        while (!this.int()) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {}
            if (System.currentTimeMillis() - currentTimeMillis > 2000L) {
                try {
                    throw new RuntimeException(Thread.currentThread().toString() + " try to unlock " + this);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        return true;
    }
    
    protected synchronized boolean int() {
        final Thread currentThread = Thread.currentThread();
        if (this.if == 0) {
            System.out.println("********");
            new Exception("Unlock").printStackTrace();
            System.out.println("Lock Trace");
            this.do.printStackTrace();
        }
        if (this.if > 0 && this.a == currentThread) {
            --this.if;
            if (this.if == 0) {
                this.a = null;
                this.do = null;
                this.notifyAll();
            }
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "Lock[" + this.for + " " + (this.if() ? ("locked by " + this.a.getName()) : "free") + "] " + this.if;
    }
    
    protected synchronized boolean for() {
        final Thread currentThread = Thread.currentThread();
        currentThread.getName();
        if (this.if == 0 || this.a == currentThread) {
            this.a = currentThread;
            if (this.if == 0) {
                try {
                    throw new RuntimeException("Lock owner stack");
                }
                catch (Throwable do1) {
                    this.do = do1;
                }
            }
            ++this.if;
            return true;
        }
        return false;
    }
}
