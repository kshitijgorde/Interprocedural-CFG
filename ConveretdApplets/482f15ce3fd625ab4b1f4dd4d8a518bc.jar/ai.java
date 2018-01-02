// 
// Decompiled by Procyon v0.5.30
// 

public final class ai extends at
{
    public int a;
    public int b;
    public int c;
    
    public ai() {
        this.a = 0;
        this.b = 0;
        this.c = 1;
    }
    
    public final int a() {
        return 117;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.a = 0;
        this.b = 0;
        this.c = 1;
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                this.c(n2);
            }
            case 32769: {
                this.d(n2);
            }
            case 32770: {
                this.e(n2);
            }
            case 40960: {
                this.g(n2);
            }
            case 40961: {
                this.h(n2);
            }
            case 40962: {
                this.i(n2);
            }
            case 40963: {
                this.j(n2);
            }
            case 40964: {
                this.k(n2);
            }
            case 40965: {
                this.l(n2);
            }
            case 40966: {
                this.m(n2);
            }
            case 40967: {
                this.n(n2);
            }
            case 40968:
            case 40969:
            case 40970:
            case 40971:
            case 40972:
            case 40973:
            case 40974:
            case 40975: {}
            case 49153:
            case 49154:
            case 49155: {
                this.b = n2;
                this.a = n2;
            }
            case 57344: {
                this.c = (n2 & 0x1);
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.b != 0 && this.c != 0 && this.a == n) {
            this.b = 0;
            return 3;
        }
        return 0;
    }
}
