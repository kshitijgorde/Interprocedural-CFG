// 
// Decompiled by Procyon v0.5.30
// 

final class bd extends ae
{
    bd a;
    int b;
    bd c;
    bd d;
    ai e;
    ai f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    
    bd() {
        this.b = 1;
    }
    
    final void b(final int n) {
        if (super.g) {
            this.g = (super.e - super.a << 16) / (super.f - super.b);
            this.l = (super.a << 16) + this.g * (n - super.b);
            this.k = this.l + 32768 >> 16;
            return;
        }
        final int n2 = super.a - 2 * super.c + super.e;
        final int n3 = 2 * (super.c - super.a);
        final int n4 = super.b - 2 * super.d + super.f;
        final int n5 = 2 * (super.d - super.b);
        this.n = 2 * (super.f - super.b);
        final int n6 = 16777216 / this.n;
        final int n7 = n6 * n6 >> 24;
        this.g = n3 * n6;
        this.i = 2 * n2 * n7;
        this.h = n5 * n6;
        this.j = 2 * n4 * n7;
        this.l = super.a << 16;
        this.m = super.b << 16;
        this.k = super.a;
        if (n > super.b) {
            this.c(n);
        }
    }
    
    final void c(final int n) {
        final boolean l = c.l;
        bd bd = null;
        Label_0125: {
            Label_0124: {
                if (super.g) {
                    this.l += this.g;
                    if (!l) {
                        break Label_0124;
                    }
                }
                final int n2 = n << 16;
                while (true) {
                    Label_0105: {
                        if (!l) {
                            break Label_0105;
                        }
                        this.m += this.h >> 8;
                        this.h += this.j;
                        this.l += this.g >> 8;
                        this.g += this.i;
                        --this.n;
                    }
                    if (this.m < n2) {
                        bd = this;
                        if (l) {
                            break Label_0125;
                        }
                        if (this.n >= 0) {
                            continue;
                        }
                    }
                    break;
                }
            }
            bd = this;
        }
        bd.k = this.l + 32768 >> 16;
    }
}
