// 
// Decompiled by Procyon v0.5.30
// 

package com.esial.util;

import com.esial.internationalGUI.b;

public class c
{
    private static b a;
    
    public static String a(final String s) {
        if (c.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String a = c.a.a(s2);
        if (a == null) {
            return new String(s2);
        }
        return a;
    }
    
    public c(final b a) {
        c.a = a;
    }
}
