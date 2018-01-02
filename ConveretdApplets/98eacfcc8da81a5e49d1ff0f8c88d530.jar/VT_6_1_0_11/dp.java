// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class dp
{
    private dg a;
    private cH b;
    private float[] c;
    private int d;
    private int e;
    private int f;
    
    public dp() {
        this.b = new cH();
    }
    
    public final boolean a(final int n, final int n2, final int d, final int e) {
        switch (n) {
            case 0: {
                this.a = new cA();
                ((cA)this.a).g();
                break;
            }
            case 1: {
                this.a = new di();
                ((di)this.a).a_();
                break;
            }
            case 2: {
                this.a = new di();
                ((di)this.a).b_();
                break;
            }
            default: {
                return false;
            }
        }
        this.a.a(n2);
        this.f = this.a.c();
        this.d = d;
        this.e = e;
        this.c = new float[e * this.f];
        this.b.a();
        return true;
    }
    
    public final dg a() {
        return this.a;
    }
    
    public final int b() {
        return this.d;
    }
    
    public final int c() {
        return this.e;
    }
    
    public final int d() {
        return this.f;
    }
    
    public final int a(final byte[] array, final int n) {
        final int d = this.b.d();
        System.arraycopy(this.b.c(), 0, array, n, d);
        this.b.a();
        return d;
    }
    
    public final int e() {
        return this.b.d();
    }
    
    public final boolean a(byte[] array, int n, final int n2) {
        final byte[] array2 = array;
        final int n3 = n;
        final float[] c = this.c;
        final int n4 = n2 / 2;
        final float[] array3 = c;
        n = n3;
        array = array2;
        if (array2.length - n < n4 * 2) {
            throw new IllegalArgumentException("Insufficient Samples to convert to floats");
        }
        if (array3.length < n4) {
            throw new IllegalArgumentException("Insufficient float buffer to convert the samples");
        }
        for (int i = 0; i < n4; ++i) {
            array3[i + 0] = ((array[n + i * 2] & 0xFF) | array[n + i * 2 + 1] << 8);
        }
        final float[] c2 = this.c;
        final int n5 = n2 / 2;
        final float[] array4 = c2;
        final int n6 = this.e * this.f;
        if (n5 != n6) {
            throw new IllegalArgumentException("SpeexEncoder requires " + n6 + " samples to process a Frame, not " + n5);
        }
        if (this.e == 2) {
            N.a(this.b, array4, this.f);
        }
        this.a.a(this.b, array4);
        return true;
    }
}
