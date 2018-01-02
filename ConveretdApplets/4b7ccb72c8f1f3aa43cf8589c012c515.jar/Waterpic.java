// 
// Decompiled by Procyon v0.5.30
// 

public final class Waterpic
{
    public int[] a;
    int[] b;
    public int c;
    public int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    
    public final void a(final Screen32 screen32, final Screen32 screen33) {
        int h = this.h;
        int n = 0;
        int i = this.i;
        for (int j = 0; j < this.d; ++j) {
            for (int k = 0; k < this.c; ++k) {
                final int n2 = (this.a[h - this.c] + this.a[h + this.c] + this.a[h - 1] + this.a[h + 1] >> 1) - this.a[i];
                final int n3 = n2 - (n2 >> this.g);
                this.a[i++] = n3;
                final int n4 = 1024 - n3;
                final int n5 = ((k - this.e) * n4 >> 10) + this.e;
                final int n6 = (n5 >= this.c) ? (this.c - 1) : ((n5 < 0) ? 0 : n5);
                final int n7 = ((j - this.f) * n4 >> 10) + this.f;
                screen32.a[n++] = screen33.a[n6 + screen33.b[(n7 >= this.d) ? (this.d - 1) : ((n7 < 0) ? 0 : n7)]];
                ++h;
            }
        }
    }
    
    public Waterpic(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.e = c >> 1;
        this.f = d >> 1;
        this.j = 9;
        this.k = 450;
        this.l = 6;
        this.g = 5;
        this.a = new int[this.c * (this.d + 2) * 2];
        this.h = this.c;
        this.i = this.c * (this.d + 3);
        this.b = new int[this.d + 2];
        int n = 0;
        for (int i = 0; i < this.d + 2; ++i) {
            this.b[i] = n;
            n += this.c;
        }
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n2 + i + 1 > 0 && n2 + i + 1 < this.d - n4) {
                    final int[] a = this.a;
                    final int n5 = this.i + this.b[n2 + i + 1] + n + j + 1;
                    a[n5] += n3;
                }
            }
        }
    }
    
    public final void a() {
        if (this.h == this.c) {
            this.h = this.i;
            this.i = this.c;
            return;
        }
        this.i = this.h;
        this.h = this.c;
    }
}
