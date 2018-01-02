// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

public class ThereAndBackPacer implements Pacer
{
    public double pace(final double n) {
        return 2.0 * ((n <= 0.5) ? n : (1.0 - n));
    }
}
