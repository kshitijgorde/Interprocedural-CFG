// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

public class SlowInSlowOutPacer implements Pacer
{
    public double pace(final double n) {
        return (n == 0.0 || n == 1.0) ? n : this.sigmoid(n);
    }
    
    private double sigmoid(double n) {
        n = 12.0 * n - 6.0;
        return 1.0 / (1.0 + Math.exp(-1.0 * n));
    }
}
