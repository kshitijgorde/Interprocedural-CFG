// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bo
{
    private static String q;
    private static String w;
    private static String e;
    private static String r;
    private static String t;
    private static String y;
    
    public static String[] q() {
        final String[] array;
        (array = new String[6])[0] = bo.q;
        array[1] = bo.w;
        array[2] = bo.e;
        array[3] = bo.r;
        array[4] = bo.t;
        array[5] = bo.y;
        return array;
    }
    
    public static int q(final String s) {
        if (s.equals(bo.q)) {
            return 1;
        }
        if (s.equals(bo.w)) {
            return 2;
        }
        if (s.equals(bo.e)) {
            return 3;
        }
        if (s.equals(bo.r)) {
            return 4;
        }
        if (s.equals(bo.t)) {
            return 5;
        }
        if (s.equals(bo.y)) {
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
    
    public static cJ q(final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n, final int n2, final int n3) {
        final cJ cj;
        (cj = new cJ(4198480, 1)).w = -1;
        cj.q = -1;
        cj.q(0, 0, b);
        if (a.q()) {
            cj.q(0, 1, b2);
            cj.q(0, 2, b3);
            cj.q(0, 3, b4);
        }
        cj.q(0, 0, n << 16 | n2);
        cj.q(0, 1, n3);
        return cj;
    }
    
    static {
        bo.q = cv.q("online");
        bo.w = cv.q("busy");
        bo.e = cv.q("be right back");
        bo.r = cv.q("away");
        bo.t = cv.q("in a call");
        bo.y = cv.q("out to lunch");
        cv.q("Visible");
        cv.q("Invisible");
        cv.q("Ghost");
    }
}
