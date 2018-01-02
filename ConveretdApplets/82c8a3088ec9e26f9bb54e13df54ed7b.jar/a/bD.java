// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bD
{
    public static final int q;
    public static int w;
    public static int e;
    public static boolean q;
    public static boolean w;
    public static boolean e;
    
    static {
        bD.q = false;
        bD.e = false;
        bD.w = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int e = 0;
        if ("1.02".equals(property)) {
            e = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        e += Integer.parseInt(property.substring(n)) << (n2 << 3);
                    }
                    else {
                        e += Integer.parseInt(property.substring(n, index)) << (n2 << 3);
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        bD.e = e;
        if (lowerCase.startsWith("windows")) {
            bD.e = true;
            if (!lowerCase.startsWith("windows nt") && !lowerCase.startsWith("windows 98") && lowerCase.startsWith("windows 95")) {}
        }
        else if (lowerCase.startsWith("mac")) {
            bD.w = true;
        }
        if (bD.w) {
            q = 3;
        }
        else {
            q = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            bD.w = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            bD.w = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            bD.w = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            bD.w = 4;
        }
        else {
            bD.w = -1;
        }
        bD.q = (bD.w != 1 || !bD.w || (bD.e & 0xFFFFFF00) != 0x10100);
    }
}
