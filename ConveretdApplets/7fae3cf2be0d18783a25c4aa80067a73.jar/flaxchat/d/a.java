// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.util.Hashtable;
import flaxchat.a.q;
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
    private static String[] d;
    private static String[] e;
    private static String[] z;
    
    public static void a(final Applet applet, final URL url) {
        a(applet.getParameter(flaxchat.d.a.z[0]) != null, applet, url);
    }
    
    public static void a(final boolean b, final Applet applet, URL a) {
        if (b) {
            try {
                a = new URL(a.z[17] + new File(new File(a.z[15]).getAbsolutePath()).getParent().replace('\\', '/') + a.z[14]);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            a.a = a;
            a.b = b.b(new URL(a.a, a.z[16]));
            final String parameter = applet.getParameter(a.z[13]);
            if (parameter != null && parameter.trim().length() != 0) {
                ((Hashtable<String, String>)a.b).put(a.z[13], parameter);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        b.a();
    }
    
    public static String a(final String s) {
        return flaxchat.d.a.b.getProperty(s);
    }
    
    public static boolean a(String property, final boolean b) {
        property = flaxchat.d.a.b.getProperty(property);
        return "1".equals(property) || (!"0".equals(property) && (flaxchat.d.a.z[7].equalsIgnoreCase(property) || (!flaxchat.d.a.z[3].equals(property) && (flaxchat.d.a.z[1].equalsIgnoreCase(property) || (!flaxchat.d.a.z[8].equals(property) && (flaxchat.d.a.z[4].equalsIgnoreCase(property) || (!flaxchat.d.a.z[10].equals(property) && (flaxchat.d.a.z[9].equalsIgnoreCase(property) || (!flaxchat.d.a.z[2].equals(property) && (flaxchat.d.a.z[5].equalsIgnoreCase(property) || (!flaxchat.d.a.z[6].equals(property) && b)))))))))));
    }
    
    public static int a(final String s, final int n) {
        try {
            return Integer.parseInt(flaxchat.d.a.b.getProperty(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String a() {
        return a(flaxchat.d.a.z[13]);
    }
    
    public static String b() {
        return a(flaxchat.d.a.z[18]);
    }
    
    public static Point b(final String s) {
        return c(a(s));
    }
    
    public static Point c(final String s) {
        final int g = flaxchat.d.b.g;
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
        ((Hashtable<String, String>)flaxchat.d.a.b).put(s, s2);
    }
    
    public static String[] c() {
        if (!a(flaxchat.d.a.z[12], true)) {
            return null;
        }
        if (flaxchat.d.a.c == null) {
            flaxchat.d.a.c = q.a(a(flaxchat.d.a.z[11]), ",");
        }
        return flaxchat.d.a.c;
    }
    
    public static String[] d() {
        if (flaxchat.d.a.d == null) {
            flaxchat.d.a.d = q.a(a(flaxchat.d.a.z[19]), ",");
        }
        return flaxchat.d.a.d;
    }
    
    public static String[] e() {
        if (!a(flaxchat.d.a.z[20], true)) {
            return null;
        }
        if (flaxchat.d.a.e == null) {
            flaxchat.d.a.e = q.a(a(flaxchat.d.a.z[21]), ",");
        }
        return flaxchat.d.a.e;
    }
    
    static {
        flaxchat.d.a.z = new String[] { z(z(";\u0014G")), z(z("\u000b#dI")), z(z("\u0011>a")), z(z("\u0011>")), z(z("\u0010:")), z(z("\f$cI")), z(z("\u0011>aI")), z(z("\u00064b")), z(z("\u00190}_&")), z(z("\u00064a")), z(z("\u001c0\u007fO&\u0013")), z(z("\u00166\u007fC1\u001a\u0006~^'\f")), z(z("\u00166\u007fC1\u001a\u0006~^'\f\u001e_")), z(z(",:xBc9>}H&\r")), z(z("P#t_,\n#rI0P")), z(z("Q\u007fM\u0002")), z(z("\f4eX*\u00116?X;\u000b")), z(z("\u00198}IyP")), z(z(",>dB'_\u0017~@'\u001a#")), z(z("\u000f#tZ&\u0011%RC.\u00120\u007fH")), z(z("\u001d=~O(\u0016?v")), z(z(">=}C4\u001a5")) };
        flaxchat.d.a.b = new Properties();
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'C';
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
                    c2 = '\u007f';
                    break;
                }
                case 1: {
                    c2 = 'Q';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = ',';
                    break;
                }
                default: {
                    c2 = 'C';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
