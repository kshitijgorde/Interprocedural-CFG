// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.OutputStream;
import java.io.InputStream;

public class E
{
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    
    public E() {
        this(1024);
    }
    
    public E(final int n) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = 0;
        this.a = new byte[n];
        final boolean b = false;
        this.c = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        this.d = -1;
        this.e = 0;
    }
    
    public E(final int n, final int e) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = 0;
        this.a = new byte[n + e];
        this.c = e;
        this.b = e;
        this.d = -1;
        this.e = e;
    }
    
    public void a() {
        this.d = -1;
        final int e = this.e;
        this.c = e;
        this.b = e;
    }
    
    public void a(final byte[] array) {
        this.a(array, 0, array.length);
    }
    
    public void a(final byte b) {
        this.a(new byte[] { b });
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        this.b(n2);
        System.arraycopy(array, n, this.a, this.c, n2);
        this.c += n2;
    }
    
    public int b() {
        if (this.c - this.b > 0) {
            return this.a[this.b++] & 0xFF;
        }
        return -1;
    }
    
    public int b(final byte[] array, final int n, final int n2) {
        final int n3 = this.c - this.b;
        if (n3 <= 0) {
            return -1;
        }
        final int n4 = (n2 > n3) ? n3 : n2;
        System.arraycopy(this.a, this.b, array, n, n4);
        this.b += n4;
        if (this.b == this.c) {
            this.f();
        }
        return n4;
    }
    
    public int b(final byte[] array) {
        return this.b(array, 0, array.length);
    }
    
    private void f() {
        if (this.b != this.e) {
            if (this.d >= this.b || this.d < 0) {
                System.arraycopy(this.a, this.b, this.a, this.e, this.c - this.b);
                this.c -= this.b - this.e;
                this.d -= this.b - this.e;
                this.b = this.e;
            }
            else if (this.d >= this.e) {
                System.arraycopy(this.a, this.d, this.a, this.e, this.c - this.d);
                this.c -= this.d - this.e;
                this.b -= this.d - this.e;
                this.d = this.e;
            }
        }
    }
    
    public byte[] c() {
        return this.a;
    }
    
    public int d() {
        return this.c - this.b;
    }
    
    public int e() {
        return this.e;
    }
    
    public int a(final InputStream inputStream, final int n) {
        this.b(n);
        final int read = inputStream.read(this.a, this.c, n);
        if (read == -1) {
            return -1;
        }
        this.c += read;
        return read;
    }
    
    public void a(final OutputStream outputStream) {
        if (this.b != this.e) {
            this.f();
        }
        outputStream.write(this.a, 0, this.c);
    }
    
    private void b(final int n) {
        if (n > this.a.length - this.c) {
            final int n2 = (this.b < this.d) ? this.b : ((this.d < 0) ? this.b : this.d);
            if (n < this.a.length - (this.c - n2 + this.e)) {
                this.f();
            }
            else {
                int n3;
                int i;
                for (n3 = this.c - n2 + n + this.e, i = this.a.length; i < n3; i <<= 1) {}
                final byte[] a = new byte[i];
                if (this.e != 0) {
                    System.arraycopy(this.a, 0, a, 0, this.e);
                }
                System.arraycopy(this.a, n2, a, this.e, this.c - n2);
                this.a = a;
                this.c -= n2 - this.e;
                if (this.d < 0) {
                    this.b = this.e;
                }
                else {
                    this.d -= n2 - this.e;
                    this.b -= n2 - this.e;
                }
            }
        }
    }
    
    public void a(final int n) {
        if (n < 0) {
            return;
        }
        this.b += n;
        if (this.b > this.c) {
            this.b = this.c;
        }
    }
}
