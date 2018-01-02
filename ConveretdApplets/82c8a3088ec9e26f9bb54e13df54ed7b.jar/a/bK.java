// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bK
{
    private static String q;
    private static String w;
    private static String e;
    private static String r;
    private static String t;
    private static String y;
    
    public static String[] q() {
        final String[] array;
        (array = new String[6])[0] = bK.q;
        array[1] = bK.w;
        array[2] = bK.e;
        array[3] = bK.r;
        array[4] = bK.t;
        array[5] = bK.y;
        return array;
    }
    
    public static int q(final String s) {
        if (s.equals(bK.q)) {
            return 1;
        }
        if (s.equals(bK.w)) {
            return 2;
        }
        if (s.equals(bK.e)) {
            return 3;
        }
        if (s.equals(bK.r)) {
            return 4;
        }
        if (s.equals(bK.t)) {
            return 5;
        }
        if (s.equals(bK.y)) {
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
    
    public static cp q(final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n, final int n2, final int n3) {
        final cp cp;
        (cp = new cp(4198480, 1)).w = -1;
        cp.q = -1;
        cp.q(0, 0, b);
        if (cs.q()) {
            cp.q(0, 1, b2);
            cp.q(0, 2, b3);
            cp.q(0, 3, b4);
        }
        cp.q(0, 0, n << 16 | n2);
        cp.q(0, 1, n3);
        return cp;
    }
    
    static {
        bK.q = ak.q("online");
        bK.w = ak.q("busy");
        bK.e = ak.q("be right back");
        bK.r = ak.q("away");
        bK.t = ak.q("in a call");
        bK.y = ak.q("out to lunch");
        ak.q("Visible");
        ak.q("Invisible");
        ak.q("Ghost");
    }
}
