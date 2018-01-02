// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

import com.daysofwonder.req.p;
import com.daysofwonder.req.k;
import java.util.Enumeration;
import java.util.Hashtable;

public class e
{
    private Hashtable a;
    private Hashtable b;
    
    public e() {
        this.a = new Hashtable();
        this.b = new Hashtable();
    }
    
    public Enumeration a() {
        return this.a.elements();
    }
    
    public synchronized void a(final int n, final int n2, final long n3, final String s, final String s2) {
        final d d = new d(n, n2, n3, new b(0, s), s2);
        this.a.put(new Integer(n), d);
        this.b.put(new Integer(n2), d);
    }
    
    public synchronized void a(final int n) {
        final d d = this.a.remove(n);
        if (d != null) {
            this.b.remove(d.b);
        }
    }
    
    public synchronized d b(final int n) {
        return this.b.get(n);
    }
    
    public synchronized boolean c(final int n) {
        return this.b.containsKey(n);
    }
    
    public void a(final k k) {
        k.writeInt(this.a.size());
        final Enumeration<d> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final d d = elements.nextElement();
            k.writeInt(d.a);
            k.writeInt(d.b);
            k.writeLong(d.c);
            k.writeInt(d.d.a());
            k.writeBoolean(d.d.b() != null);
            if (d.d.b() != null) {
                k.writeUTF(d.d.b());
            }
            k.writeUTF(d.e);
        }
    }
    
    public void b(final k k) {
        for (int int1 = k.readInt(), i = 0; i < int1; ++i) {
            final int int2 = k.readInt();
            final int int3 = k.readInt();
            final long long1 = k.readLong();
            final int int4 = k.readInt();
            String utf = null;
            if (k.readBoolean()) {
                utf = k.readUTF();
            }
            final d d = new d(int2, int3, long1, new b(int4, utf), k.readUTF());
            this.a.put(int2, d);
            this.b.put(int3, d);
        }
    }
    
    public int c(final k k) {
        int n = 4 + this.a.size() * 20;
        final Enumeration<d> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final d d = elements.nextElement();
            ++n;
            if (d.d.b() != null) {
                n += p.a(d.d.b());
            }
            n += p.a(d.e);
        }
        return n;
    }
    
    public String toString() {
        return this.a.toString();
    }
}
