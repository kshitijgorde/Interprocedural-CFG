// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public abstract class m extends l
{
    protected byte[] a;
    protected int b;
    protected int c;
    protected int d;
    protected long e;
    protected long f;
    
    public m() {
        this.b = 0;
        this.c = this.a();
        this.d = this.c << 3;
        this.e = 0L;
        this.f = 0L;
        this.a = new byte[this.c];
    }
    
    public m(final m m) {
        this.b = m.b;
        this.c = m.c;
        this.d = m.d;
        this.e = m.e;
        this.f = m.f;
        this.a = m.a.clone();
    }
    
    public Object clone() {
        throw new CloneNotSupportedException("clone not supported in base class");
    }
    
    public abstract int a();
    
    public abstract void a(final byte[] p0, final int p1);
    
    public abstract byte[] b();
    
    public void a(final byte[] array, int n, int i) {
        this.e += i;
        this.f += i << 3;
        final int n2;
        if (i >= (n2 = this.c - this.b)) {
            System.arraycopy(array, n, this.a, this.b, n2);
            this.a(this.a, 0);
            i -= n2;
            n += n2;
            this.b = 0;
        }
        while (i > this.c - 1) {
            this.a(array, n);
            i -= this.c;
            n += this.c;
        }
        if (i > 0) {
            System.arraycopy(array, n, this.a, this.b, i);
            this.b += i;
        }
    }
    
    public byte[] c() {
        return this.b();
    }
    
    public byte[] a(final byte[] array) {
        this.a(array, 0, array.length);
        return this.c();
    }
}
