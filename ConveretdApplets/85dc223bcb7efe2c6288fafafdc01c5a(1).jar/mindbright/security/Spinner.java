// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public class Spinner extends Thread
{
    private long t;
    
    public static int spin(final long t) {
        int counter = 0;
        final Thread s = new Spinner(t);
        s.start();
        do {
            ++counter;
            Thread.yield();
        } while (s.isAlive());
        return counter;
    }
    
    public static int bogusSpin() {
        final Runtime rt = Runtime.getRuntime();
        Thread.yield();
        rt.gc();
        int bogus = (int)System.currentTimeMillis();
        bogus = ((bogus & 0xFF) + ((bogus & 0xFF00) >>> 8) & 0xFF);
        return bogus;
    }
    
    private Spinner(final long t) {
        this.t = t;
    }
    
    public void run() {
        try {
            Thread.sleep(this.t);
        }
        catch (InterruptedException ex) {}
    }
    
    public static int guessTime(final int n) {
        int t;
        for (t = 5; spin(t) < n; t = t * 3 / 2) {}
        return t;
    }
}
