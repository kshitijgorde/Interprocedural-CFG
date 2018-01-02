// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class y
{
    float[][] for;
    float[] do;
    static int if;
    static float[] a;
    
    public y() {
        this.for = new float[4][10];
        this.do = new float[] { 0.285599f, 0.571199f, 0.856798f, 1.142397f, 1.427997f, 1.713596f, 1.999195f, 2.284795f, 2.570394f, 2.855993f };
    }
    
    void a() {
        for (int i = 0; i < 4; ++i) {
            j.a(this.do, this.for[i], 10);
        }
        y.if = 0;
        j.a(this.do, y.a, 10);
    }
    
    public void if(final int[] array, final int n, final float[] array2, final int n2) {
        final float[] array3 = new float[10];
        if (n2 == 0) {
            final int if1 = array[n + 0] >> 7 & 0x1;
            n.a(c.l, c.h, array[n + 0] & 0x7F, array[n + 1] >> 5 & 0x1F, array[n + 1] & 0x1F, c.else[if1], this.for, array2, c.new[if1]);
            j.a(array2, y.a, 10);
            y.if = if1;
        }
        else {
            j.a(y.a, array2, 10);
            n.a(y.a, array3, c.else[y.if], this.for, c.long[y.if]);
            n.a(array3, this.for);
        }
    }
    
    public void a(final int[] array, final int n, final float[] array2, final int n2) {
        this.if(array, n, array2, n2);
        for (int i = 0; i < 10; ++i) {
            array2[i] = (float)Math.cos(array2[i]);
        }
    }
    
    static {
        y.a = new float[10];
    }
}
