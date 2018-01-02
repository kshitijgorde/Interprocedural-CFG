// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cr
{
    private static String q;
    private static String w;
    private static String e;
    public static boolean q;
    
    public static void q(final W w) {
        final aw aw = new aw(Integer.MIN_VALUE, cr.q);
        final aw aw2 = new aw(-2147483647, cr.w);
        final aw aw3 = new aw(-2147483646, cr.e);
        aw.q = w.w("securityIcons/" + aw.o, true);
        aw2.q = w.w("securityIcons/" + aw2.o, true);
        aw3.q = w.w("securityIcons/" + aw3.o, true);
        w.w.q(aw);
        w.w.q(aw2);
        w.w.q(aw3);
        cr.q = true;
    }
    
    static {
        cr.q = "sec_warning.gif";
        cr.w = "sec_notice.gif";
        cr.e = "sec_info.gif";
        cr.q = false;
    }
}
