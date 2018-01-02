// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class l
{
    private int a;
    private int b;
    private int c;
    private Object[] d;
    
    public final synchronized boolean a() {
        return this.a == this.b;
    }
    
    public final synchronized Object b() {
        if (this.a == this.b) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        final Object o = this.d[this.a];
        this.d[this.a] = null;
        this.a = (this.a + 1) % this.d.length;
        --this.c;
        return o;
    }
    
    public final synchronized void a(final Object o) {
        if ((this.b + 1) % this.d.length == this.a) {
            final Object[] d = new Object[2 * (this.d.length - 1) + 1];
            System.arraycopy(this.d, this.a, d, 0, this.d.length - this.a);
            if (this.a != 0) {
                System.arraycopy(this.d, 0, d, this.d.length - this.a, this.a - 1);
            }
            this.a = 0;
            this.b = this.c;
            this.d = d;
        }
        this.d[this.b] = o;
        this.b = (this.b + 1) % this.d.length;
        ++this.c;
    }
    
    public final synchronized void c() {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i] = null;
        }
        final boolean b = false;
        this.b = (b ? 1 : 0);
        this.a = (b ? 1 : 0);
        this.c = 0;
    }
    
    public l() {
        this(10);
    }
    
    public l(final int n) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = new Object[n + 1];
    }
}
