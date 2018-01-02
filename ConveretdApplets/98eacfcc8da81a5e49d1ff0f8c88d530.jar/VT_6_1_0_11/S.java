// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class S extends cO
{
    private String[] a;
    private int b;
    
    S(final cs cs) {
        super(cs);
        this.b = 256;
    }
    
    public final String a(final int n) {
        if (n < this.b) {
            if (this.a == null) {
                this.a = new String[this.b];
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = super.a(i);
                }
            }
            return this.a[n];
        }
        return super.a(n);
    }
}
