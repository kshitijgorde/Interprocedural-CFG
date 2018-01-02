// 
// Decompiled by Procyon v0.5.30
// 

public final class l extends at
{
    private int a;
    private boolean b;
    public boolean a;
    
    public l() {
        this.a = 0;
        this.b = false;
        this.a = false;
    }
    
    public final int a() {
        return 33;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.b = false;
        this.a = 0;
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
    
    public final void a(final int n, final int a) {
        switch (n) {
            case 32768: {
                if (this.a) {
                    if ((a & 0x40) != 0x0) {
                        this.c();
                    }
                    else {
                        this.b();
                    }
                    this.c(a & 0x1F);
                    return;
                }
                this.c(a);
            }
            case 32769: {
                if (this.a) {
                    this.d(a & 0x1F);
                    return;
                }
                this.d(a);
            }
            case 32770: {
                this.g(a * 2 + 0);
                this.h(a * 2 + 1);
            }
            case 32771: {
                this.i(a * 2 + 0);
                this.j(a * 2 + 1);
            }
            case 40960: {
                this.k(a);
            }
            case 40961: {
                this.l(a);
            }
            case 40962: {
                this.m(a);
            }
            case 40963: {
                this.n(a);
            }
            case 49152: {
                this.a = a;
            }
            case 49153:
            case 49154:
            case 57345:
            case 57346: {
                this.b = (a != 0);
            }
            case 57344: {
                if ((a & 0x40) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.b && (super.a.a.a.b & 0x18) != 0x0 && n >= 0 && n <= 239) {
            if (this.a == 0) {
                this.a = 0;
                this.b = false;
                return 3;
            }
            ++this.a;
        }
        return 0;
    }
    
    public final void a(final long n) {
        this.a = false;
        if (n == 2323450558L || n == 929118424L || n == 2918582006L || n == 2690820465L || n == 2051635939L || n == 3133804713L || n == 711285153L || n == 2931293417L || n == 3642816991L) {
            this.a = true;
        }
    }
}
