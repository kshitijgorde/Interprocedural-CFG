// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bs
{
    public static final int c;
    public static int t;
    public static int g;
    public static boolean h;
    public static boolean d;
    public static boolean e;
    public static boolean b;
    public static boolean y;
    public static boolean r;
    public static boolean q;
    
    static {
        bs.h = false;
        bs.d = false;
        bs.e = false;
        bs.b = false;
        bs.y = false;
        bs.r = false;
        bs.q = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int g = 0;
        if ("1.02".equals(property)) {
            g = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        g += Integer.parseInt(property.substring(n)) << n2 * 8;
                    }
                    else {
                        g += Integer.parseInt(property.substring(n, index)) << n2 * 8;
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        bs.g = g;
        if (lowerCase.startsWith("windows")) {
            bs.e = true;
            if (lowerCase.startsWith("windows nt")) {
                bs.b = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                bs.r = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                bs.y = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            bs.d = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            bs.q = true;
        }
        if (bs.d) {
            c = 3;
        }
        else {
            c = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            bs.t = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            bs.t = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            bs.t = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            bs.t = 4;
        }
        else {
            bs.t = -1;
        }
        bs.h = (bs.t != 1 || !bs.d || (bs.g & 0xFFFFFF00) != 0x10100);
    }
}
