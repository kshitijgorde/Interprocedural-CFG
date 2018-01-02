// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.util.Enumeration;
import java.io.IOException;
import com.daysofwonder.util.t;
import java.util.StringTokenizer;
import java.util.PropertyResourceBundle;
import java.io.InputStream;
import java.util.Hashtable;

public class f
{
    private static Hashtable a;
    private static boolean b;
    private static Hashtable c;
    
    public static final void a(final InputStream inputStream) {
        try {
            final PropertyResourceBundle propertyResourceBundle = new PropertyResourceBundle(inputStream);
            final Enumeration<String> keys = propertyResourceBundle.getKeys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String string = propertyResourceBundle.getString(s);
                Hashtable<?, ?> hashtable;
                if (!f.a.containsKey(nextToken)) {
                    hashtable = new Hashtable<Object, Object>();
                    f.a.put(nextToken, hashtable);
                }
                else {
                    hashtable = f.a.get(nextToken);
                }
                hashtable.put(nextToken2.toUpperCase(), string);
                f.b = true;
            }
        }
        catch (IOException ex) {
            t.a(ex);
        }
    }
    
    public static final void a(final Class clazz, final String s) {
        a(clazz.getResourceAsStream(s));
    }
    
    public static Object a(final String s, final String s2) {
        if (!f.b) {
            throw new RuntimeException("Associative class factory not initialized");
        }
        final String s3 = f.a.get(s).get(s2.toUpperCase());
        if (s3 != null) {
            return Class.forName(s3).newInstance();
        }
        throw new InstantiationException(s2 + " has not been registered in " + s + " category -- check your request.properties");
    }
    
    public static synchronized G a(final G g) {
        final int y = g.y();
        v v = f.c.get(y);
        if (v == null) {
            try {
                v = (v)a("request", Integer.valueOf(y).toString());
            }
            catch (Exception ex) {
                t.a(ex);
                throw new IOException();
            }
            f.c.put(y, v);
        }
        final G a = v.a(g);
        g.w();
        return a;
    }
    
    static {
        f.a = new Hashtable();
        f.b = false;
        f.c = new Hashtable();
        try {
            a(f.class.getResourceAsStream("request.properties"));
        }
        catch (RuntimeException ex) {
            System.err.println("runtime ex");
            t.a(ex);
        }
        catch (Exception ex2) {
            System.err.println("ex");
            t.a(ex2);
        }
    }
}
