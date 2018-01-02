// 
// Decompiled by Procyon v0.5.30
// 

public class cb extends Thread
{
    public long mp;
    
    public static int mv(final long n) {
        int n2 = 0;
        final cb cb = new cb(n);
        cb.start();
        do {
            ++n2;
            Thread.yield();
        } while (cb.isAlive());
        return n2;
    }
    
    public static int mu() {
        final Runtime runtime = Runtime.getRuntime();
        Thread.yield();
        runtime.gc();
        final int n = (int)System.currentTimeMillis();
        return (n & 0xFF) + ((n & 0xFF00) >>> 8) & 0xFF;
    }
    
    private cb(final long mp) {
        this.mp = mp;
    }
    
    public final void run() {
        try {
            Thread.sleep(this.mp);
        }
        catch (InterruptedException ex) {}
    }
    
    public static int mt(final int n) {
        int n2;
        for (n2 = 5; mv(n2) < n; n2 = n2 * 3 / 2) {}
        return n2;
    }
}
