import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.util.Hashtable;
import java.applet.Applet;
import java.util.StringTokenizer;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    private static String l = "\u9cdc\u9cda\u9cdd\u9ccd";
    private static String m = "\u9cd1\u9ccd\u9cdb";
    private static String o = "\u9c99";
    private static String p = "\u9cce\u9cc9\u9cc4\u9cdb\u9ccd";
    private static String s = "\u9cc6\u9cc7";
    private static String u = "\u9c98";
    private static String v = "";
    private static String w = "\u9c84";
    private static String O = "\u9cd4";
    private static String P = "\u9cea\u9ce7\u9ce4\u9cec";
    private static String sa = "\u9ce1\u9cfc\u9ce9\u9ce4\u9ce1\u9ceb";
    private static String ta = "\u9cf8\u9ce4\u9ce9\u9ce1\u9ce6";
    private static String ua = "\u9cc0\u9cdc\u9cdc\u9cd8\u9c92\u9c87\u9c87";
    
    public static int b(final Object o, final String s, final int n) {
        return _(o, s, 10, n);
    }
    
    public static boolean _(final Object o, final String s, final boolean b) {
        final String a = a(o, s);
        if (a == null) {
            return b;
        }
        return a.equalsIgnoreCase(m.l) || a.equalsIgnoreCase(m.m) || a.equalsIgnoreCase(m.o) || (!a.equalsIgnoreCase(m.p) && !a.equalsIgnoreCase(m.s) && !a.equalsIgnoreCase(m.u) && b);
    }
    
    public static int _(final Object o, final String s, final int n, final int n2) {
        try {
            return Integer.parseInt(a(o, s), n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public static double a(final Object o, final String s, final double n) {
        try {
            return new Double(a(o, s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String a(final Object o, final String s, final String s2) {
        String a = a(o, s);
        if (a == null || a.equals(m.v)) {
            a = s2;
        }
        return a;
    }
    
    public static Color a(final Object o, final String s, final int n) {
        final String a = a(o, s);
        if (a != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(a, m.w);
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
        }
        return new Color(_(o, s, 16, n));
    }
    
    private static String a(final Object o, final String s) {
        if (o instanceof Applet) {
            return ((Applet)o).getParameter(s);
        }
        if (o instanceof Hashtable) {
            return ((Hashtable)o).get(s);
        }
        return null;
    }
    
    private static String b(final Object o, final String s, final String s2) {
        final String a = a(o, s);
        if (a == null || a.equals(m.v)) {
            return s2;
        }
        return a;
    }
    
    public static Font _(final Object o, final String s, final String s2, final int n, final int n2) {
        final String a = a(o, s);
        String nextToken = s2;
        int int1 = n2;
        int n3 = n;
        if (a != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(a, m.w);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), m.O);
                n3 = 0;
                while (stringTokenizer2.hasMoreTokens()) {
                    final String trim = stringTokenizer2.nextToken().trim();
                    if (trim.equalsIgnoreCase(m.P)) {
                        n3 |= 0x1;
                    }
                    else if (trim.equalsIgnoreCase(m.sa)) {
                        n3 |= 0x2;
                    }
                    else {
                        if (!trim.equalsIgnoreCase(m.ta)) {
                            n3 = n;
                            break;
                        }
                        n3 |= 0x0;
                    }
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    int1 = Integer.parseInt(stringTokenizer.nextToken());
                }
                catch (Exception ex) {}
            }
        }
        return new Font(nextToken, n3, int1);
    }
    
    public static URL b(final Object o, final String s, final boolean b) {
        if (s != null) {
            URL resource = null;
            if (b) {
                resource = o.getClass().getResource(s);
            }
            if (resource == null) {
                if (!s.startsWith(m.ua) && o instanceof Applet) {
                    try {
                        resource = new URL(((Applet)o).getDocumentBase(), s);
                    }
                    catch (MalformedURLException ex) {}
                }
                else {
                    try {
                        resource = new URL(s);
                    }
                    catch (MalformedURLException ex2) {}
                }
            }
            return resource;
        }
        return null;
    }
    
    public static URL _(final Object o, final String s, final boolean b) {
        return b(o, a(o, s), b);
    }
    
    private static String d(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u9ca8');
        }
        return new String(array);
    }
    
    static {
        m.l = d(m.l);
        m.m = d(m.m);
        m.o = d(m.o);
        m.p = d(m.p);
        m.s = d(m.s);
        m.u = d(m.u);
        m.v = d(m.v);
        m.w = d(m.w);
        m.O = d(m.O);
        m.P = d(m.P);
        m.sa = d(m.sa);
        m.ta = d(m.ta);
        m.ua = d(m.ua);
    }
}
