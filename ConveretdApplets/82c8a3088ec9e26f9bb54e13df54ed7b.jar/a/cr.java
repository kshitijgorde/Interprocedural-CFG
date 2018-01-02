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
        final av av = new av(Integer.MIN_VALUE, cr.q);
        final av av2 = new av(-2147483647, cr.w);
        final av av3 = new av(-2147483646, cr.e);
        av.q = w.w("securityIcons/" + av.o, true);
        av2.q = w.w("securityIcons/" + av2.o, true);
        av3.q = w.w("securityIcons/" + av3.o, true);
        w.w.q(av);
        w.w.q(av2);
        w.w.q(av3);
        cr.q = true;
    }
    
    static {
        cr.q = "sec_warning.gif";
        cr.w = "sec_notice.gif";
        cr.e = "sec_info.gif";
        cr.q = false;
    }
}
