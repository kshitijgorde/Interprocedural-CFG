import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aa
{
    public int a;
    private int k;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    private int l;
    private au a;
    public int j;
    
    public aa(final int l, final au a) {
        this.a = 0;
        this.k = 0;
        this.b = 88;
        this.c = 90;
        this.d = 10;
        this.e = 32;
        this.f = 38;
        this.g = 40;
        this.h = 37;
        this.i = 39;
        this.l = 1;
        this.a = null;
        this.j = 0;
        this.l = l;
        this.a = a;
        this.b = a.a.l;
        this.c = a.a.m;
        this.d = a.a.n;
        this.e = a.a.o;
        this.f = a.a.p;
        this.g = a.a.q;
        this.h = a.a.r;
        this.i = a.a.s;
    }
    
    public final boolean a(final int n) {
        if (this.l == this.a.a.d) {
            if (n == this.b) {
                this.j |= 0x1;
                return true;
            }
            if (n == this.c) {
                this.j |= 0x2;
                return true;
            }
            if (n == this.e) {
                this.j |= 0x4;
                return true;
            }
            if (n == this.d) {
                this.j |= 0x8;
                return true;
            }
            if (n == this.f) {
                this.j |= 0x10;
                return true;
            }
            if (n == this.g) {
                this.j |= 0x20;
                return true;
            }
            if (n == this.h) {
                this.j |= 0x40;
                return true;
            }
            if (n == this.i) {
                this.j |= 0x80;
                return true;
            }
        }
        return false;
    }
    
    public final boolean b(final int n) {
        if (this.l == this.a.a.d) {
            if (n == this.b) {
                this.j &= 0xFE;
                return true;
            }
            if (n == this.c) {
                this.j &= 0xFD;
                return true;
            }
            if (n == this.e) {
                this.j &= 0xFB;
                return true;
            }
            if (n == this.d) {
                this.j &= 0xF7;
                return true;
            }
            if (n == this.f) {
                this.j &= 0xEF;
                return true;
            }
            if (n == this.g) {
                this.j &= 0xDF;
                return true;
            }
            if (n == this.h) {
                this.j &= 0xBF;
                return true;
            }
            if (n == this.i) {
                this.j &= 0x7F;
                return true;
            }
        }
        return false;
    }
    
    private int b() {
        int a = this.a;
        if (this.l == 2 && this.a.a.a.a == 185471631L) {
            a = (this.a.a.a & 0x8);
        }
        if ((a & 0x30) == 0x30) {
            a &= 0xCF;
        }
        if ((a & 0xC0) == 0xC0) {
            a &= 0x3F;
        }
        return a;
    }
    
    public final int a() {
        final int n = this.b() >> this.k;
        this.k = (this.k + 1 & 0x7);
        return n & 0x1;
    }
    
    public final void a() {
        this.k = 0;
    }
    
    public final void b() {
        this.a = this.j;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = aK.c(inputStream);
        this.k = aK.c(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write(("JY" + this.l).getBytes());
        aK.a(outputStream, 2);
        aK.c(outputStream, this.a);
        aK.c(outputStream, this.k);
    }
    
    public final String toString() {
        return "NESCafe JoyPad " + this.l;
    }
}
