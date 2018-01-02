// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class aZ
{
    public static final int a;
    public static int b;
    public static int c;
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    
    static {
        aZ.a = false;
        aZ.c = false;
        aZ.d = false;
        aZ.b = false;
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
        if ((aZ.c = c) > 66048) {
            aZ.d = true;
        }
        if (lowerCase.startsWith("windows")) {
            aZ.c = true;
            if (!lowerCase.startsWith("windows nt") && !lowerCase.startsWith("windows 98") && lowerCase.startsWith("windows 95")) {}
        }
        else if (lowerCase.startsWith("mac")) {
            aZ.b = true;
        }
        if (aZ.b) {
            a = 3;
        }
        else {
            a = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            aZ.b = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            aZ.b = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            aZ.b = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            aZ.b = 4;
        }
        else {
            aZ.b = -1;
        }
        aZ.a = (aZ.b != 1 || !aZ.b || (aZ.c & 0xFFFFFF00) != 0x10100);
    }
}
