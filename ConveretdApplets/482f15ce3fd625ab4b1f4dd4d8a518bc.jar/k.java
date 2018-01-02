// 
// Decompiled by Procyon v0.5.30
// 

public final class k extends at
{
    public final int a() {
        return 227;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = (n & 0x100) >> 4 | (n & 0x78) >> 3;
        if ((n & 0x1) != 0x0) {
            this.c(n3 * 4 + 0);
            this.d(n3 * 4 + 1);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
        }
        else if ((n & 0x4) != 0x0) {
            this.c(n3 * 4 + 2);
            this.d(n3 * 4 + 3);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
        }
        else {
            this.c(n3 * 4 + 0);
            this.d(n3 * 4 + 1);
            this.e(n3 * 4 + 0);
            this.f(n3 * 4 + 1);
        }
        if ((n & 0x80) == 0x0) {
            if ((n & 0x200) != 0x0) {
                this.e((n3 & 0x1C) * 4 + 14);
                this.f((n3 & 0x1C) * 4 + 15);
            }
            else {
                this.e((n3 & 0x1C) * 4 + 0);
                this.f((n3 & 0x1C) * 4 + 1);
            }
        }
        if ((n & 0x2) != 0x0) {
            this.c();
            return;
        }
        this.b();
    }
    
    public final void a() {
        this.a(0, 1, 0, 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
    }
}
