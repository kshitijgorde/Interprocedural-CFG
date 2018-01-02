// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.awt.c;
import ji.util.d;
import java.util.Enumeration;
import java.util.Hashtable;

public class a1
{
    private Hashtable a;
    
    private boolean a(final long n) {
        if (this.a == null) {
            return false;
        }
        final Enumeration<sq> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            if (n == elements.nextElement().c) {
                return true;
            }
        }
        return false;
    }
    
    public final long a(final String s, final String s2) {
        if (this.a != null) {
            final sq sq = this.a.get(s);
            if (sq != null) {
                sq.b.put(s2, s2);
                return sq.c;
            }
        }
        else {
            this.a = new Hashtable();
        }
        return this.c(s, s2);
    }
    
    private long c(final String s, final String s2) {
        if (this.a == null) {
            this.a = new Hashtable();
        }
        long cq = d.cq(s);
        boolean b = false;
        do {
            if (!this.a(cq)) {
                b = true;
                this.a.put(s, new sq(cq, s, s2, (ad4)null));
            }
            else {
                ++cq;
            }
        } while (!b);
        return cq;
    }
    
    public final boolean a(final String s, final a7 a) {
        if (this.a == null) {
            return false;
        }
        final sq sq = this.a.get(s);
        if (sq == null) {
            return false;
        }
        sq.a = a;
        return true;
    }
    
    public final a7 a(final String s) {
        if (this.a == null) {
            return null;
        }
        final sq sq = this.a.get(s);
        if (sq == null) {
            return null;
        }
        return sq.a;
    }
    
    public final long[] b(final String s) {
        if (this.a == null) {
            return null;
        }
        final c c = new c("netcachefiledata1");
        final Enumeration<sq> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final sq sq = elements.nextElement();
            if (sq.b.get(s) != null) {
                c.c(sq);
            }
        }
        final long[] array = new long[c.b()];
        for (int i = 0; i < c.b(); ++i) {
            array[i] = ((sq)c.b(i)).c;
        }
        return array;
    }
    
    public final boolean b(final String s, final String s2) {
        if (this.a == null) {
            return true;
        }
        final sq sq = this.a.get(s);
        return sq == null || this.a(sq, s2);
    }
    
    private boolean a(final sq sq, final String s) {
        if (sq.b.get(s) != null) {
            sq.b.remove(s);
        }
        if (sq.b.size() == 0) {
            final String d = sq.d;
            sq.a();
            this.a.remove(d);
            return true;
        }
        return false;
    }
    
    public final boolean a(final long n, final String s) {
        if (this.a == null) {
            return true;
        }
        final Enumeration<sq> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final sq sq = elements.nextElement();
            if (n == sq.c) {
                return this.a(sq, s);
            }
        }
        return false;
    }
    
    public final void a() {
        if (this.a != null) {
            final Enumeration<String> keys = this.a.keys();
            while (keys.hasMoreElements()) {
                ((sq)this.a.get(keys.nextElement())).a();
            }
            this.a.clear();
            this.a = null;
        }
    }
    
    private class sq
    {
        a7 a;
        Hashtable b;
        long c;
        String d;
        
        private sq(final a1 a1, final long c, final String d, final String s) {
            this.c = c;
            this.d = d;
            (this.b = new Hashtable()).put(s, s);
        }
        
        private void a() {
            this.a = null;
            if (this.b != null) {
                this.b.clear();
                this.b = null;
            }
            this.d = null;
        }
    }
    
    interface ad4
    {
    }
}
