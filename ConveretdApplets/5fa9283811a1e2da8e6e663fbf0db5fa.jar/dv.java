// 
// Decompiled by Procyon v0.5.30
// 

public class dv
{
    public int p;
    public int d;
    public int[] p;
    
    public final void p(final int p) {
        this.p = p;
        this.d = p - 1;
        for (int i = 0; i < this.p; ++i) {
            this.p[i] = i;
        }
    }
    
    public final boolean p() {
        return this.d <= 0;
    }
    
    public final void p(final int n, final int n2) {
        final int n3 = this.p[n];
        this.p[n] = this.p[n2];
        this.p[n2] = n3;
    }
    
    public final void d(final int n) {
        if (n != this.d) {
            this.p(n, this.d);
        }
        --this.d;
    }
    
    public dv(final int n) {
        this.p = new int[200];
        this.p(n);
    }
}
