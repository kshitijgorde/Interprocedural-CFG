// 
// Decompiled by Procyon v0.5.30
// 

public final class az extends at
{
    private int a;
    
    public az() {
        this.a = 0;
    }
    
    public final int a() {
        return 122;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
    }
    
    public final void a(final long n) {
        if (n == -133648544L) {
            this.a = 1;
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 32768) {
            if (this.a == 1) {
                final int n3 = (n2 & 0x20) >> 2;
                final int n4 = (n2 & 0x2) << 2 | (n2 & 0x4);
                this.a(n4, n4 + 1, n4 + 2, n4 + 3, n3, n3 + 1, n3 + 2, n3 + 3);
                return;
            }
            if (n == 24576) {
                final int n5 = n2 & 0x7;
                final int n6 = (n2 & 0x70) >> 4;
                this.g(n5 * 4 + 0);
                this.g(n5 * 4 + 1);
                this.g(n5 * 4 + 2);
                this.g(n5 * 4 + 3);
                this.g(n6 * 4 + 0);
                this.g(n6 * 4 + 1);
                this.g(n6 * 4 + 2);
                this.g(n6 * 4 + 3);
            }
        }
    }
}
