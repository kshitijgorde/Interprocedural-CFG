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

public class instanceof
{
    private static String V = "\uc579\uc57f\uc578\uc568";
    private static String W = "\uc574\uc568\uc57e";
    private static String _a = "\uc53c";
    private static String aa = "\uc56b\uc56c\uc561\uc57e\uc568";
    private static String ba = "\uc563\uc562";
    private static String fa = "\uc53d";
    private static String ga = "";
    private static String ha = "\uc521";
    private static String ia = "\uc571";
    private static String db = "\uc54f\uc542\uc541\uc549";
    private static String eb = "\uc544\uc559\uc54c\uc541\uc544\uc54e";
    private static String fb = "\uc55d\uc541\uc54c\uc544\uc543";
    private static String gb = "\uc565\uc579\uc579\uc57d\uc537\uc522\uc522";
    private static String hb = "\uc565\uc579\uc579\uc57d\uc57e\uc537\uc522\uc522";
    
    public static int _(final Object o, final String s, final int n) {
        return b(o, s, 10, n);
    }
    
    public static boolean _(final Object o, final String s, final boolean b) {
        final String _ = _(o, s);
        if (_ == null) {
            return b;
        }
        return _.equalsIgnoreCase(instanceof.V) || _.equalsIgnoreCase(instanceof.W) || _.equalsIgnoreCase(instanceof._a) || (!_.equalsIgnoreCase(instanceof.aa) && !_.equalsIgnoreCase(instanceof.ba) && !_.equalsIgnoreCase(instanceof.fa) && b);
    }
    
    public static int b(final Object o, final String s, final int n, final int n2) {
        try {
            return Integer.parseInt(_(o, s), n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public static double _(final Object o, final String s, final double n) {
        try {
            return new Double(_(o, s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String _(final Object o, final String s, final String s2) {
        String _ = _(o, s);
        if (_ == null || _.equals(instanceof.ga)) {
            _ = s2;
        }
        return _;
    }
    
    public static Color b(final Object o, final String s, final int n) {
        final String _ = _(o, s);
        if (_ != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(_, instanceof.ha);
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
        }
        return new Color(b(o, s, 16, n));
    }
    
    private static String _(final Object o, final String s) {
        if (o instanceof Applet) {
            return ((Applet)o).getParameter(s);
        }
        if (o instanceof Hashtable) {
            return ((Hashtable)o).get(s);
        }
        return null;
    }
    
    private static String a(final Object o, final String s, final String s2) {
        final String _ = _(o, s);
        if (_ == null || _.equals(instanceof.ga)) {
            return s2;
        }
        return _;
    }
    
    public static Font a(final Object o, final String s, final String s2, final int n, final int n2) {
        final String _ = _(o, s);
        String nextToken = s2;
        int int1 = n2;
        int n3 = n;
        if (_ != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(_, instanceof.ha);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), instanceof.ia);
                n3 = 0;
                while (stringTokenizer2.hasMoreTokens()) {
                    final String trim = stringTokenizer2.nextToken().trim();
                    if (trim.equalsIgnoreCase(instanceof.db)) {
                        n3 |= 0x1;
                    }
                    else if (trim.equalsIgnoreCase(instanceof.eb)) {
                        n3 |= 0x2;
                    }
                    else {
                        if (!trim.equalsIgnoreCase(instanceof.fb)) {
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
                if (!s.startsWith(instanceof.gb) && !s.startsWith(instanceof.hb) && o instanceof Applet) {
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
        return _(o, _(o, s), b);
    }
    
    private static String h(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEC50D);
        }
        return new String(array);
    }
    
    static {
        instanceof.V = h(instanceof.V);
        instanceof.W = h(instanceof.W);
        instanceof._a = h(instanceof._a);
        instanceof.aa = h(instanceof.aa);
        instanceof.ba = h(instanceof.ba);
        instanceof.fa = h(instanceof.fa);
        instanceof.ga = h(instanceof.ga);
        instanceof.ha = h(instanceof.ha);
        instanceof.ia = h(instanceof.ia);
        instanceof.db = h(instanceof.db);
        instanceof.eb = h(instanceof.eb);
        instanceof.fb = h(instanceof.fb);
        instanceof.gb = h(instanceof.gb);
        instanceof.hb = h(instanceof.hb);
    }
}
