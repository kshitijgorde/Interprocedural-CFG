// 
// Decompiled by Procyon v0.5.30
// 

public final class aq extends at
{
    private boolean a;
    
    public aq() {
        this.a = false;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void b(final int n, final int n2) {
        if ((n & 0xE060) == 0x4020) {
            if ((n & 0x100) != 0x0) {
                this.a = ((n2 & 0x1) != 0x0);
                return;
            }
            this.e((n2 & 0x8) | (n2 & 0x1) << 2 | (n2 & 0x6) >> 1);
        }
    }
    
    public final int a() {
        return 50;
    }
    
    public final void a() {
        this.a = false;
        for (int i = 0; i < 8192; ++i) {
            super.a.c[i] = super.a.b[122880 + i];
        }
        this.a(8, 9, 0, 11);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
    
    public final int a(final int n) {
        if (this.a && n == 21) {
            return 3;
        }
        return 0;
    }
}
