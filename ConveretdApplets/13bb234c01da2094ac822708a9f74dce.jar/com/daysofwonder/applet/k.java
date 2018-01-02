// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.req.l;
import com.daysofwonder.a.i;

public class k
{
    private String a;
    private int b;
    private boolean c;
    private int d;
    
    public k(final i i) {
        this.a = i.z();
        this.b = i.w();
        this.c = false;
        this.d = i.B();
    }
    
    public k(final l l) {
        this.a = l.b;
        this.b = l.a;
        this.c = l.c;
        this.d = l.d;
    }
    
    public String a() {
        return this.a;
    }
    
    public int b() {
        return this.b;
    }
    
    public boolean c() {
        return this.c;
    }
    
    public int d() {
        return this.d;
    }
    
    public int hashCode() {
        return 31 * 1 + this.b;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.b == ((k)o).b);
    }
}
