// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

public class d
{
    public int a;
    public int b;
    public long c;
    public b d;
    public String e;
    
    public d(final int a, final int b, final long c, final b d, final String e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append('[').append(this.a).append(',').append(this.b).append(',').append(this.e).append(',').append(this.d).append(']');
        return sb.toString();
    }
}
