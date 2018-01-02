// 
// Decompiled by Procyon v0.5.30
// 

public class ag implements h
{
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    c k;
    
    public void a(final c k) {
        this.k = k;
    }
    
    public c a() {
        return this.k;
    }
    
    public void a(final t t) {
    }
    
    ag() {
        final int n = 256;
        this.e = n;
        this.d = n;
        this.c = n;
        this.b = n;
    }
    
    ag(final ag ag) {
        this.a(ag);
    }
    
    void a(final ag ag) {
        this.b = ag.b;
        this.f = ag.f;
        this.c = ag.c;
        this.g = ag.g;
        this.d = ag.d;
        this.h = ag.h;
        this.e = ag.e;
        this.i = ag.i;
        this.j = ag.j;
    }
    
    int b(int n) {
        if (this.j == 0) {
            return n;
        }
        final int n2 = n >> 16 & 0xFF;
        final int n3 = n >> 8 & 0xFF;
        final int n4 = n & 0xFF;
        final int n5 = n >> 24 & 0xFF;
        int n6 = (n2 * this.c >> 8) + this.g;
        if (n6 > 255) {
            n6 = 255;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        int n7 = (n3 * this.d >> 8) + this.h;
        if (n7 > 255) {
            n7 = 255;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        int n8 = (n4 * this.e >> 8) + this.i;
        if (n8 > 255) {
            n8 = 255;
        }
        if (n8 < 0) {
            n8 = 0;
        }
        int n9 = (n5 * this.b >> 8) + this.f;
        if (n9 > 255) {
            n9 = 255;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        n = (n6 << 16) + (n7 << 8) + n8 + (n9 << 24);
        return n;
    }
    
    void a(final ai ai) {
        if (this.j == 0) {
            return;
        }
        final int a = ai.a;
        final int n = ((a >> 16 & 0xFF) * this.c >> 8) + this.g;
        final int n2 = ((a >> 8 & 0xFF) * this.d >> 8) + this.h;
        final int n3 = ((a & 0xFF) * this.e >> 8) + this.i;
        final int n4 = ((a >>> 24 & 0xFF) * this.b >> 8) + this.f;
        ai.a((((n4 & 0xFF00) != 0x0) ? ((n4 <= 0) ? 0 : 255) : n4) << 24 | (((n & 0xFF00) != 0x0) ? ((n <= 0) ? 0 : 255) : n) << 16 | (((n2 & 0xFF00) != 0x0) ? ((n2 <= 0) ? 0 : 255) : n2) << 8 | (((n3 & 0xFF00) != 0x0) ? ((n3 <= 0) ? 0 : 255) : n3));
    }
    
    void b(final ai ai) {
        if (this.j == 0 || ai.ac == null) {
            return;
        }
        final int[] ac = ai.ac;
        int n = ac.length - 1;
        while (true) {
            Label_0276: {
                if (!c.l) {
                    break Label_0276;
                }
                final int n2 = ac[n];
                final int n3 = ((n2 >> 16 & 0xFF) * this.c >> 8) + this.g;
                final int n4 = ((n2 >> 8 & 0xFF) * this.d >> 8) + this.h;
                final int n5 = ((n2 & 0xFF) * this.e >> 8) + this.i;
                final int n6 = ((n2 >> 24 & 0xFF) * this.b >> 8) + this.f;
                final int n7 = ((n3 & 0xFF00) != 0x0) ? ((n3 <= 0) ? 0 : 255) : n3;
                final int n8 = ((n4 & 0xFF00) != 0x0) ? ((n4 <= 0) ? 0 : 255) : n4;
                final int n9 = ((n5 & 0xFF00) != 0x0) ? ((n5 <= 0) ? 0 : 255) : n5;
                final int n10 = ((n6 & 0xFF00) != 0x0) ? ((n6 <= 0) ? 0 : 255) : n6;
                if (n10 != 255) {
                    ai.r = true;
                }
                ac[n] = (n10 << 24 | n7 << 16 | n8 << 8 | n9);
                --n;
            }
            if (n < 0) {
                return;
            }
            continue;
        }
    }
    
    void b(final ag ag) {
        if (ag.j == 0) {
            return;
        }
        this.c = this.c * ag.c >> 8;
        this.d = this.d * ag.d >> 8;
        this.e = this.e * ag.e >> 8;
        this.b = this.b * ag.b >> 8;
        this.g = (this.g * ag.c >> 8) + ag.g;
        this.h = (this.h * ag.d >> 8) + ag.h;
        this.i = (this.i * ag.e >> 8) + ag.i;
        this.f = (this.f * ag.b >> 8) + ag.f;
        this.j = 3;
    }
}
