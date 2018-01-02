// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.util.Hashtable;

public final class bE
{
    private Hashtable a;
    
    public bE() {
        this.a = new Hashtable();
    }
    
    public bE(final Hashtable a) {
        if (a != null) {
            this.a = a;
            return;
        }
        this.a = new Hashtable();
    }
    
    public final bE a(final String s) {
        final Hashtable value;
        if ((value = this.a.get(s)) != null && value instanceof Hashtable) {
            return new bE(value);
        }
        return null;
    }
    
    public final String b(final String s) {
        return this.a.get(s);
    }
    
    public final Hashtable a() {
        return this.a;
    }
    
    public final void a(final String s, final bE be) {
        if (be != null && be.a != null) {
            this.a.put(s, be.a);
        }
    }
    
    public final void a(final String s, final String s2) {
        if (s2 != null) {
            this.a.put(s, s2);
        }
    }
    
    public final boolean equals(final Object o) {
        if (o == null || !(o instanceof bE)) {
            return false;
        }
        final bE be = (bE)o;
        if (this.a == null || be.a == null) {
            return false;
        }
        if (this.a.keySet().size() != be.a.keySet().size()) {
            return false;
        }
        final Enumeration<String> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (this.a.get(s) instanceof String) {
                if (!this.b(s).equals(be.b(s))) {
                    return false;
                }
                continue;
            }
            else {
                if (!this.a(s).equals(be.a(s))) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
}
