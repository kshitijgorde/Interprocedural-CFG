// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.resource;

import java.awt.Toolkit;
import java.io.InputStream;

public final class a
{
    private static a e;
    public static final boolean a;
    public static final boolean b;
    public static final boolean c;
    public static final boolean d;
    
    public static final InputStream a(final String s) {
        return com.iseemedia.apps.tourclients40.resource.a.e.getClass().getResourceAsStream(s);
    }
    
    static {
        com.iseemedia.apps.tourclients40.resource.a.e = new a();
        Toolkit.getDefaultToolkit();
        System.getProperty("java.version").startsWith("1.1.5");
        a = System.getProperty("java.version").startsWith("1.0");
        b = System.getProperty("os.name").startsWith("Mac");
        c = System.getProperty("java.vendor").startsWith("Microsoft");
        d = System.getProperty("java.vendor").startsWith("Apple");
    }
}
