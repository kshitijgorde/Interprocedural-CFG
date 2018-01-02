// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dM
{
    private static String q;
    private static String w;
    private static String e;
    public static boolean q;
    
    public static void q(final ap ap) {
        final aZ az = new aZ(Integer.MIN_VALUE, dM.q);
        final aZ az2 = new aZ(-2147483647, dM.w);
        final aZ az3 = new aZ(-2147483646, dM.e);
        az.q = ap.w("securityIcons/" + az.a, true);
        az2.q = ap.w("securityIcons/" + az2.a, true);
        az3.q = ap.w("securityIcons/" + az3.a, true);
        ap.p.q(az);
        ap.p.q(az2);
        ap.p.q(az3);
        dM.q = true;
    }
    
    static {
        dM.q = "sec_warning.gif";
        dM.w = "sec_notice.gif";
        dM.e = "sec_info.gif";
        dM.q = false;
    }
}
