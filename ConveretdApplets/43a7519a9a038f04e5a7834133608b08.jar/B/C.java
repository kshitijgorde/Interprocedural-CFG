// 
// Decompiled by Procyon v0.5.30
// 

package B;

public final class C
{
    private final float[] a;
    private final double b;
    
    public C() {
        this.a = new float[120000];
        this.b = this.a.length - 1;
        for (int length = this.a.length, i = 0; i < length; ++i) {
            this.a[i] = (float)Math.acos(i / this.b);
        }
    }
    
    public final float a(final double n) {
        if (n < -1.0 || n > 1.0) {
            return Float.NaN;
        }
        final int n2 = (int)(n * this.b);
        if (n < 0.0) {
            return 3.1415927f - this.a[-n2];
        }
        return this.a[n2];
    }
}
