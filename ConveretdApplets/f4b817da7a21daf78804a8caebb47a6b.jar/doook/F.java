// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class F
{
    public static final int e;
    public static int f;
    public static int a;
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean i;
    public static boolean e;
    public static boolean f;
    
    static {
        F.a = false;
        F.b = false;
        F.c = false;
        F.d = false;
        F.i = false;
        F.e = false;
        F.f = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int a = 0;
        if ("1.02".equals(property)) {
            a = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        a += Integer.parseInt(property.substring(n)) << n2 * 8;
                    }
                    else {
                        a += Integer.parseInt(property.substring(n, index)) << n2 * 8;
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        F.a = a;
        if (lowerCase.startsWith("windows")) {
            F.c = true;
            if (lowerCase.startsWith("windows nt")) {
                F.d = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                F.e = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                F.i = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            F.b = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            F.f = true;
        }
        if (F.b) {
            e = 3;
        }
        else {
            e = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            F.f = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            F.f = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            F.f = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            F.f = 4;
        }
        else {
            F.f = -1;
        }
        F.a = (F.f != 1 || !F.b || (F.a & 0xFFFFFF00) != 0x10100);
    }
}
