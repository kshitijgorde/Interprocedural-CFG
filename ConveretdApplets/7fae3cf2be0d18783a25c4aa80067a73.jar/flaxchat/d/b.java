// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.util.Properties;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import flaxchat.a.e;
import java.awt.Color;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Component;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;

public class b
{
    private static Hashtable a;
    private static final Hashtable b;
    private static final Hashtable c;
    private static boolean d;
    private static Vector e;
    private static Vector f;
    public static int g;
    private static String[] z;
    
    public static void a() {
        try {
            flaxchat.d.b.a = a(new URL(flaxchat.d.a.a, String.valueOf(flaxchat.d.a.a()) + flaxchat.d.b.z[11]));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void a(final Component component) {
        flaxchat.d.d.a(component);
        flaxchat.d.d.a();
        flaxchat.d.c.a();
        flaxchat.a.c.b().setIconImage(flaxchat.d.d.v());
        component.repaint();
    }
    
    public static Hashtable a(final String s) {
        if (flaxchat.d.b.a == null) {
            return flaxchat.d.b.b;
        }
        final Hashtable hashtable = flaxchat.d.b.a.get(s);
        if (hashtable == null) {
            return flaxchat.d.b.b;
        }
        return hashtable;
    }
    
    public static String a(final String s, final String s2) {
        return a(s).get(s2);
    }
    
    public static Vector b() {
        if (!flaxchat.d.b.d) {
            b(c(flaxchat.d.b.z[13]));
        }
        return flaxchat.d.b.f;
    }
    
    public static Vector c() {
        if (!flaxchat.d.b.d) {
            b(c(flaxchat.d.b.z[13]));
        }
        return flaxchat.d.b.e;
    }
    
    private static void b(final String s) {
        final int g = flaxchat.d.b.g;
        flaxchat.d.b.e.removeAllElements();
        flaxchat.d.b.f.removeAllElements();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (true) {
            Label_0116: {
                if (g == 0) {
                    break Label_0116;
                }
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ":");
                if (stringTokenizer2.hasMoreTokens()) {
                    flaxchat.d.b.e.addElement(stringTokenizer2.nextElement());
                    if (stringTokenizer2.hasMoreTokens()) {
                        flaxchat.d.b.f.addElement(new Integer(b(stringTokenizer2.nextToken(), -1)));
                        if (g == 0) {
                            break Label_0116;
                        }
                    }
                    flaxchat.d.b.f.addElement(new Integer(-1));
                }
            }
            if (!stringTokenizer.hasMoreElements()) {
                flaxchat.d.b.d = true;
                return;
            }
            continue;
        }
    }
    
    public static String c(final String s) {
        return a(flaxchat.d.b.z[1], s);
    }
    
    public static int a(final String s, final int n) {
        return b(c(s), n);
    }
    
    public static int b(final String s, final int n) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static boolean a(final String s, final boolean b) {
        try {
            return Boolean.valueOf(c(s));
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public static String b(String a, final String s) {
        a = a(flaxchat.d.b.z[1], a);
        if (a == null) {
            return s;
        }
        return "\u0003" + a;
    }
    
    public static Font d(final String s) {
        return c(s, flaxchat.d.b.z[12]);
    }
    
    public static Font c(String s, final String s2) {
        final int g = flaxchat.d.b.g;
        if (s == null) {
            s = s2;
        }
        final Font font = flaxchat.d.b.c.get(flaxchat.d.b.z[8] + s);
        if (font != null) {
            return font;
        }
        String s3 = c(s);
        if (s3 == null || s3.trim().length() == 0) {
            s3 = c(s2);
        }
        Font font2;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s3, ",");
            final String trim = stringTokenizer.nextToken().trim();
            final String trim2 = stringTokenizer.nextToken().trim();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            final StringTokenizer stringTokenizer2 = new StringTokenizer(trim2, "+");
            int n = 0;
            while (true) {
                Label_0208: {
                    if (g == 0) {
                        break Label_0208;
                    }
                    final String trim3 = stringTokenizer2.nextToken().trim();
                    if (flaxchat.d.b.z[7].equalsIgnoreCase(trim3)) {
                        ++n;
                        if (g == 0) {
                            break Label_0208;
                        }
                    }
                    if (flaxchat.d.b.z[10].equalsIgnoreCase(trim3)) {
                        n += 2;
                        if (g == 0) {
                            break Label_0208;
                        }
                    }
                    if (flaxchat.d.b.z[9].equalsIgnoreCase(trim3)) {
                        n += 0;
                    }
                }
                if (stringTokenizer2.hasMoreElements()) {
                    continue;
                }
                break;
            }
            font2 = new Font(trim, n, int1);
        }
        catch (Exception ex) {
            font2 = new Font(flaxchat.d.b.z[6], 0, 14);
        }
        catch (Error error) {
            font2 = new Font(flaxchat.d.b.z[6], 0, 14);
        }
        flaxchat.d.b.c.put(flaxchat.d.b.z[8] + s, font2);
        return font2;
    }
    
    public static Color a(final String s, final Color color) {
        final int g = flaxchat.d.b.g;
        final Color color2 = flaxchat.d.b.c.get(flaxchat.d.b.z[14] + s);
        if (color2 != null) {
            return color2;
        }
        final String c = c(s);
        Color e = null;
        Label_0217: {
            try {
                final String trim = c.trim();
                if (trim.startsWith("#")) {
                    e = e(trim);
                    if (g == 0) {
                        break Label_0217;
                    }
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(trim, ",");
                int n = 0;
                int int1 = 0;
                int int2 = 0;
                int int3 = 0;
                while (true) {
                    Label_0183: {
                        if (g == 0) {
                            break Label_0183;
                        }
                        Label_0164: {
                            switch (n) {
                                case 0: {
                                    int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                                    if (g != 0) {
                                        break Label_0164;
                                    }
                                    break;
                                }
                                case 1: {
                                    int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                                    if (g != 0) {
                                        break Label_0164;
                                    }
                                    break;
                                }
                                case 2: {
                                    int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
                                    break;
                                }
                            }
                        }
                        ++n;
                    }
                    if (stringTokenizer.hasMoreElements()) {
                        continue;
                    }
                    break;
                }
                e = new Color(int1, int2, int3);
            }
            catch (Exception ex) {
                e = color;
            }
            catch (Error error) {
                e = color;
            }
        }
        flaxchat.d.b.c.put(flaxchat.d.b.z[14] + s, e);
        return e;
    }
    
    public static Color e(String s) {
        s = s.trim();
        if (s.charAt(0) == '#') {
            s = s.substring(1);
        }
        String s2 = "0";
        String s3 = "0";
        String s4 = "0";
        if (s.length() == 6) {
            s2 = String.valueOf(s.charAt(0)) + "" + s.charAt(1);
            s3 = String.valueOf(s.charAt(2)) + "" + s.charAt(3);
            s4 = String.valueOf(s.charAt(4)) + "" + s.charAt(5);
            if (flaxchat.d.b.g == 0) {
                return new Color(c(s2, 16), c(s3, 16), c(s4, 16));
            }
        }
        if (s.length() == 3) {
            s2 = String.valueOf(s.charAt(1));
            s3 = String.valueOf(s.charAt(2));
            s4 = String.valueOf(s.charAt(3));
        }
        return new Color(c(s2, 16), c(s3, 16), c(s4, 16));
    }
    
    public static int c(final String s, final int n) throws NumberFormatException {
        int g = flaxchat.d.b.g;
        if (s == null) {
            throw new NumberFormatException(flaxchat.d.b.z[4]);
        }
        if (n < 2) {
            throw new NumberFormatException(flaxchat.d.b.z[3] + n + flaxchat.d.b.z[5]);
        }
        if (n > 36) {
            throw new NumberFormatException(flaxchat.d.b.z[3] + n + flaxchat.d.b.z[2]);
        }
        int n2 = 0;
        boolean b = false;
        int n3 = 0;
        final int length = s.length();
        if (length > 0) {
            int n4 = 0;
            Label_0149: {
                if (s.charAt(0) == '-') {
                    b = true;
                    n4 = Integer.MIN_VALUE;
                    ++n3;
                    if (g == 0) {
                        break Label_0149;
                    }
                }
                n4 = -2147483647;
            }
            while (true) {
                Label_0238: {
                    if (n3 >= length) {
                        break Label_0238;
                    }
                    final int digit = Character.digit(s.charAt(n3++), n);
                    if (digit < 0) {
                        throw f(s);
                    }
                    n2 = -digit;
                    if (g == 0) {
                        break Label_0238;
                    }
                    final int digit2 = Character.digit(s.charAt(n3++), n);
                    if (digit2 < 0) {
                        throw f(s);
                    }
                    final int n5 = n2 * n;
                    if (n5 < n4 + digit2) {
                        throw f(s);
                    }
                    n2 = n5 - digit2;
                }
                if (n3 < length) {
                    continue;
                }
                break;
            }
            if (g == 0) {
                if (!b) {
                    final int n6 = -n2;
                    if (flaxchat.a.e.c != 0) {
                        flaxchat.d.b.g = ++g;
                    }
                    return n6;
                }
                if (n3 > 1) {
                    return n2;
                }
                throw f(s);
            }
        }
        throw f(s);
    }
    
    private static NumberFormatException f(final String s) {
        return new NumberFormatException(flaxchat.d.b.z[0] + s + "\"");
    }
    
    private static Hashtable a(final URL url) throws IOException {
        return a(url.openStream());
    }
    
    private static Hashtable a(final InputStream inputStream) throws IOException {
        final int g = flaxchat.d.b.g;
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final Hashtable<Object, Hashtable<String, String>> hashtable = new Hashtable<Object, Hashtable<String, String>>();
        Hashtable<String, String> hashtable2 = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            final String[] g2 = g(line);
            if (g2 != null && g2.length != 0) {
                if (g2.length == 1) {
                    hashtable2 = hashtable.get(g2[0]);
                    if (hashtable2 != null) {
                        continue;
                    }
                    hashtable2 = new Hashtable<String, String>();
                    hashtable.put(g2[0], hashtable2);
                    if (g == 0) {
                        continue;
                    }
                }
                if (hashtable2 == null) {
                    continue;
                }
                hashtable2.put(g2[0], g2[1]);
            }
        }
        return hashtable;
    }
    
    private static String[] g(String trim) {
        trim = trim.trim();
        if (trim.startsWith("#")) {
            return null;
        }
        if (trim.startsWith("[")) {
            final int index = trim.indexOf("]");
            if (index == -1) {
                return new String[] { trim.substring(1) };
            }
            return new String[] { trim.substring(1, index) };
        }
        else {
            final int index2 = trim.indexOf(61);
            if (index2 == -1) {
                return new String[] { trim, "" };
            }
            return new String[] { trim.substring(0, index2).trim(), trim.substring(index2 + 1).trim() };
        }
    }
    
    public static Properties b(final URL url) throws IOException {
        final Properties properties = new Properties();
        String line;
        while ((line = new BufferedReader(new InputStreamReader(url.openStream())).readLine()) != null) {
            final int index = line.indexOf(61);
            if (index != -1) {
                ((Hashtable<String, String>)properties).put(line.substring(0, index).trim(), line.substring(index + 1).trim());
            }
        }
        return properties;
    }
    
    static {
        flaxchat.d.b.z = new String[] { z(z("M\u001c\u0019\u007f\u0002e\u0003\u001e+Kx\u0007\u00196\u0005lIK}")), z(z("{\u0001\u000e9\u000ey\u0016\u0005<\u000e")), z(z("+\u0014\u0019:\n\u007f\u0016\u0019\u007f\u001fc\u0012\u0005\u007f(c\u0012\u0019>\b\u007f\u0016\u0019q&J+4\r*O:3")), z(z("y\u0012\u000f6\u0013+")), z(z("e\u0006\u00073")), z(z("+\u001f\u000e,\u0018+\u0007\u0003>\u0005+0\u0003>\u0019j\u0010\u001f:\u0019%>\"\u00114Y2/\u00163")), z(z("O\u001a\n3\u0004l")), z(z("i\u001c\u0007;")), z(z("m\u001c\u0005+F")), z(z("{\u001f\n6\u0005")), z(z("b\u0007\n3\u0002h")), z(z("$\u0000\u00006\u0005%\u0007\u0013+")), z(z("j\u0003\u001b\u0019\u0004e\u0007")), z(z("e\u0006\u000f8\u000eX\u0007\u00196\u0005l")), z(z("h\u001f\u0019r")) };
        b = new Hashtable();
        c = new Hashtable();
        flaxchat.d.b.e = new Vector();
        flaxchat.d.b.f = new Vector();
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'k';
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
                    c2 = '\u000b';
                    break;
                }
                case 1: {
                    c2 = 's';
                    break;
                }
                case 2: {
                    c2 = 'k';
                    break;
                }
                case 3: {
                    c2 = '_';
                    break;
                }
                default: {
                    c2 = 'k';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
