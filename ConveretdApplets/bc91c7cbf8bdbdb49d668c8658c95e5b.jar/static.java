// 
// Decompiled by Procyon v0.5.30
// 

public class static
{
    public static final float b(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        final double n7 = Math.pow(n5, 4.0) * Math.pow(3.141592653589793, 2.0) * Math.pow(n6, 2.0) / (16.0 * Math.pow(n4, 2.0)) * 2.0 * (n - n2) / n3;
        if (n7 > 1.0) {
            return -1.0f;
        }
        return (float)(n5 * Math.pow(1.0 - n7, -0.25));
    }
    
    public static final float _(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return (float)Math.pow(Math.pow(3.141592653589793, 2.0) * Math.pow(n6, 2.0) / (16.0 * Math.pow(n4, 2.0)) * 2.0 * (n - n2) / n3 + 1.0 / Math.pow(n5, 4.0), -0.25);
    }
}
