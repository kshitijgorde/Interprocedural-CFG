// 
// Decompiled by Procyon v0.5.30
// 

package B;

public final class I
{
    private final float[] a;
    private final double b;
    private final double c;
    
    public I() {
        this.a = new float[50000];
        this.b = this.a.length - 1;
        this.c = this.b / 1.5707963267948966;
        for (int length = this.a.length, i = 0; i < length; ++i) {
            this.a[i] = (float)Math.sin(i / this.c);
        }
    }
    
    public final float a(double n) {
        if (n > 1.5707963267948966) {
            n = 3.141592653589793 - n;
        }
        return this.a[(int)(n * this.c)];
    }
}
