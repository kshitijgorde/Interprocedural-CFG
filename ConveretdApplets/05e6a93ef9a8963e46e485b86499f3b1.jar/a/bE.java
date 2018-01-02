// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bE
{
    public static final int q;
    public static int w;
    public static int e;
    public static boolean q;
    public static boolean w;
    public static boolean e;
    
    static {
        bE.q = false;
        bE.e = false;
        bE.w = false;
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
        bE.e = e;
        if (lowerCase.startsWith("windows")) {
            bE.e = true;
            if (!lowerCase.startsWith("windows nt") && !lowerCase.startsWith("windows 98") && lowerCase.startsWith("windows 95")) {}
        }
        else if (lowerCase.startsWith("mac")) {
            bE.w = true;
        }
        if (bE.w) {
            q = 3;
        }
        else {
            q = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            bE.w = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            bE.w = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            bE.w = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            bE.w = 4;
        }
        else {
            bE.w = -1;
        }
        bE.q = (bE.w != 1 || !bE.w || (bE.e & 0xFFFFFF00) != 0x10100);
    }
}
