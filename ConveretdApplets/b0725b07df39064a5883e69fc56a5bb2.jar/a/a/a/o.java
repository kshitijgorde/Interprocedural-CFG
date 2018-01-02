// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

class o extends p
{
    private h[] c;
    private char[] d;
    private char[] e;
    
    public o() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.c = new h[16];
        this.d = new char[256];
        this.e = new char[256];
    }
    
    public boolean g() {
        for (int i = 0; i < 16; ++i) {
            this.c[i] = null;
        }
        for (int j = 0; j < 256; ++j) {
            this.d[j] = '\u00ff';
            this.e[j] = '\u00ff';
        }
        return true;
    }
    
    public void b(final int n) {
        for (int n2 = 0; n2 < 16 && this.c[n2] != null; ++n2) {
            this.c[n2].a(n);
        }
    }
    
    public boolean f() {
        for (int i = 0; i < 16; ++i) {
            if (this.c[i] != null) {
                this.c[i].b();
            }
        }
        return true;
    }
    
    public char a(final char c) {
        final char c2;
        if ((c2 = this.d[c]) != '\u00ff') {
            return this.c[c2].a(c);
        }
        return '\u00ff';
    }
    
    public void a(final char c, final char c2) {
        final char c3;
        if ((c3 = this.e[c]) != '\u00ff') {
            this.c[c3].a(c, c2);
        }
    }
    
    public boolean a(final char c, final char c2, final h h, boolean b, boolean b2) {
        boolean b3 = false;
        int n = 16;
        if ((!b && !b2) || c2 <= '\0' || c < '\0' || c + c2 > '\u0100') {
            return false;
        }
        for (int i = 0; i < 16; ++i) {
            if (this.c[i] == h) {
                n = i;
                b3 = true;
                break;
            }
            if (this.c[i] == null && i < n) {
                n = i;
            }
        }
        if (n >= 16) {
            return false;
        }
        if (b) {
            for (char c3 = c; c3 < c + c2; ++c3) {
                if (this.d[c3] != '\u00ff') {
                    return false;
                }
            }
        }
        if (b2) {
            for (char c4 = c; c4 < c + c2; ++c4) {
                if (this.e[c4] != '\u00ff') {
                    return false;
                }
            }
        }
        if (!b3) {
            h.a();
            this.c[n] = h;
        }
        while (true) {
            char[] array;
            if (b) {
                array = this.d;
            }
            else {
                if (!b2) {
                    break;
                }
                array = this.e;
            }
            for (char c5 = c; c5 < c + c2; ++c5) {
                array[c5] = (char)n;
            }
            if (!b && b2) {
                b2 = false;
            }
            b = false;
        }
        return true;
    }
}
