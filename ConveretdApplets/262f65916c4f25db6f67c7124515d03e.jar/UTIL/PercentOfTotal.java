// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

public class PercentOfTotal
{
    public static int IDLE;
    public static int CPU;
    public static int AUDIO;
    public static int BLIT;
    public static int VIDEO;
    public static int AUDIOBUFFER;
    private long[] a;
    private double b;
    private double c;
    
    public PercentOfTotal() {
        this.c = 0.1;
        this.a = new long[PercentOfTotal.AUDIOBUFFER + 1];
    }
    
    public void updateComponentNanos(final int n, final long n2) {
        this.a[n] = (long)(this.a[n] * (1.0 - this.c) + n2 * this.c);
    }
    
    public double getPercentPerf(final int n) {
        if (n == PercentOfTotal.IDLE) {
            return this.b * 100.0;
        }
        return this.a[n] / this.getSum() * 100.0;
    }
    
    public long getSum() {
        long n = 0L;
        for (int i = 0; i < this.a.length; ++i) {
            n += this.a[i];
        }
        return n;
    }
    
    public void setLeftover(final double n) {
        this.b = this.b * (1.0 - this.c) + n * this.c;
    }
    
    static {
        PercentOfTotal.IDLE = -1;
        PercentOfTotal.AUDIOBUFFER = (PercentOfTotal.VIDEO = (PercentOfTotal.BLIT = (PercentOfTotal.AUDIO = (PercentOfTotal.CPU = 0) + 1) + 1) + 1) + 1;
    }
}
