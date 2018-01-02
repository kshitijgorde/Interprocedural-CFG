// 
// Decompiled by Procyon v0.5.30
// 

public final class q
{
    O a;
    boolean b;
    private boolean p;
    boolean c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    
    public q(final O a) {
        this.c = false;
        this.a = a;
    }
    
    public final void a() {
        if (this.p) {
            if ((this.o & 0x1) == 0x0) {
                if (this.g > 0) {
                    --this.g;
                }
            }
            else if (this.g < 63) {
                ++this.g;
            }
            this.m = (this.b ? ((this.g << 1) + this.n) : 0);
            this.o >>= 1;
        }
        --this.f;
        if (this.f <= 0) {
            this.p = false;
            if (this.k == 0 && this.d == 1) {
                this.i = this.h;
                this.k = this.j;
            }
            if (this.k > 0) {
                this.o = this.a.a.h.a(this.i);
                this.a.a.b.b(4);
                --this.k;
                ++this.i;
                if (this.i > 65535) {
                    this.i = 32768;
                }
                this.p = true;
                if (this.k == 0 && this.d == 2) {
                    this.c = true;
                }
            }
            this.f = 8;
        }
        if (this.c) {
            this.a.a.b.a(0);
        }
    }
    
    public final void a(final int n, int n2) {
        if (n == 16400) {
            if (n2 >> 6 == 0) {
                this.d = 0;
            }
            else if ((n2 >> 6 & 0x1) == 0x1) {
                this.d = 1;
            }
            else if (n2 >> 6 == 2) {
                this.d = 2;
            }
            if ((n2 & 0x80) == 0x0) {
                this.c = false;
            }
            final O a = this.a;
            n2 &= 0xF;
            final O o = a;
            this.e = ((n2 >= 0 && n2 < 16) ? o.h[n2] : 0);
            return;
        }
        if (n == 16401) {
            this.g = (n2 >> 1 & 0x3F);
            this.n = (n2 & 0x1);
            if (this.a.o) {
                this.m = (this.g << 1) + this.n;
            }
        }
        else {
            if (n == 16402) {
                this.h = (n2 << 6 | 0xC000);
                this.i = this.h;
                return;
            }
            if (n == 16403) {
                this.j = (n2 << 4) + 1;
                this.k = this.j;
                return;
            }
            if (n == 16405) {
                if ((n2 >> 4 & 0x1) == 0x0) {
                    this.k = 0;
                }
                else {
                    this.i = this.h;
                    this.k = this.j;
                }
                this.c = false;
            }
        }
    }
}
