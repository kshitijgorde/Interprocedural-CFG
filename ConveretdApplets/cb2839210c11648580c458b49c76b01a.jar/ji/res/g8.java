// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.i;
import ji.util.d;
import ji.io.h;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class g8
{
    private static Class a;
    private static Constructor b;
    private static Integer c;
    private static Integer d;
    private static Integer e;
    private static Integer f;
    private static Method g;
    private static Method h;
    private static Method i;
    private static Method j;
    private static Method k;
    private static Method l;
    static /* synthetic */ Class m;
    
    public static final char[] a(final String s, final String s2, final boolean b) {
        if (b) {
            ji.io.h.d(s2, "Converting String to a Bidi set of chars: ".concat(String.valueOf(String.valueOf(s))));
        }
        if (!ji.util.d.dp()) {
            if (b) {
                ji.io.h.d(s2, "Not JRE 1.4 so cannot process character using Bidi, output existing chars: ".concat(String.valueOf(String.valueOf(s))));
            }
            return s.toCharArray();
        }
        final char[] c = c(s, s2, b);
        if (c == null) {
            if (b) {
                ji.io.h.d(s2, "Problem occurred processing characters using Bidi, output existing chars: ".concat(String.valueOf(String.valueOf(s))));
            }
            return s.toCharArray();
        }
        return c;
    }
    
    private static final Object b(final String s, final String s2, final boolean b) {
        try {
            final Class e = e();
            if (g8.b == null && e != null) {
                g8.b = e.getConstructor((g8.m == null) ? (g8.m = class$("java.lang.String")) : g8.m, Integer.TYPE);
            }
            final Integer a = a(s2, b);
            if (g8.b != null && a != null) {
                return g8.b.newInstance(s, a);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return null;
    }
    
    private static Integer a(final String s, final boolean b) throws Exception {
        final String e = ji.util.i.e(8);
        Integer n;
        if (ji.util.d.by(e) || e.equalsIgnoreCase("lefttoright")) {
            n = d();
        }
        else if (e.equalsIgnoreCase("righttoleft")) {
            n = c();
        }
        else if (e.equalsIgnoreCase("defaultlefttoright")) {
            n = b();
        }
        else if (e.equalsIgnoreCase("defaultrighttoleft")) {
            n = a();
        }
        else {
            n = d();
        }
        if (b) {
            ji.io.h.d(s, "Bidi Base Level is: ".concat(String.valueOf(String.valueOf(n))));
        }
        return n;
    }
    
    private static Integer a() throws Exception {
        if (g8.f == null) {
            g8.f = new Integer(e().getDeclaredField("DIRECTION_DEFAULT_RIGHT_TO_LEFT").getInt(null));
        }
        return g8.f;
    }
    
    private static Integer b() throws Exception {
        if (g8.d == null) {
            g8.d = new Integer(e().getDeclaredField("DIRECTION_DEFAULT_LEFT_TO_RIGHT").getInt(null));
        }
        return g8.d;
    }
    
    private static Integer c() throws Exception {
        if (g8.e == null) {
            g8.e = new Integer(e().getDeclaredField("DIRECTION_RIGHT_TO_LEFT").getInt(null));
        }
        return g8.e;
    }
    
    private static Integer d() throws Exception {
        if (g8.c == null) {
            g8.c = new Integer(e().getDeclaredField("DIRECTION_LEFT_TO_RIGHT").getInt(null));
        }
        return g8.c;
    }
    
    private static Class e() {
        if (g8.a == null) {
            try {
                g8.a = Class.forName("java.text.Bidi");
            }
            catch (ClassNotFoundException ex) {
                ji.util.d.a(ex);
            }
        }
        return g8.a;
    }
    
    private static boolean a(final Object o) {
        try {
            if (g8.g == null) {
                g8.g = e().getMethod("isLeftToRight", (Class[])null);
            }
            return (boolean)g8.g.invoke(o, (Object[])null);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    private static boolean b(final Object o) {
        try {
            if (g8.h == null) {
                g8.h = e().getMethod("isRightToLeft", (Class[])null);
            }
            return (boolean)g8.h.invoke(o, (Object[])null);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    private static final int c(final Object o) {
        try {
            if (g8.i == null) {
                g8.i = e().getMethod("getRunCount", (Class[])null);
            }
            return (int)g8.i.invoke(o, (Object[])null);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return -1;
        }
    }
    
    private static final int a(final Object o, final int n) {
        try {
            if (g8.j == null) {
                g8.j = e().getMethod("getRunStart", Integer.TYPE);
            }
            return (int)g8.j.invoke(o, new Integer(n));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return -1;
        }
    }
    
    private static final int b(final Object o, final int n) {
        try {
            if (g8.k == null) {
                g8.k = e().getMethod("getRunLimit", Integer.TYPE);
            }
            return (int)g8.k.invoke(o, new Integer(n));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return -1;
        }
    }
    
    private static final int c(final Object o, final int n) {
        try {
            if (g8.l == null) {
                g8.l = e().getMethod("getRunLevel", Integer.TYPE);
            }
            return (int)g8.l.invoke(o, new Integer(n));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return -1;
        }
    }
    
    private static final char[] c(final String s, final String s2, final boolean b) {
        char[] charArray = null;
        try {
            final Object b2 = b(s, s2, b);
            if (a(b2)) {
                charArray = s.toCharArray();
                if (b) {
                    ji.io.h.d(s2, "All characters in string are left to right characters, output: ".concat(String.valueOf(String.valueOf(s))));
                }
            }
            else if (b(b2)) {
                charArray = new char[s.length()];
                int n = 0;
                for (int i = s.length() - 1; i >= 0; --i) {
                    charArray[n] = s.charAt(i);
                    ++n;
                }
                if (b) {
                    ji.io.h.d(s2, "All characters in string are right to left characters, output: ".concat(String.valueOf(String.valueOf(new String(charArray)))));
                }
            }
            else {
                charArray = new char[s.length()];
                int n2 = 0;
                for (int c = c(b2), j = 0; j < c; ++j) {
                    final int a = a(b2, j);
                    final int b3 = b(b2, j);
                    if (c(b2, j) % 2 > 0) {
                        for (int k = b3 - 1; k >= a; --k) {
                            charArray[n2] = s.charAt(k);
                            ++n2;
                        }
                    }
                    else {
                        for (int l = a; l < b3; ++l) {
                            charArray[n2] = s.charAt(l);
                            ++n2;
                        }
                    }
                }
                if (b) {
                    ji.io.h.d(s2, "Characters have mixed alignment, output: ".concat(String.valueOf(String.valueOf(new String(charArray)))));
                }
            }
        }
        catch (Exception ex) {
            if (b) {
                String s3 = ex.getMessage();
                if (ji.util.d.by(s3)) {
                    s3 = ex.toString();
                }
                ji.io.h.d(s2, s3);
            }
            ji.util.d.a(ex);
        }
        return charArray;
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        g8.a = null;
        g8.b = null;
        g8.c = null;
        g8.d = null;
        g8.e = null;
        g8.f = null;
        g8.g = null;
        g8.h = null;
        g8.i = null;
        g8.j = null;
        g8.k = null;
        g8.l = null;
    }
}
