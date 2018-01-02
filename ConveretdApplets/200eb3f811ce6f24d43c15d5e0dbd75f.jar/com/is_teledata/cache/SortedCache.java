// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.cache;

import java.util.Vector;
import java.util.Hashtable;

public class SortedCache extends bs
{
    public Hashtable a;
    public Vector b;
    
    public SortedCache() {
        this.a = new Hashtable();
        this.b = new Vector();
    }
    
    public bt b(final String s, final Object o, final int n, final long n2) {
        return new bt(s, o, n, n2);
    }
    
    public synchronized bt b(final String s, final boolean b) {
        if (!b) {
            this.c();
        }
        final bt bt = this.a.get(s);
        if (bt != null) {
            ++super.f;
        }
        else {
            ++super.g;
        }
        ++super.e;
        return bt;
    }
    
    public synchronized bt a(final bt bt) {
        if (super.a == 0 || super.b == 0) {
            return null;
        }
        final long c = bt.c();
        if (c < System.currentTimeMillis()) {
            ++super.h;
            ++super.i;
            return null;
        }
        this.c();
        final bt bt2 = this.a.get(bt.d());
        if (bt2 != null) {
            this.b(bt2);
        }
        while (this.b()) {
            this.d();
            ++super.j;
        }
        this.a.put(bt.d(), bt);
        int n = 0;
        int i = this.b.size() - 1;
        while (i >= n) {
            final int n2 = n + (i - n) / 2;
            final long c2 = this.b.elementAt(n2).c();
            if (c2 < c) {
                i = n2 - 1;
            }
            else {
                if (c2 <= c) {
                    n = n2;
                    break;
                }
                n = n2 + 1;
            }
        }
        this.b.insertElementAt(bt, n);
        ++super.d;
        super.c += bt.a();
        ++super.h;
        return bt2;
    }
    
    public final synchronized bt b(final bt bt) {
        final int index = this.b.indexOf(bt);
        if (index >= 0) {
            this.a(index);
            return bt;
        }
        return null;
    }
    
    public final synchronized void c() {
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.b.size() > 0 && this.b.lastElement().c() <= currentTimeMillis) {
            this.d();
        }
    }
    
    public final void a(final int n) {
        final bt bt = this.b.elementAt(n);
        super.c -= bt.a();
        --super.d;
        ++super.i;
        this.b.removeElementAt(n);
        this.a.remove(bt.d());
    }
    
    public final void d() {
        this.a(this.b.size() - 1);
    }
    
    public final synchronized void a() {
        this.b = new Vector();
        this.a = new Hashtable();
        super.i += super.d;
        super.c = 0;
        super.d = 0;
    }
}
