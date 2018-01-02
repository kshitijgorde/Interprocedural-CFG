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

public class catch
{
    private static String a = "\u5aa6\u5aa0\u5aa7\u5ab7";
    private static String b = "\u5aab\u5ab7\u5aa1";
    private static String c = "\u5ae3";
    private static String d = "\u5ab4\u5ab3\u5abe\u5aa1\u5ab7";
    private static String e = "\u5abc\u5abd";
    private static String f = "\u5ae2";
    private static String g = "";
    private static String h = "\u5afe";
    private static String i = "\u5aae";
    private static String I = "\u5a90\u5a9d\u5a9e\u5a96";
    private static String J = "\u5a9b\u5a86\u5a93\u5a9e\u5a9b\u5a91";
    private static String K = "\u5a82\u5a9e\u5a93\u5a9b\u5a9c";
    private static String L = "\u5aba\u5aa6\u5aa6\u5aa2\u5ae8\u5afd\u5afd";
    private static String M = "\u5aba\u5aa6\u5aa6\u5aa2\u5aa1\u5ae8\u5afd\u5afd";
    
    public static int a(final Object o, final String s, final int n) {
        return _(o, s, 10, n);
    }
    
    public static boolean _(final Object o, final String s, final boolean b) {
        final String b2 = b(o, s);
        if (b2 == null) {
            return b;
        }
        return b2.equalsIgnoreCase(catch.a) || b2.equalsIgnoreCase(catch.b) || b2.equalsIgnoreCase(catch.c) || (!b2.equalsIgnoreCase(catch.d) && !b2.equalsIgnoreCase(catch.e) && !b2.equalsIgnoreCase(catch.f) && b);
    }
    
    public static int _(final Object o, final String s, final int n, final int n2) {
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
        if (b == null || b.equals(catch.g)) {
            b = s2;
        }
        return b;
    }
    
    public static Color b(final Object o, final String s, final int n) {
        final String b = b(o, s);
        if (b != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(b, catch.h);
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
        }
        return new Color(_(o, s, 16, n));
    }
    
    private static String b(final Object o, final String s) {
        String parameter = null;
        if (o instanceof Applet) {
            parameter = ((Applet)o).getParameter(s);
        }
        else if (o instanceof Hashtable) {
            parameter = ((Hashtable)o).get(s);
        }
        return parameter;
    }
    
    private static String a(final Object o, final String s, final String s2) {
        final String b = b(o, s);
        if (b == null || b.equals(catch.g)) {
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
            final StringTokenizer stringTokenizer = new StringTokenizer(b, catch.h);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), catch.i);
                n3 = 0;
                while (stringTokenizer2.hasMoreTokens()) {
                    final String trim = stringTokenizer2.nextToken().trim();
                    if (trim.equalsIgnoreCase(catch.I)) {
                        n3 |= 0x1;
                    }
                    else if (trim.equalsIgnoreCase(catch.J)) {
                        n3 |= 0x2;
                    }
                    else {
                        if (!trim.equalsIgnoreCase(catch.K)) {
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
                if (!s.startsWith(catch.L) && !s.startsWith(catch.M) && o instanceof Applet) {
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
    
    public static URL b(final Object o, final String s, final boolean b) {
        return _(o, b(o, s), b);
    }
    
    private static String f(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x15AD2);
        }
        return new String(array);
    }
    
    static {
        catch.a = f(catch.a);
        catch.b = f(catch.b);
        catch.c = f(catch.c);
        catch.d = f(catch.d);
        catch.e = f(catch.e);
        catch.f = f(catch.f);
        catch.g = f(catch.g);
        catch.h = f(catch.h);
        catch.i = f(catch.i);
        catch.I = f(catch.I);
        catch.J = f(catch.J);
        catch.K = f(catch.K);
        catch.L = f(catch.L);
        catch.M = f(catch.M);
    }
}
