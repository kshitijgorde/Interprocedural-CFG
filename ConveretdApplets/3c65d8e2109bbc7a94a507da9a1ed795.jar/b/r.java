// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class r
{
    float[] a;
    
    public r() {
        this.a = new float[4];
    }
    
    void a() {
        for (int i = 0; i < 4; ++i) {
            this.a[i] = 1.0f;
        }
    }
    
    int a(final int n, final int n2) {
        final int n3 = (n2 > 0) ? (n + 1) : n;
        int n4 = n3 - 40 - 10;
        if (n4 < 0) {
            n4 = 0;
        }
        final int n5 = (int)(n4 * 0.025f);
        final int n6 = (int)((n3 + 10 - 2) * 0.025f);
        float n7 = -1.0f;
        int n8 = 0;
        for (int i = n6; i >= n5; --i) {
            if (this.a[i] > n7) {
                n7 = this.a[i];
            }
        }
        if (n7 > 60000.0f) {
            n8 = 1;
        }
        return n8;
    }
    
    void a(final float n, final int n2) {
        float n3 = -1.0f;
        final int n4 = n2 - 40;
        if (n4 < 0) {
            final float n5 = 1.0f + n * this.a[0];
            if (n5 > n3) {
                n3 = n5;
            }
            final float n6 = 1.0f + n * n5;
            if (n6 > n3) {
                n3 = n6;
            }
        }
        else {
            final int n7 = (int)(n4 * 0.025f);
            for (int n8 = (int)((n2 - 1) * 0.025f), i = n7; i <= n8; ++i) {
                final float n9 = 1.0f + n * this.a[i];
                if (n9 > n3) {
                    n3 = n9;
                }
            }
        }
        for (int j = 3; j >= 1; --j) {
            this.a[j] = this.a[j - 1];
        }
        this.a[0] = n3;
    }
}
