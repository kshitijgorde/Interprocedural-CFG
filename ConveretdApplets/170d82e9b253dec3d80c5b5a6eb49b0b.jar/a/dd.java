// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dd
{
    private static String q;
    private static String w;
    private static String e;
    public static boolean q;
    
    public static void q(final cV cv) {
        final cm cm = new cm(Integer.MIN_VALUE, dd.q);
        final cm cm2 = new cm(-2147483647, dd.w);
        final cm cm3 = new cm(-2147483646, dd.e);
        cm.q = cv.e("securityIcons/" + cm.getName(), true);
        cm2.q = cv.e("securityIcons/" + cm2.getName(), true);
        cm3.q = cv.e("securityIcons/" + cm3.getName(), true);
        cv.w.q(cm);
        cv.w.q(cm2);
        cv.w.q(cm3);
        dd.q = true;
    }
    
    static {
        dd.q = "sec_warning.gif";
        dd.w = "sec_notice.gif";
        dd.e = "sec_info.gif";
        dd.q = false;
    }
}
