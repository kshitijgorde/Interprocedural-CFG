// 
// Decompiled by Procyon v0.5.30
// 

package courseware.util;

public class MemCleaner
{
    private static Runtime runt;
    private static long lastused;
    private static long used;
    private static long delta;
    private static long NAPTIME;
    private static boolean verbose;
    
    public static void nap() {
        try {
            Thread.currentThread();
            Thread.sleep(MemCleaner.NAPTIME);
        }
        catch (InterruptedException ex) {}
    }
    
    public static void nap(final long n) {
        try {
            Thread.currentThread();
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public static synchronized void cleanup() {
        if (MemCleaner.verbose) {
            MemCleaner.used = MemCleaner.runt.totalMemory() - MemCleaner.runt.freeMemory();
            MemCleaner.delta = MemCleaner.used - MemCleaner.lastused;
            System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("MemCleaner begin: used=").concat(String.valueOf(MemCleaner.used))).concat(String.valueOf(", delta="))).concat(String.valueOf(MemCleaner.delta))).concat(String.valueOf(", total="))).concat(String.valueOf(MemCleaner.runt.totalMemory())));
        }
        nap();
        MemCleaner.runt.gc();
        MemCleaner.runt.gc();
        MemCleaner.runt.gc();
        if (MemCleaner.verbose) {
            MemCleaner.used = MemCleaner.runt.totalMemory() - MemCleaner.runt.freeMemory();
            MemCleaner.delta = MemCleaner.used - MemCleaner.lastused;
            MemCleaner.lastused = MemCleaner.used;
            System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("MemCleaner ends : used=").concat(String.valueOf(MemCleaner.used))).concat(String.valueOf(", netdelta="))).concat(String.valueOf(MemCleaner.delta))).concat(String.valueOf(", total="))).concat(String.valueOf(MemCleaner.runt.totalMemory()))).concat(String.valueOf("\n")));
        }
    }
    
    public static synchronized void report() {
        MemCleaner.used = MemCleaner.runt.totalMemory() - MemCleaner.runt.freeMemory();
        MemCleaner.delta = MemCleaner.used - MemCleaner.lastused;
        System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("MemCleaner report: used=").concat(String.valueOf(MemCleaner.used))).concat(String.valueOf(", delta="))).concat(String.valueOf(MemCleaner.delta))).concat(String.valueOf(", total="))).concat(String.valueOf(MemCleaner.runt.totalMemory())));
    }
    
    public static synchronized void printStackTrace(final String s) {
        final Throwable t = new Throwable(s);
        t.fillInStackTrace();
        t.printStackTrace();
    }
    
    public static void setNapTime(final long naptime) {
        MemCleaner.NAPTIME = naptime;
    }
    
    public static long getNapTime() {
        return MemCleaner.NAPTIME;
    }
    
    public static void setVerbose(final boolean verbose) {
        MemCleaner.verbose = verbose;
    }
    
    static {
        MemCleaner.runt = Runtime.getRuntime();
        MemCleaner.NAPTIME = 10L;
        MemCleaner.verbose = false;
    }
}
