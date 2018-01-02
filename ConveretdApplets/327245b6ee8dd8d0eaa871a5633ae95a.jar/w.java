// 
// Decompiled by Procyon v0.5.30
// 

class w
{
    byte[] a;
    int b;
    int c;
    int d;
    int e;
    int f;
    
    final void a(final byte[] a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    final void c() {
        this.e = 0;
        this.d = 0;
    }
    
    final int a(int n) {
        final boolean l = c.l;
        int n2 = 0;
        int n3;
        do {
            n3 = n - this.e;
            if (n3 <= 0) {
                break;
            }
            n2 |= this.d << n3;
            n -= this.e;
            this.d = (this.a[this.b++] & 0xFF);
            this.e = 8;
            if (!l) {
                continue;
            }
            return n2;
        } while (!l);
        n2 |= this.d >>> -n3;
        this.e -= n;
        this.d &= 255 >>> 8 - this.e;
        return n2;
    }
    
    final int b(final int n) {
        int a = this.a(n);
        if ((a & 1 << n - 1) != 0x0) {
            a |= -1 << n;
        }
        return a;
    }
    
    final int d() {
        return this.a[this.b++] & 0xFF;
    }
    
    final int e() {
        final int n = (this.a[this.b] & 0xFF) | (this.a[this.b + 1] & 0xFF) << 8;
        this.b += 2;
        return n;
    }
    
    final int f() {
        int e = this.e();
        if ((e & 0x8000) != 0x0) {
            e |= 0xFFFF0000;
        }
        return e;
    }
    
    final int g() {
        final int n = (this.a[this.b] & 0xFF) | (this.a[this.b + 1] & 0xFF) << 8 | (this.a[this.b + 2] & 0xFF) << 16 | (this.a[this.b + 3] & 0xFF) << 24;
        this.b += 4;
        return n;
    }
    
    final long h() {
        final long n = (this.a[this.b + 4] & 0xFF) | (this.a[this.b + 5] & 0xFF) << 8 | (this.a[this.b + 6] & 0xFF) << 16 | (this.a[this.b + 7] & 0xFF) << 24;
        final long n2 = (this.a[this.b + 0] & 0xFF) | (this.a[this.b + 1] & 0xFF) << 8 | (this.a[this.b + 2] & 0xFF) << 16 | (this.a[this.b + 3] & 0xFF) << 24;
        this.b += 8;
        return n + (n2 << 32);
    }
    
    final float i() {
        return Float.intBitsToFloat(this.g());
    }
    
    final double j() {
        return Double.longBitsToDouble(this.h());
    }
    
    void a(final ag ag) {
        this.e = 0;
        this.d = 0;
        ag.j = this.a(2);
        final int a = this.a(4);
        Label_0098: {
            if ((ag.j & 0x1) != 0x0) {
                ag.c = this.b(a);
                ag.d = this.b(a);
                ag.e = this.b(a);
                ag.b = this.b(a);
                if (!c.l) {
                    break Label_0098;
                }
            }
            final int n = 256;
            ag.b = n;
            ag.e = n;
            ag.d = n;
            ag.c = n;
        }
        if ((ag.j & 0x2) != 0x0) {
            ag.g = this.b(a);
            ag.h = this.b(a);
            ag.i = this.b(a);
            ag.f = this.b(a);
            return;
        }
        final boolean b = false;
        ag.f = (b ? 1 : 0);
        ag.i = (b ? 1 : 0);
        ag.h = (b ? 1 : 0);
        ag.g = (b ? 1 : 0);
    }
    
    final int c(final int n) {
        if (n < 3) {
            final int n2 = 0xFF000000 | (this.a[this.b++] & 0xFF) << 16 | (this.a[this.b++] & 0xFF) << 8 | (this.a[this.b++] & 0xFF);
            if (!c.l) {
                return n2;
            }
        }
        return (this.a[this.b++] & 0xFF) << 16 | (this.a[this.b++] & 0xFF) << 8 | (this.a[this.b++] & 0xFF) | (this.a[this.b++] & 0xFF) << 24;
    }
    
    final f k() {
        this.e = 0;
        this.d = 0;
        final int a = this.a(5);
        final f f = new f();
        f.a = this.b(a);
        f.c = this.b(a);
        f.b = this.b(a);
        f.d = this.b(a);
        return f;
    }
    
    final ac l() {
        final boolean l = c.l;
        this.e = 0;
        this.d = 0;
        final ac ac = new ac();
        Label_0069: {
            if (this.a(1) != 0) {
                final int a = this.a(5);
                ac.a = this.b(a);
                ac.d = this.b(a);
                if (!l) {
                    break Label_0069;
                }
            }
            final ac ac2 = ac;
            final ac ac3 = ac;
            final int n = 65536;
            ac3.d = n;
            ac2.a = n;
        }
        Label_0115: {
            if (this.a(1) != 0) {
                final int a2 = this.a(5);
                ac.b = this.b(a2);
                ac.c = this.b(a2);
                if (!l) {
                    break Label_0115;
                }
            }
            final ac ac4 = ac;
            final ac ac5 = ac;
            final boolean b = false;
            ac5.c = (b ? 1 : 0);
            ac4.b = (b ? 1 : 0);
        }
        final int a3 = this.a(5);
        ac.e = this.b(a3);
        ac.f = this.b(a3);
        return ac;
    }
    
    final String m() {
        String s = "";
        if (this.f >= 6) {
            while (this.b < this.a.length) {
                final byte b = this.a[this.b++];
                if (b == 0) {
                    break;
                }
                final char c = (char)(b & 0xFF);
                if (c > '\u007f') {
                    final int n = this.b - 1;
                    while (this.b < this.a.length) {
                        if (this.a[this.b++] == 0) {
                            break;
                        }
                    }
                    try {
                        s += new String(this.a, n, this.b - n, "UTF8");
                    }
                    catch (Exception ex) {}
                    return s;
                }
                s += c;
            }
        }
        else {
            while (this.b < this.a.length) {
                final byte b2 = this.a[this.b++];
                if (b2 == 0) {
                    break;
                }
                s += (char)(b2 & 0xFF);
            }
        }
        return s;
    }
}
