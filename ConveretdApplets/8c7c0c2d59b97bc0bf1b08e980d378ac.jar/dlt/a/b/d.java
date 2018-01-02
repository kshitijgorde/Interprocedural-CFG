// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.b;

public abstract class d
{
    private static double[] if;
    private static double[] a;
    
    private static double[] if() {
        final double[] array = new double[360];
        for (int i = 0; i < 360; ++i) {
            array[i] = Math.sin(Math.toRadians(i));
        }
        return array;
    }
    
    private static double[] a() {
        final double[] array = new double[360];
        for (int i = 0; i < 360; ++i) {
            array[i] = Math.cos(Math.toRadians(i));
        }
        return array;
    }
    
    public static double a(final int n) {
        return d.a[n % 360];
    }
    
    public static double if(final int n) {
        return d.if[n % 360];
    }
    
    static {
        d.if = if();
        d.a = a();
    }
}
