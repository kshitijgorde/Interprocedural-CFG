// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public final class f
{
    private l[] a;
    private int b;
    private int c;
    
    public f(final int n, int i) {
        this.a = null;
        this.b = 1;
        this.c = 0;
        while (this.b < i) {
            this.b <<= 1;
            ++this.c;
        }
        this.a = new l[n];
        for (i = 0; i < n; ++i) {
            this.a[i] = new l(this.b);
        }
    }
    
    public final l a(final int n) {
        if (n < this.a.length) {
            return this.a[n];
        }
        return null;
    }
    
    public final void a() {
        this = this;
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].a(0);
        }
    }
    
    public final void a(int n, final char c) {
        if (n < 0 || n >= this.b * this.a.length) {
            return;
        }
        final int n2 = n >> this.c;
        n &= this.b - 1;
        if (n2 < this.a.length) {
            this.a[n2].a(n, c);
        }
    }
}
