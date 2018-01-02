// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class f
{
    public static final int g;
    public static int h;
    public static int i;
    public static boolean h;
    public static boolean d;
    public static boolean e;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static boolean l;
    
    static {
        f.h = false;
        f.d = false;
        f.e = false;
        f.i = false;
        f.j = false;
        f.k = false;
        f.l = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int i = 0;
        if ("1.02".equals(property)) {
            i = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        i += Integer.parseInt(property.substring(n)) << n2 * 8;
                    }
                    else {
                        i += Integer.parseInt(property.substring(n, index)) << n2 * 8;
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        f.i = i;
        if (lowerCase.startsWith("windows")) {
            f.e = true;
            if (lowerCase.startsWith("windows nt")) {
                f.i = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                f.k = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                f.j = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            f.d = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            f.l = true;
        }
        if (f.d) {
            g = 3;
        }
        else {
            g = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            f.h = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            f.h = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            f.h = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            f.h = 4;
        }
        else {
            f.h = -1;
        }
        f.h = (f.h != 1 || !f.d || (f.i & 0xFFFFFF00) != 0x10100);
    }
}
