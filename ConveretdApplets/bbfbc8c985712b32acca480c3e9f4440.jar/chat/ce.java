// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class ce
{
    public static final int a;
    public static int b;
    public static int c;
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    
    static {
        ce.a = false;
        ce.c = false;
        ce.d = false;
        ce.b = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int c = 0;
        if ("1.02".equals(property)) {
            c = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        c += Integer.parseInt(property.substring(n)) << (n2 << 3);
                    }
                    else {
                        c += Integer.parseInt(property.substring(n, index)) << (n2 << 3);
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        if ((ce.c = c) > 66048) {
            ce.d = true;
        }
        if (lowerCase.startsWith("windows")) {
            ce.c = true;
            if (!lowerCase.startsWith("windows nt") && !lowerCase.startsWith("windows 98") && lowerCase.startsWith("windows 95")) {}
        }
        else if (lowerCase.startsWith("mac")) {
            ce.b = true;
        }
        if (ce.b) {
            a = 3;
        }
        else {
            a = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            ce.b = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            ce.b = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            ce.b = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            ce.b = 4;
        }
        else {
            ce.b = -1;
        }
        ce.a = (ce.b != 1 || !ce.b || (ce.c & 0xFFFFFF00) != 0x10100);
    }
}
