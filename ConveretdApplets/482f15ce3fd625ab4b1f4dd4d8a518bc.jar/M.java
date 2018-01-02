// 
// Decompiled by Procyon v0.5.30
// 

public final class M extends at
{
    public int a;
    public int b;
    
    public M() {
        this.a = 0;
        this.b = 0;
    }
    
    public final int a() {
        return 236;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        if (32768 <= n && n <= 49151) {
            this.a = ((n & 0x3) << 4 | (this.a & 0x7));
        }
        else {
            this.a = ((n & 0x7) | (this.a & 0x30));
            this.b = (n & 0x30);
        }
        if ((n & 0x20) != 0x0) {
            this.c();
        }
        else {
            this.b();
        }
        switch (this.b) {
            case 0: {
                this.a |= 0x8;
                this.c(this.a * 2 + 0);
                this.d(this.a * 2 + 1);
                this.e((this.a | 0x7) * 2 + 0);
                this.f((this.a | 0x7) * 2 + 1);
            }
            case 16: {
                this.a &= 0x37;
                this.c(this.a * 2 + 0);
                this.d(this.a * 2 + 1);
                this.e((this.a | 0x7) * 2 + 0);
                this.f((this.a | 0x7) * 2 + 1);
            }
            case 32: {
                this.a |= 0x8;
                this.c((this.a & 0xFE) * 2 + 0);
                this.d((this.a & 0xFE) * 2 + 1);
                this.e((this.a & 0xFE) * 2 + 2);
                this.f((this.a & 0xFE) * 2 + 3);
            }
            case 48: {
                this.a |= 0x8;
                this.c(this.a * 2 + 0);
                this.d(this.a * 2 + 1);
                this.e(this.a * 2 + 0);
                this.f(this.a * 2 + 1);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a = 0;
        this.b = 0;
    }
}
