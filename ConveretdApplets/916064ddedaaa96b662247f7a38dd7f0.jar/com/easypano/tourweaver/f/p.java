// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.util.Enumeration;
import java.util.Hashtable;

public class p
{
    Hashtable a;
    
    public p() {
        this.a = new Hashtable();
    }
    
    public void a(final h h) {
        final boolean i = r.i;
        final Enumeration e = h.e();
        while (e.hasMoreElements()) {
            final d d = e.nextElement();
            this.a(h, d);
            d.k().render();
            if (i) {
                break;
            }
        }
    }
    
    public void a(final h h, final d d) {
        final boolean i = r.i;
        final String name = d.k().getName();
        final q q2;
        q q = q2 = this.a.get(name);
        final q q3;
        h h2 = null;
        Label_0069: {
            Label_0066: {
                if (!i) {
                    if (q2 != null) {
                        q3 = q;
                        h2 = h;
                        if (i) {
                            break Label_0069;
                        }
                        if (q3.a(h)) {
                            break Label_0066;
                        }
                    }
                    this.a(h, name);
                }
                q = q2;
            }
            h2 = h;
        }
        q3.a(h2, d);
    }
    
    private void a(final q q) {
        q q2 = q;
        if (!r.i) {
            if (!(q instanceof s)) {
                return;
            }
            q2 = q;
        }
        q2.a(new bb());
    }
    
    public q a(final h h, final String s) {
        final boolean i = r.i;
        q q = null;
        boolean b2;
        final boolean b = b2 = (h instanceof y);
        final q q2;
        if (!i) {
            if (b) {
                q = (q2 = this.a.get(s));
                if (i) {
                    return q2;
                }
                if (q2 == null) {
                    q = new bb();
                    this.a.put(s, q);
                    return q2;
                }
                return q2;
            }
            else {
                final boolean b3;
                b2 = (b3 = (h instanceof s));
            }
        }
        Object value = null;
        Label_0123: {
            if (!i) {
                if (b) {
                    final s s2 = (s)h;
                    q = (q)h;
                    q.a(this.a(s2.k(), s));
                    return q2;
                }
                value = h;
                if (i) {
                    break Label_0123;
                }
                b2 = (h instanceof x);
            }
            if (!b2) {
                return q2;
            }
            value = this.a.get(s);
        }
        final q q3;
        q = (q3 = (q)value);
        if (!i) {
            if (q3 == null) {
                q = new g();
                this.a.put(s, q);
            }
        }
        return q2;
    }
}
