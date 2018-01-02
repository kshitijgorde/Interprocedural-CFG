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

public class extends
{
    private static String ja = "\u7cfd\u7cfb\u7cfc\u7cec";
    private static String na = "\u7cf0\u7cec\u7cfa";
    private static String oa = "\u7cb8";
    private static String pa = "\u7cef\u7ce8\u7ce5\u7cfa\u7cec";
    private static String sa = "\u7ce7\u7ce6";
    private static String ta = "\u7cb9";
    private static String ua = "";
    private static String va = "\u7ca5";
    private static String wa = "\u7cf5";
    private static String rb = "\u7ccb\u7cc6\u7cc5\u7ccd";
    private static String sb = "\u7cc0\u7cdd\u7cc8\u7cc5\u7cc0\u7cca";
    private static String tb = "\u7cd9\u7cc5\u7cc8\u7cc0\u7cc7";
    private static String _ = "\u7ce1\u7cfd\u7cfd\u7cf9\u7cb3\u7ca6\u7ca6";
    private static String a = "\u7ce1\u7cfd\u7cfd\u7cf9\u7cfa\u7cb3\u7ca6\u7ca6";
    
    public static int a(final Object o, final String s, final int n) {
        return b(o, s, 10, n);
    }
    
    public static boolean a(final Object o, final String s, final boolean b) {
        final String b2 = b(o, s);
        if (b2 == null) {
            return b;
        }
        return b2.equalsIgnoreCase(extends.ja) || b2.equalsIgnoreCase(extends.na) || b2.equalsIgnoreCase(extends.oa) || (!b2.equalsIgnoreCase(extends.pa) && !b2.equalsIgnoreCase(extends.sa) && !b2.equalsIgnoreCase(extends.ta) && b);
    }
    
    public static int b(final Object o, final String s, final int n, final int n2) {
        try {
            return Integer.parseInt(b(o, s), n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public static double a(final Object o, final String s, final double n) {
        try {
            return new Double(b(o, s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String _(final Object o, final String s, final String s2) {
        String b = b(o, s);
        if (b == null || b.equals(extends.ua)) {
            b = s2;
        }
        return b;
    }
    
    public static Color b(final Object o, final String s, final int n) {
        final String b = b(o, s);
        if (b != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(b, extends.va);
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
        }
        return new Color(b(o, s, 16, n));
    }
    
    private static String b(final Object o, final String s) {
        if (o instanceof Applet) {
            return ((Applet)o).getParameter(s);
        }
        if (o instanceof Hashtable) {
            return ((Hashtable)o).get(s);
        }
        return null;
    }
    
    private static String a(final Object o, final String s, final String s2) {
        final String b = b(o, s);
        if (b == null || b.equals(extends.ua)) {
            return s2;
        }
        return b;
    }
    
    public static Font b(final Object o, final String s, final String s2, final int n, final int n2) {
        final String b = b(o, s);
        String nextToken = s2;
        int int1 = n2;
        int n3 = n;
        if (b != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(b, extends.va);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), extends.wa);
                n3 = 0;
                while (stringTokenizer2.hasMoreTokens()) {
                    final String trim = stringTokenizer2.nextToken().trim();
                    if (trim.equalsIgnoreCase(extends.rb)) {
                        n3 |= 0x1;
                    }
                    else if (trim.equalsIgnoreCase(extends.sb)) {
                        n3 |= 0x2;
                    }
                    else {
                        if (!trim.equalsIgnoreCase(extends.tb)) {
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
    
    public static URL _(final Object o, final String s, final boolean b) {
        if (s != null) {
            URL resource = null;
            if (b) {
                resource = o.getClass().getResource(s);
            }
            if (resource == null) {
                if (!s.startsWith(extends._) && !s.startsWith(extends.a) && o instanceof Applet) {
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
    
    public static URL a(final Object o, final String s, final boolean b) {
        return _(o, b(o, s), b);
    }
    
    private static String c(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x17C89);
        }
        return new String(array);
    }
    
    static {
        extends.ja = c(extends.ja);
        extends.na = c(extends.na);
        extends.oa = c(extends.oa);
        extends.pa = c(extends.pa);
        extends.sa = c(extends.sa);
        extends.ta = c(extends.ta);
        extends.ua = c(extends.ua);
        extends.va = c(extends.va);
        extends.wa = c(extends.wa);
        extends.rb = c(extends.rb);
        extends.sb = c(extends.sb);
        extends.tb = c(extends.tb);
        extends._ = c(extends._);
        extends.a = c(extends.a);
    }
}
