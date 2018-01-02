// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bQ
{
    private static String q;
    private static String w;
    private static String e;
    public static boolean q;
    
    public static void q(final bI bi) {
        final bj bj = new bj(Integer.MIN_VALUE, bQ.q);
        final bj bj2 = new bj(-2147483647, bQ.w);
        final bj bj3 = new bj(-2147483646, bQ.e);
        bj.q = bi.e("securityIcons/" + bj.getName(), true);
        bj2.q = bi.e("securityIcons/" + bj2.getName(), true);
        bj3.q = bi.e("securityIcons/" + bj3.getName(), true);
        bi.w.q(bj);
        bi.w.q(bj2);
        bi.w.q(bj3);
        bQ.q = true;
    }
    
    static {
        bQ.q = "sec_warning.gif";
        bQ.w = "sec_notice.gif";
        bQ.e = "sec_info.gif";
        bQ.q = false;
    }
}
