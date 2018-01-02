// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.io.IOException;
import java.io.FileNotFoundException;
import ji.io.h;
import ji.util.i;

public class av
{
    private String a;
    private long b;
    private String c;
    private boolean d;
    
    public static boolean a(final String s, final String s2) {
        boolean c = false;
        if (g.a(s, s2) && g.a(s2)) {
            c = g.v.c(s, s2);
        }
        return c;
    }
    
    public static boolean b(final String s, final String s2) {
        boolean d = false;
        if (g.a(s, s2) && g.a(s2)) {
            d = g.v.d(s, s2);
        }
        return d;
    }
    
    public av(final String a, final String s, final String c) throws Exception {
        this.a = null;
        this.b = 0L;
        this.c = null;
        this.d = false;
        this.c = c;
        this.a = a;
        if (g.a(a, c) && g.a(c)) {
            if (i.c(195)) {
                h.d(c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: opening ").append(a).append("..."))));
            }
            this.b = g.v.a(a, s, c);
            if (i.c(195)) {
                h.d(c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: opened: ").append(this.b).append("/").append(a))));
            }
            if (this.b == 0) {
                if (i.c(195)) {
                    h.d(c, "EXCACHE: file not found!");
                }
                throw new FileNotFoundException(String.valueOf(String.valueOf(a)).concat(" (cannot open this file)"));
            }
            this.d = true;
        }
    }
    
    public final boolean a() {
        return this.d;
    }
    
    public final void a(final byte[] array, final int n, final int n2, final boolean b) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: write1: ").append(n).append(", ").append(n2).append(", ").append(this.a))));
        }
        g.v.a(this.b, array, n, n2, b);
    }
    
    public final void a(final int[] array, final int n, final int n2, final boolean b) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: write2: ").append(n).append(", ").append(n2).append(", ").append(this.a))));
        }
        g.v.a(this.b, array, n, n2, b);
    }
    
    public final void a(final int n) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: writeByte: ").append(n).append(", ").append(this.a))));
        }
        g.v.a(this.b, n);
    }
    
    public final void b(final int n) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: writeShort: ").append(n).append(", ").append(this.a))));
        }
        g.v.b(this.b, n);
    }
    
    public final void c(final int n) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: writeInt: ").append(n).append(", ").append(this.a))));
        }
        g.v.c(this.b, n);
    }
    
    public final void a(final long n) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: writeLong: ").append(n).append(", ").append(this.a))));
        }
        g.v.b(this.b, n);
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws IOException {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: read1: ").append(n).append(", ").append(n2).append(", ").append(this.a))));
        }
        final int a = g.v.a(this.b, array, n, n2);
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: read1: num = ".concat(String.valueOf(String.valueOf(a))));
        }
        return a;
    }
    
    public final int b(final int[] array, final int n, final int n2, final boolean b) throws IOException {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: read2: ").append(n).append(", ").append(n2).append(", ").append(this.a))));
        }
        return g.v.b(this.b, array, n, n2, b);
    }
    
    public final int b() throws IOException {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: readByte: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        return g.v.d(this.b);
    }
    
    public final int c() throws IOException {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: readShort: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        return g.v.e(this.b);
    }
    
    public final int d() throws IOException {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: readInt: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        return g.v.f(this.b);
    }
    
    public final long e() throws IOException {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: readLong: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        return g.v.g(this.b);
    }
    
    public final void b(final long n) {
        if (i.c(195)) {
            h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: seek: ").append(n).append(", ").append(this.a))));
        }
        g.v.a(this.b, n);
    }
    
    public static long c(final String s, final String s2) {
        long a = -1L;
        if (g.a(s, s2) && g.a(s2)) {
            a = g.v.a(s, s2);
        }
        return a;
    }
    
    public final long f() {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: length: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        final long a = g.v.a(this.b);
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: length = ".concat(String.valueOf(String.valueOf(a))));
        }
        return a;
    }
    
    public static final long d(final String s, final String s2) {
        long b = 0L;
        if (g.a(s, s2) && g.a(s2)) {
            b = g.v.b(s, s2);
        }
        return b;
    }
    
    public final long g() {
        if (i.c(195)) {
            h.d(this.c, "EXCACHE: getFilePointer: ".concat(String.valueOf(String.valueOf(this.a))));
        }
        return g.v.b(this.b);
    }
    
    public final void h() {
        if (this.a != null) {
            if (this.d) {
                if (this.b != 0 && g.v != null) {
                    if (i.c(195)) {
                        h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("EXCACHE: close: ").append(this.b).append("/").append(this.a))));
                    }
                    g.v.c(this.b);
                }
                this.b = 0L;
            }
            this.a = null;
            this.c = null;
        }
    }
}
