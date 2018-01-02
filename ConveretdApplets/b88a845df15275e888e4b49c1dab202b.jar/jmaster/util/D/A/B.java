// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.io.Serializable;

public class B implements Q, Cloneable, Serializable
{
    static final long C = -7990431442314209043L;
    protected int[] B;
    
    public B() {
        this.B = new int[256];
    }
    
    public B(final int[] b) {
        this.B = b;
    }
    
    public Object clone() {
        try {
            final B b = (B)super.clone();
            b.B = this.B.clone();
            return b;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void A(final int[] b) {
        this.B = b;
    }
    
    public int[] A() {
        return this.B;
    }
    
    public int A(final float n) {
        int n2 = (int)(n * 255.0f);
        if (n2 < 0) {
            n2 = 0;
        }
        else if (n2 > 255) {
            n2 = 255;
        }
        return this.B[n2];
    }
    
    public void B(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.B[n2];
        final int n6 = this.B[n3];
        for (int i = n2; i <= n; ++i) {
            this.B[i] = I.A((i - n2) / (n - n2), n5, n4);
        }
        for (int j = n; j < n3; ++j) {
            this.B[j] = I.A((j - n) / (n3 - n), n4, n6);
        }
    }
    
    public void A(final int n, final int n2, final int n3, final int n4) {
        for (int i = n; i <= n2; ++i) {
            this.B[i] = I.A((i - n) / (n2 - n), n3, n4);
        }
    }
    
    public void A(final int n, final int n2, final int n3) {
        for (int i = n; i <= n2; ++i) {
            this.B[i] = n3;
        }
    }
    
    public void A(final int n, final int n2) {
        this.B[n] = n2;
    }
}
