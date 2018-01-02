// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.util.Hashtable;
import flaxchat.e.p;
import java.util.StringTokenizer;
import java.awt.Point;
import java.net.MalformedURLException;
import java.io.File;
import java.applet.Applet;
import java.util.Properties;
import java.net.URL;

public class a
{
    public static URL a;
    private static Properties b;
    private static String[] c;
    private static String[] z;
    
    public static void a(final Applet applet, final URL url) {
        a(applet.getParameter(flaxchat.i.a.z[1]) != null, applet, url);
    }
    
    public static void a(final boolean b, final Applet applet, URL a) {
        if (b) {
            try {
                a = new URL(a.z[16] + new File(new File(a.z[18]).getAbsolutePath()).getParent().replace('\\', '/') + a.z[17]);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            a.a = a;
            a.b = b.b(new URL(a.a, a.z[15]));
            final String parameter = applet.getParameter(a.z[0]);
            if (parameter != null && parameter.trim().length() != 0) {
                ((Hashtable<String, String>)a.b).put(a.z[0], parameter);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        b.a();
    }
    
    public static String a(final String s) {
        return flaxchat.i.a.b.getProperty(s);
    }
    
    public static boolean a(String property, final boolean b) {
        property = flaxchat.i.a.b.getProperty(property);
        return "1".equals(property) || (!"0".equals(property) && (flaxchat.i.a.z[12].equalsIgnoreCase(property) || (!flaxchat.i.a.z[13].equals(property) && (flaxchat.i.a.z[9].equalsIgnoreCase(property) || (!flaxchat.i.a.z[11].equals(property) && (flaxchat.i.a.z[6].equalsIgnoreCase(property) || (!flaxchat.i.a.z[5].equals(property) && (flaxchat.i.a.z[10].equalsIgnoreCase(property) || (!flaxchat.i.a.z[8].equals(property) && (flaxchat.i.a.z[4].equalsIgnoreCase(property) || (!flaxchat.i.a.z[7].equals(property) && b)))))))))));
    }
    
    public static int a(final String s, final int n) {
        try {
            return Integer.parseInt(flaxchat.i.a.b.getProperty(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String a() {
        return a(flaxchat.i.a.z[14]);
    }
    
    public static String b() {
        return a(flaxchat.i.a.z[0]);
    }
    
    public static Point b(final String s) {
        return c(a(s));
    }
    
    public static Point c(final String s) {
        final int g = flaxchat.i.b.g;
        final Point point = new Point(0, 0);
        if (s == null) {
            return point;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
        final boolean b = false;
        while (true) {
            Label_0112: {
                if (g == 0) {
                    break Label_0112;
                }
                switch (b) {
                    case 0: {
                        try {
                            point.x = Integer.parseInt(stringTokenizer.nextToken());
                        }
                        catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                    case 1: {
                        try {
                            point.y = Integer.parseInt(stringTokenizer.nextToken());
                        }
                        catch (NumberFormatException ex2) {
                            ex2.printStackTrace();
                        }
                        break;
                    }
                }
            }
            if (!stringTokenizer.hasMoreElements()) {
                return point;
            }
            continue;
        }
    }
    
    public static void a(final String s, final String s2) {
        ((Hashtable<String, String>)flaxchat.i.a.b).put(s, s2);
    }
    
    public static String[] c() {
        if (!a(flaxchat.i.a.z[2], true)) {
            return null;
        }
        if (flaxchat.i.a.c == null) {
            flaxchat.i.a.c = p.a(a(flaxchat.i.a.z[3]), ",");
        }
        return flaxchat.i.a.c;
    }
    
    static {
        flaxchat.i.a.z = new String[] { z(z("aCPz2tGUpw@")), z(z("vmo")), z(z("[OW{`W\u007fVfvAgw")), z(z("[OW{`W\u007fVfvA")), z(z("A]Kq")), z(z("QIWww^")), z(z("]C")), z(z("\\GIq")), z(z("\\GI")), z(z("FZLq")), z(z("KMI")), z(z("TIUgw")), z(z("KMJ")), z(z("\\G")), z(z("b@V`}\u0012nVxvWZ")), z(z("AMM`{\\O\u0017`jF")), z(z("TAUq(\u001d")), z(z("\u001dZ\\g}GZZqa\u001d")), z(z("\u001c\u0006e:")) };
        flaxchat.i.a.b = new Properties();
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0012';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '2';
                    break;
                }
                case 1: {
                    c2 = '(';
                    break;
                }
                case 2: {
                    c2 = '9';
                    break;
                }
                case 3: {
                    c2 = '\u0014';
                    break;
                }
                default: {
                    c2 = '\u0012';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
