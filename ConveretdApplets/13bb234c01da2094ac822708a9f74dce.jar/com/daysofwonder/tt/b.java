// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import com.daysofwonder.a.j;
import com.daysofwonder.a.l;
import com.daysofwonder.a.h;

public class b extends h
{
    private static int[] a;
    private i[] b;
    private l c;
    
    public b() {
        this.c = new k();
    }
    
    public static synchronized void a(final j j) {
        i.a.a(j);
        i.a.c(0);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.b.length; ++i) {
            sb.append(this.b[i].f());
            sb.append(',');
        }
        return sb.toString();
    }
    
    static {
        b.a = new int[] { 12, 12, 12, 12, 12, 12, 12, 12, 14 };
    }
}
