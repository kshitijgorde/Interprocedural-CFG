// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public final class i
{
    private static int a;
    private static int b;
    private static int c;
    private d[] d;
    private int e;
    private char f;
    private char g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int[] m;
    private int[] n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private static char[] v;
    private boolean w;
    
    public final void a(final boolean b) {
        this.w = true;
    }
    
    public final boolean a() {
        return this.w;
    }
    
    private int e() {
        return this.d[this.e].d + this.d[this.e].e;
    }
    
    private void f() {
        this.n[0] = this.i;
        this.n[1] = this.j;
        this.n[2] = 0;
        this.n[3] = this.m[2];
        this.n[4] = this.m[3];
        this.n[5] = this.m[4];
        this.n[6] = this.m[5];
        this.h = 208;
        this.p = 0;
        this.q = 7;
        final boolean i = false;
        this.k = (i ? 1 : 0);
        this.j = (i ? 1 : 0);
        this.i = (i ? 1 : 0);
        this.f = '\0';
        this.g = '\u0001';
    }
    
    private void g() {
        this.d[this.e].e = (this.m[1] >> 2 & 0x1);
        this.d[this.e].d = this.m[2];
    }
    
    private void b(final char c) {
        switch (this.m[0] & 0xF) {
            case 2: {
                this.e = (this.m[1] & 0x3);
                this.g();
                if (!this.d[this.e].a) {
                    this.i = (this.e | 0xD8);
                    this.f();
                    return;
                }
                this.g();
                this.f = '\u0001';
                this.t = this.e();
                this.u = (this.m[4] & 0xF) - 1 << 9;
                this.s = a.a.a.i.c * a.a.a.i.b << 9;
                this.r = 0;
                this.h = 240;
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                this.e = (this.m[1] & 0x3);
                this.l = (this.m[1] & 0x7);
                if (this.d[this.e].a) {
                    this.l |= 0x20;
                }
                if (this.d[this.e].d == 0) {
                    this.l |= 0x10;
                }
                if ((this.l & 0x4) == 0x4) {
                    this.l |= 0x8;
                }
                this.q = 1;
                this.p = 0;
                this.n[0] = this.l;
                this.f = '\0';
                this.g = '\u0001';
                this.h = 208;
                return;
            }
            case 5: {
                if (this.f == '\0') {
                    this.e = (this.m[1] & 0x3);
                    this.g();
                    this.f = '\u0001';
                    if (!this.d[this.e].a) {
                        this.i = (this.e | 0xD8);
                        this.f();
                        return;
                    }
                    this.t = this.e();
                    this.u = (this.m[4] & 0xF) - 1 << 9;
                    this.s = 512 + (this.m[4] - this.m[6] << 9);
                    this.r = 0;
                    this.h = 176;
                    return;
                }
                else {
                    this.d[this.e].b.a(this.t).a(this.u + this.r, c);
                    ++this.r;
                    if (this.r == this.s) {
                        this.i = (this.m[1] & 0x7);
                        this.f();
                    }
                    return;
                }
                break;
            }
            case 6: {
                this.e = (this.m[1] & 0x3);
                this.g();
                if (!this.d[this.e].a) {
                    this.i = (this.e | 0xD8);
                    this.f();
                    return;
                }
                this.f = '\u0001';
                this.t = this.e();
                this.u = (this.m[4] & 0x1F) - 1 << 8;
                this.s = this.u + 256;
                this.r = 0;
                this.h = 240;
                return;
            }
            case 7: {
                final boolean i = false;
                this.k = (i ? 1 : 0);
                this.j = (i ? 1 : 0);
                this.i = (i ? 1 : 0);
                this.e = (this.m[1] & 0x3);
                this.i = (this.m[1] & 0x7);
                if (!this.d[this.e].a) {
                    this.i |= 0xD8;
                }
                else if (this.d[this.e].d > 77) {
                    final d d = this.d[this.e];
                    d.d -= 77;
                    this.i |= 0x30;
                }
                else {
                    this.d[this.e].d = 0;
                    this.i |= 0x20;
                }
                this.h = (0x80 | 1 << (this.m[1] & 0x3));
                break;
            }
            case 8: {
                this.h = 208;
                this.q = 2;
                this.p = 0;
                if (!this.d[this.e].a) {
                    this.i |= 0x8;
                }
                if ((this.i & 0x38) == 0x0) {
                    this.i |= 0x80;
                }
                this.i &= 0x3F;
                this.n[0] = this.i;
                this.i = 0;
                this.n[1] = this.d[this.e].d;
                this.f = '\0';
                this.g = '\u0001';
                return;
            }
            case 10: {
                this.e = (this.m[1] & 0x3);
                this.d[this.e].e = (this.m[1] >> 2 & 0x1);
                if (!this.d[this.e].a) {
                    this.i = (this.e | 0xD8);
                    this.f();
                    return;
                }
                this.t = this.e();
                this.i = (this.m[1] & 0x7);
                this.f();
                this.n[5] = 65;
                return;
            }
            case 15: {
                this.h = (0x80 | 1 << (this.m[1] & 0x3));
                this.e = (this.m[1] & 0x3);
                this.d[this.e].e = (this.m[1] >> 2 & 0x1);
                if (!this.d[this.e].a) {
                    this.i = (this.e | 0xD8);
                    this.f();
                    return;
                }
                this.d[this.e].d = this.m[2];
                if (this.d[this.e].a) {
                    this.i = (0x20 | (this.m[1] & 0x7));
                    break;
                }
                this.i = (0x8 | (this.m[1] & 0x7));
                break;
            }
        }
        this.f = '\0';
    }
    
    public i() {
        this.m = new int[9];
        this.n = new int[7];
        this.w = false;
        this.d = new d[a.a.a.i.a];
        for (int i = 0; i < a.a.a.i.a; ++i) {
            this.d[i] = new d(this);
        }
        this.b();
    }
    
    public final void b() {
        this.o = 0;
        this.f = '\0';
        this.g = '\0';
        this.h = 128;
        this.w = false;
        for (int i = 0; i < 9; ++i) {
            this.m[i] = 0;
        }
        for (int j = 0; j < a.a.a.i.a; ++j) {
            this.d[j].a();
        }
    }
    
    public final void a(final char c) {
        if (this.f == '\0') {
            if (this.o == 0) {
                this.m[0] = (c & '\u001f');
                ++this.o;
                this.h |= 0x10;
            }
            else if (this.o < a.a.a.i.v[this.m[0]]) {
                this.m[this.o] = c;
                ++this.o;
            }
            if (this.o == a.a.a.i.v[this.m[0]]) {
                this.o = 0;
                this.h |= 0x20;
                this.b(c);
                this.w = true;
            }
        }
        else {
            this.b(c);
        }
    }
    
    public final int c() {
        this.w = false;
        if (this.f != '\0') {
            this = this;
            int b = 0;
            switch (this.m[0]) {
                case 2:
                case 6: {
                    b = this.d[this.e].b.a(this.t).b(this.u + this.r);
                    if (++this.r == this.s) {
                        this.i = ((this.m[1] & 0x7) | 0x40);
                        this.j = 128;
                        this.f();
                        break;
                    }
                    break;
                }
            }
            return b;
        }
        if (this.g != '\0') {
            final int n = (this = this).n[this.p];
            final i i = this;
            ++i.p;
            if (this.p == this.q) {
                this.h = 128;
                this.g = '\0';
            }
            return n;
        }
        return 0;
    }
    
    public final int d() {
        return this.h & 0xFF;
    }
    
    public final void a(int n, byte[] array) {
        this.d[n].a();
        this.d[n].a = true;
        final f b = this.d[n].b;
        final byte[] array2 = array;
        final int n2 = 0;
        final byte[] array3 = array2;
        final int n3 = 0;
        int n4 = array2.length;
        int n5 = n3;
        array = array3;
        n = n2;
        final f f = b;
        if (0 <= array.length) {
            if (n4 + 0 > array.length) {
                n4 = array.length;
            }
            for (int i = 0; i < n4; ++i) {
                f.a(n++, (char)(array[n5++] & 0xFF));
            }
        }
    }
    
    static {
        i.a = 2;
        i.b = 16;
        i.c = 2;
        i.v = new char[] { '\u0001', '\u0001', '\t', '\u0003', '\u0002', '\t', '\t', '\u0002', '\u0001', '\t', '\u0002', '\u0001', '\t', '\u0006', '\u0001', '\u0003', '\u0001', '\t', '\u0001', '\u0001', '\u0001', '\u0001', '\u0001', '\u0001', '\u0001', '\t', '\u0001', '\u0001', '\u0001', '\u0001', '\t', '\u0001' };
    }
}
