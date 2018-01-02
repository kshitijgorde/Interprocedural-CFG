// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.cache;

import java.util.Hashtable;

public class LRUCache extends bs
{
    public Hashtable a;
    public dv b;
    
    public LRUCache() {
        this.a = new Hashtable();
        this.b = new dv(this);
    }
    
    public bt b(final String s, final Object o, final int n, final long n2) {
        return new em(s, o, n, n2);
    }
    
    public synchronized bt a(final bt bt) {
        if (super.a == 0 || super.b == 0) {
            return null;
        }
        if (bt.c() < System.currentTimeMillis()) {
            ++super.h;
            ++super.i;
            return null;
        }
        final em em = this.a.put(bt.d(), bt);
        ++super.h;
        if (em != null) {
            this.b.a(em);
        }
        final em b = this.b.b((em)bt);
        if (b != null) {
            this.a.remove(b.d());
            ++super.j;
        }
        return em;
    }
    
    public synchronized bt b(final String s, final boolean b) {
        em em = this.a.get(s);
        if (!b && em != null && System.currentTimeMillis() > em.c()) {
            this.b(em);
            em = null;
        }
        if (em != null) {
            ++super.f;
            this.b.a(em);
            final em b2 = this.b.b(em);
            if (b2 != null) {
                this.a.remove(b2.d());
            }
            --super.i;
        }
        else {
            ++super.g;
        }
        ++super.e;
        return em;
    }
    
    public synchronized bt b(bt bt) {
        bt = this.a.remove(bt.d());
        if (bt != null) {
            this.b.a((em)bt);
        }
        return bt;
    }
    
    public synchronized void a() {
        this.a.clear();
        this.b.b();
    }
}
