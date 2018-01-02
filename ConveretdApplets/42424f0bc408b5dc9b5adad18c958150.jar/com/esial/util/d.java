// 
// Decompiled by Procyon v0.5.30
// 

package com.esial.util;

import com.esial.internationalGUI.c;

public class d
{
    private static c a;
    
    public static String a(final String s) {
        if (d.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String a = d.a.a(s2);
        if (a == null) {
            return new String(s2);
        }
        return a;
    }
    
    public d(final c a) {
        d.a = a;
    }
}
