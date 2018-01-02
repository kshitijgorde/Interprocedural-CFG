// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

public class h
{
    int a;
    int b;
    
    public h(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public boolean equals(final Object o) {
        final h h = (h)o;
        return this.a == h.a && this.b == h.b;
    }
    
    public int hashCode() {
        return 29 * this.a + this.b;
    }
}
