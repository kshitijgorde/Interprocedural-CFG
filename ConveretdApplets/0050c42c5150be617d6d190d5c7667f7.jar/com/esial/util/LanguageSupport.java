// 
// Decompiled by Procyon v0.5.30
// 

package com.esial.util;

import com.esial.internationalGUI.c;

public class LanguageSupport
{
    private static c a;
    
    public static String translate(final String s) {
        if (LanguageSupport.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String a = LanguageSupport.a.a(s2);
        if (a == null) {
            return new String(s2);
        }
        return a;
    }
    
    public LanguageSupport(final c a) {
        LanguageSupport.a = a;
    }
}
