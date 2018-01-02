// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.util.j;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.daysofwonder.util.t;
import com.daysofwonder.util.E;

public class n implements w
{
    private static final int b;
    protected E a;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    
    private static void a(final String s) {
        t.a("BlockingByteArrayPacket", s);
    }
    
    private static void c(final byte[] array) {
        t.a("BlockingByteArrayPacket", array);
    }
    
    private static void e(final int n) {
    }
    
    private static void c(final int n, final int n2) {
    }
    
    public static E a(final int n) {
        e(n);
        return new E(n);
    }
    
    public static E a(final int n, final int n2) {
        c(n2, n);
        return new E(n, n2);
    }
    
    public n() {
        this.a = null;
        this.c = new byte[4];
        this.f = 4;
        this.g = 4;
        this.h = 0;
    }
    
    public n(final int n) {
        this.a = null;
        this.c = new byte[4];
        this.f = 4;
        this.g = 4;
        this.h = 0;
        this.h = 0;
    }
    
    public n(final int n, final int n2) {
        this.a = null;
        this.c = new byte[4];
        this.f = 4;
        this.g = 4;
        this.h = 0;
        this.h = 0;
    }
    
    public void a(final long n) {
        this.a.a(com.daysofwonder.util.w.a(n));
    }
    
    public void b(final int n) {
        this.a.a((byte)((n & 0xFF000000) >> 24));
        this.a.a((byte)((n & 0xFF0000) >> 16));
        this.a.a((byte)((n & 0xFF00) >> 8));
        this.a.a((byte)(n & 0xFF));
    }
    
    public void a(final short n) {
        this.a.a((byte)(n >> 8));
        this.a.a((byte)n);
    }
    
    public void a(final byte b) {
        this.a.a(b);
    }
    
    public void a(final byte[] array) {
        this.b(array.length);
        this.a.a(array);
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        this.b(n2);
        this.a.a(array, n, n2);
    }
    
    public long a() {
        return com.daysofwonder.util.w.a(this.a.b(), this.a.b(), this.a.b(), this.a.b(), this.a.b(), this.a.b(), this.a.b(), this.a.b());
    }
    
    public int b() {
        return com.daysofwonder.util.w.a(this.a.b(), this.a.b(), this.a.b(), this.a.b());
    }
    
    public short c() {
        return (short)com.daysofwonder.util.w.a(this.a.b(), this.a.b());
    }
    
    public int d() {
        return this.a.b();
    }
    
    public byte[] e() {
        final byte[] array = new byte[this.b()];
        this.a.b(array);
        return array;
    }
    
    public int b(final byte[] array, final int n, final int n2) {
        return this.a.b(array, n, n2);
    }
    
    public int a(final Object o, final Object o2) {
        final InputStream inputStream = (InputStream)o2;
        int n = 0;
        if (this.f > 0) {
            n = inputStream.read(this.c, 0, 4);
            if (n < 0) {
                return -1;
            }
            this.f -= n;
            if (this.f == 0) {
                this.d = com.daysofwonder.util.w.a(this.c);
                if (com.daysofwonder.req.n.b > 2) {
                    a("reading size " + this.d);
                }
                if (this.d < 0) {
                    t.d("Strange Packet Size: " + this.d);
                    throw new IOException("Strange packet size: " + this.d);
                }
                if (this.d > 256000) {
                    t.c("Packet too large: " + this.d);
                    this.d = 256000;
                }
                this.e = this.d - 4;
                this.a = a(this.e);
            }
        }
        if (this.f == 0 && this.e > 0) {
            n = this.a.a(inputStream, this.e);
            if (n < 0) {
                return -1;
            }
            this.e -= n;
            if (n == 0) {
                return n;
            }
        }
        if (this.f == 0 && this.e == 0 && n != 0) {
            return -2;
        }
        return n;
    }
    
    public void b(final byte[] array) {
        final int n = this.a.d() + this.a.e();
        if (com.daysofwonder.req.n.b > 2) {
            a("putHeader: size = " + n);
        }
        final byte[] c = this.a.c();
        com.daysofwonder.util.w.a(n, c);
        System.arraycopy(array, 0, c, 4, array.length);
        if (com.daysofwonder.req.n.b > 4) {
            c(c);
        }
    }
    
    public int a(final Object o) {
        if (n.b > 2) {
            a("writeToStream(): begin");
        }
        final OutputStream outputStream = (OutputStream)o;
        this.a.a(outputStream);
        outputStream.flush();
        if (n.b > 2) {
            a("writeToStream(): packet written");
        }
        return -2;
    }
    
    public void f() {
        this.f = 4;
        final boolean b = false;
        this.e = (b ? 1 : 0);
        this.d = (b ? 1 : 0);
        this.h = 0;
        this.a.a();
    }
    
    public void c(final int n) {
        this.a.a(n);
    }
    
    public void a(final int n, final Object o) {
        this.a = a(n, 8);
    }
    
    public int g() {
        return -1;
    }
    
    public void b(final int n, final int n2) {
        com.daysofwonder.util.w.a(n2, this.a.c(), n + this.a.e());
    }
    
    public void d(final int n) {
    }
    
    static {
        b = j.a("BlockingByteArrayPacket");
    }
}
