// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bM
{
    private static String q;
    private static String w;
    private static String e;
    private static String r;
    private static String t;
    private static String y;
    
    public static String[] q() {
        final String[] array;
        (array = new String[6])[0] = bM.q;
        array[1] = bM.w;
        array[2] = bM.e;
        array[3] = bM.r;
        array[4] = bM.t;
        array[5] = bM.y;
        return array;
    }
    
    public static int q(final String s) {
        if (s.equals(bM.q)) {
            return 1;
        }
        if (s.equals(bM.w)) {
            return 2;
        }
        if (s.equals(bM.e)) {
            return 3;
        }
        if (s.equals(bM.r)) {
            return 4;
        }
        if (s.equals(bM.t)) {
            return 5;
        }
        if (s.equals(bM.y)) {
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
    
    public static cr q(final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n, final int n2, final int n3) {
        final cr cr;
        (cr = new cr(4198480, 1)).w = -1;
        cr.q = -1;
        cr.q(0, 0, b);
        if (cu.q()) {
            cr.q(0, 1, b2);
            cr.q(0, 2, b3);
            cr.q(0, 3, b4);
        }
        cr.q(0, 0, n << 16 | n2);
        cr.q(0, 1, n3);
        return cr;
    }
    
    static {
        bM.q = ak.q("online");
        bM.w = ak.q("busy");
        bM.e = ak.q("be right back");
        bM.r = ak.q("away");
        bM.t = ak.q("in a call");
        bM.y = ak.q("out to lunch");
        ak.q("Visible");
        ak.q("Invisible");
        ak.q("Ghost");
    }
}
