import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class u extends t implements Serializable
{
    public i p;
    public i d;
    public g a;
    public i n;
    boolean v;
    
    public u() {
        this.p = new i("src", this, 1, false);
        this.d = new i("frame", this, 1, false);
        this.a = new g("mask", this, 1, false);
        this.n = new i("dst", this, 3, false);
        this.v = false;
    }
    
    public final void p(final boolean v) {
        this.v = v;
    }
    
    public final boolean execute() {
        if (this.v) {
            this.p(this.p.a, this.d.a, this.a.a, this.n.a);
        }
        else {
            this.d(this.p.a, this.d.a, this.a.a, this.n.a);
        }
        return true;
    }
    
    public final void p(final int[] array, final int[] array2, final byte[] array3, final int[] array4) {
        for (int i = 0; i < array.length; ++i) {
            final int n = array3[i] & 0xFF;
            final int n2 = 255 - n;
            if (n == 0) {
                array4[i] = array2[i];
            }
            else if (n == 255) {
                array4[i] = array[i];
            }
            else {
                array4[i] = ((n2 * (array2[i] & 0xFF0000) + n * (array[i] & 0xFF0000) & 0xFF000000) | (n2 * (array2[i] & 0xFF00) + n * (array[i] & 0xFF00) & 0xFF0000) | (n2 * (array2[i] & 0xFF) + n * (array[i] & 0xFF) & 0xFF00)) >> 8;
            }
        }
    }
    
    public final void d(final int[] array, final int[] array2, final byte[] array3, final int[] array4) {
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF0000;
            final int n2 = array[i] & 0xFF00;
            final int n3 = array[i] & 0xFF;
            final int n4 = array2[i] & 0xFF0000;
            final int n5 = array2[i] & 0xFF00;
            final int n6 = array2[i] & 0xFF;
            final int n7 = array3[i] & 0xFF;
            final int n8 = 255 - n7;
            array4[i] = ((n8 * n4 + n7 * n & 0xFF000000) | (n8 * n5 + n7 * n2 & 0xFF0000) | (n8 * n6 + n7 * n3 & 0xFF00)) >> 8;
        }
    }
}
