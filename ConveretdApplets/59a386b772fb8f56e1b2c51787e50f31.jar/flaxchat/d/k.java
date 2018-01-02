// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import flaxchat.h.g;
import flaxchat.h.c;

public class k
{
    private final Object a;
    private final String b;
    public final int c;
    int d;
    int e;
    boolean f;
    
    public k(final String s) {
        this.a = s;
        this.b = s;
        this.c = 0;
        this.f = false;
    }
    
    public k(final String b, final Object a) {
        this.a = a;
        this.b = b;
        this.c = 0;
        this.f = false;
    }
    
    public k(final String s, final int d, final int e, final int c) {
        this.b = s;
        this.a = s;
        this.f = false;
        this.d = d;
        this.e = e;
        this.c = c;
    }
    
    public String toString() {
        return this.a.toString();
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof k) {
            return this.b.equalsIgnoreCase(((k)o).b);
        }
        return this.b.equalsIgnoreCase(o.toString());
    }
    
    public c a() {
        return (c)this.a;
    }
    
    public g b() {
        return (g)this.a;
    }
}
