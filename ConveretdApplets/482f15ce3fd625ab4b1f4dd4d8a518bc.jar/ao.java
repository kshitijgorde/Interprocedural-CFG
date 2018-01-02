// 
// Decompiled by Procyon v0.5.30
// 

public final class ao extends at
{
    public int a;
    public int b;
    
    public ao() {
        this.a = 0;
        this.b = 1;
    }
    
    public final int a() {
        return 51;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a = 0;
        this.b = 1;
        this.f();
        super.a.a.a.a = false;
    }
    
    public final void a(final int n, final int n2) {
        if (n > 24576 && n < 32768) {
            this.b = ((n2 & 0x10) >> 3 | (n2 & 0x2) >> 1);
            this.f();
            return;
        }
        if (n >= 32768) {
            this.a = (n2 & 0xF) << 2;
            if (49152 <= n && n <= 57343) {
                this.b = ((this.b & 0x1) | (n2 & 0x10) >> 3);
            }
            this.f();
        }
    }
    
    private final void f() {
        switch (this.b) {
            case 0: {
                this.b();
                this.b(this.a | 0x2C | 0x3);
                this.c(this.a | 0x0 | 0x0);
                this.d(this.a | 0x0 | 0x1);
                this.e(this.a | 0xC | 0x2);
                this.f(this.a | 0xC | 0x3);
            }
            case 1: {
                this.b();
                this.b(this.a | 0x20 | 0x3);
                this.c(this.a | 0x0 | 0x0);
                this.d(this.a | 0x0 | 0x1);
                this.e(this.a | 0x0 | 0x2);
                this.f(this.a | 0x0 | 0x3);
            }
            case 2: {
                this.b();
                this.b(this.a | 0x2E | 0x3);
                this.c(this.a | 0x2 | 0x0);
                this.d(this.a | 0x2 | 0x1);
                this.e(this.a | 0xE | 0x2);
                this.f(this.a | 0xE | 0x3);
            }
            case 3: {
                this.c();
                this.b(this.a | 0x20 | 0x3);
                this.c(this.a | 0x0 | 0x0);
                this.d(this.a | 0x0 | 0x1);
                this.e(this.a | 0x0 | 0x2);
                this.f(this.a | 0x0 | 0x3);
                break;
            }
        }
    }
}
