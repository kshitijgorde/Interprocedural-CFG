import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class C27 extends InputStream
{
    int c;
    int d;
    int e;
    static char[] g;
    static String h;
    static char[] i;
    static String j;
    static char[] k;
    InputStream l;
    int m;
    boolean n;
    char[] o;
    boolean p;
    static char[] q;
    static String r;
    public static String s;
    boolean t;
    String u;
    int v;
    public static int w;
    int x;
    
    static {
        C27.s = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
        C27.g = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
        C27.w = 0;
        C27.k = new char[] { '\0', '\0', '\0', '\0', '#', '\u0001' };
        C27.j = new String(C27.k);
        C27.i = new char[] { '\0', '\0', '\0', '\0', '\u0010', '\0' };
        C27.r = new String(C27.i);
        C27.q = new char[] { '\0', '\0', '\0', '\0', '\u0011', '\0' };
        C27.h = new String(C27.q);
    }
    
    public C27(final InputStream inputStream) {
        this(new char[0], inputStream);
        this.p = false;
    }
    
    public int read() throws IOException {
        ++C27.w;
        if (this.u != null && this.x < this.u.length()) {
            return this.u.charAt(this.x++);
        }
        if (this.t) {
            return this.a();
        }
        final int read = this.l.read();
        if (read == -1) {
            return read;
        }
        if ((char)read != '{') {
            return read;
        }
        final char[] array = new char[Math.max(C27.i.length, C27.k.length)];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (char)this.l.read();
        }
        this.u = new String(array);
        if (this.u.equals(C27.j) || this.u.equals(C27.r)) {
            this.t = true;
            return this.a();
        }
        this.x = 0;
        return 123;
    }
    
    public C27(final char[] array, final InputStream l) {
        this.e = -1;
        this.d = -1;
        this.v = -1;
        this.n = false;
        this.p = true;
        this.o = new char[65536];
        this.l = l;
        for (int i = 0; i < array.length; ++i) {
            this.o[i] = array[i];
        }
        this.c = 0;
        this.m = array.length;
        this.t = false;
        this.x = Integer.MAX_VALUE;
    }
    
    protected int a() throws IOException {
        if (this.e == -1 && this.d == -1) {
            this.b();
        }
        if (this.e == -1 && this.d == -1) {
            for (int n = this.l.read(); n != -1 && n != 125 && Character.isSpace((char)n); n = this.l.read()) {}
            this.t = false;
            return this.read();
        }
        if (this.e > 0) {
            --this.e;
            if (this.e == 0) {
                this.e = -1;
            }
            final int read = this.l.read();
            this.o[this.m++] = (char)read;
            if (this.m == this.o.length) {
                this.n = true;
                this.m = 0;
            }
            return read;
        }
        if (this.d == -2) {
            final int read2 = this.l.read();
            if (read2 == -1) {
                throw new IOException("Error while reading offset code, found EOF");
            }
            this.d = read2 + 18;
        }
        if (this.d > 0 && this.v < 0) {
            final int read3 = this.l.read();
            final int read4 = this.l.read();
            if (read3 == -1 || read4 == -1) {
                throw new IOException("Invalid extended compression code, found EOF instead");
            }
            this.v = read4 << 8;
            this.v += read3;
        }
        int n2 = this.m - this.v - 1;
        if (!this.p) {
            if (!this.n) {
                n2 = this.v++;
            }
            else {
                n2 = this.m + this.v;
            }
        }
        if (n2 < 0) {
            n2 += this.o.length;
        }
        if (n2 >= this.o.length) {
            n2 -= this.o.length;
        }
        final char c = this.o[n2];
        this.o[this.m++] = c;
        if (this.m == this.o.length) {
            this.n = true;
            this.m = 0;
        }
        --this.d;
        if (this.d == 0) {
            this.v = -1;
            this.d = -1;
        }
        return c;
    }
    
    protected void b() throws IOException {
        final int read = this.l.read();
        if (read == -1) {
            throw new IOException("Error reading the compression code, found EOF");
        }
        final int n = read >> 4;
        final int e = read & 0xF;
        if (e == 0) {
            this.e = -1;
        }
        else if (e > 0 && e < 15) {
            this.e = e;
        }
        else {
            final int read2 = this.l.read();
            if (read2 == -1) {
                throw new IOException("While trying to read extended literal length found EOF");
            }
            this.e = read2 + 15;
        }
        if (n == 0) {
            this.d = -1;
        }
        else if (n > 0 && n < 15) {
            this.d = 3 + n;
        }
        else {
            this.d = -2;
        }
    }
}
