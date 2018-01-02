// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class C
{
    static final String[] a;
    private static final Properties b;
    
    private static void a() {
        try {
            final String s = "/";
            int n = 0;
            for (int i = 0; i < C.a.length; ++i) {
                final InputStream resourceAsStream = C.class.getResourceAsStream(s + C.a[i]);
                if (resourceAsStream != null) {
                    try {
                        C.b.load(resourceAsStream);
                        n = 1;
                    }
                    catch (IOException ex) {
                        t.a("Couldn't load debug.properties");
                        t.a(ex);
                    }
                }
                if (n == 0) {
                    final InputStream resourceAsStream2 = C.class.getResourceAsStream(s + "META-INF" + s + C.a[i]);
                    if (resourceAsStream2 != null) {
                        try {
                            C.b.load(resourceAsStream2);
                            n = 1;
                        }
                        catch (IOException ex2) {
                            t.a("Couldn't load debug.properties");
                            t.a(ex2);
                        }
                    }
                }
                if (n == 0) {
                    final InputStream resourceAsStream3 = C.class.getResourceAsStream(C.a[i]);
                    if (resourceAsStream3 != null) {
                        try {
                            C.b.load(resourceAsStream3);
                            n = 1;
                        }
                        catch (IOException ex3) {
                            t.a("Couldn't load debug.properties");
                            t.a(ex3);
                        }
                    }
                }
                if (n == 0) {
                    System.err.println("Warning: failed to load the Debug.properties file.");
                }
            }
        }
        catch (Exception ex4) {
            t.a(ex4);
        }
    }
    
    public static String a(final String s) {
        return C.b.getProperty(s);
    }
    
    static {
        a = new String[] { "debug.properties" };
        b = new Properties();
        a();
    }
}
