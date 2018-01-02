// 
// Decompiled by Procyon v0.5.30
// 

public final class Y extends ag
{
    private int n;
    private int o;
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        switch ((n >> 12) - 8) {
            case 0: {
                this.f(n2, 0);
            }
            case 1: {
                this.f(n2, 2048);
            }
            case 2: {
                this.f(n2, 4096);
            }
            case 3: {
                this.f(n2, 6144);
            }
            case 4: {
                this.i();
            }
            case 5: {
                this.i();
            }
            case 6: {
                this.n = (n2 >> 4 & 0x1);
                this.o = (n2 & 0x3);
                this.i();
            }
            case 7: {
                this.a(n2, 32768);
                break;
            }
        }
    }
    
    private void i() {
        if (this.n == 0) {
            switch (this.o) {
                case 0: {
                    this.e.a(1);
                }
                case 1: {
                    this.e.a(0);
                }
                case 2: {
                    this.e.a(3);
                }
                case 3: {
                    this.e.a(4);
                    break;
                }
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int b = a.b();
        this.a(0, 32768);
        this.a(b - 1, 49152);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
        final boolean b = false;
        this.o = (b ? 1 : 0);
        this.n = (b ? 1 : 0);
    }
}
