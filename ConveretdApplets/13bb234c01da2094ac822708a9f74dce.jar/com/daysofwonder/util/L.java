// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class L implements B
{
    private final B a;
    private final B b;
    
    protected L(final B a, final B b) {
        this.a = a;
        this.b = b;
    }
    
    protected B a(final B b) {
        if (b == this.a) {
            return this.b;
        }
        if (b == this.b) {
            return this.a;
        }
        final B d = d(this.a, b);
        final B d2 = d(this.b, b);
        if (d == this.a && d2 == this.b) {
            return this;
        }
        return c(d, d2);
    }
    
    public static B a(final B b, final B b2) {
        return c(b, b2);
    }
    
    public static B b(final B b, final B b2) {
        return d(b, b2);
    }
    
    protected static B c(final B b, final B b2) {
        if (b == null) {
            return b2;
        }
        if (b2 == null) {
            return b;
        }
        return new L(b, b2);
    }
    
    protected static B d(final B b, final B b2) {
        if (b == b2 || b == null) {
            return null;
        }
        if (b instanceof L) {
            return ((L)b).a(b2);
        }
        return b;
    }
    
    public void a(final Object o, final Object o2) {
        this.a.a(o, o2);
        this.b.a(o, o2);
    }
}
