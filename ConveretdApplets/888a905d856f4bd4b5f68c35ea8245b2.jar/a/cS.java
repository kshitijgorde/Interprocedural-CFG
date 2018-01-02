// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cS
{
    private static String q;
    private static String w;
    private static String e;
    private static String r;
    private static String t;
    private static String y;
    
    public static String[] q() {
        final String[] array;
        (array = new String[6])[0] = cS.q;
        array[1] = cS.w;
        array[2] = cS.e;
        array[3] = cS.r;
        array[4] = cS.t;
        array[5] = cS.y;
        return array;
    }
    
    public static int q(final String s) {
        if (s.equals(cS.q)) {
            return 1;
        }
        if (s.equals(cS.w)) {
            return 2;
        }
        if (s.equals(cS.e)) {
            return 3;
        }
        if (s.equals(cS.r)) {
            return 4;
        }
        if (s.equals(cS.t)) {
            return 5;
        }
        if (s.equals(cS.y)) {
            return 6;
        }
        return 0;
    }
    
    public static boolean q(final String s) {
        for (int i = 0; i < 6; ++i) {
            if (s.equalsIgnoreCase(q()[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static dI q(final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n, final int n2, final int n3) {
        final dI di;
        (di = new dI(4198480, 1)).w = -1;
        di.q = -1;
        di.q(0, 0, b);
        if (dN.q()) {
            di.q(0, 1, b2);
            di.q(0, 2, b3);
            di.q(0, 3, b4);
        }
        di.q(0, 0, n << 16 | n2);
        di.q(0, 1, n3);
        return di;
    }
    
    static {
        cS.q = be.w("online");
        cS.w = be.w("busy");
        cS.e = be.w("be right back");
        cS.r = be.w("away");
        cS.t = be.w("in a call");
        cS.y = be.w("out to lunch");
        be.w("Visible");
        be.w("Invisible");
        be.w("Ghost");
    }
}
