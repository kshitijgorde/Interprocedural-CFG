// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import java.util.HashMap;
import java.util.Map;
import com.daysofwonder.a.m;

public class p extends m
{
    public static final String[] a;
    public static final String[] b;
    private static final Map c;
    private static final Map d;
    
    public p() {
        final String[] b = p.b;
        for (int length = b.length, i = 0; i < length; ++i) {
            this.a(b[i], false);
        }
    }
    
    public boolean a(final String s) {
        return super.a(b(s));
    }
    
    public static String b(final String s) {
        return p.d.get(s);
    }
    
    static {
        a = new String[] { "us", "eu", "ch", "u1", "u2", "u3" };
        b = new String[] { "us", "eu", "ch", "u1" };
        c = new HashMap();
        d = new HashMap();
        p.c.put("OLFT-TTOL-USAM-2008", "us");
        p.c.put("OLFT-TTOL-EURO-2008", "eu");
        p.c.put("OLFT-TTOL-SWIS-2008", "ch");
        p.c.put("OLFT-TTOL-1910-2008", "u1");
        p.d.put("us", "us");
        p.d.put("eu", "eu");
        p.d.put("ch", "ch");
        p.d.put("u1", "u1");
        p.d.put("u2", "u1");
        p.d.put("u3", "u1");
    }
}
