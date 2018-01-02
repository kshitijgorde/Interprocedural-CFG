// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.a;

public final class a
{
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    
    private static int a(final int n, final int n2, final int n3) {
        return n2 - ((n2 - (n & n2)) * n3 / 100 & n2);
    }
    
    private static String a(int a, final int n, final double n2) {
        if ((a += (int)0.5) > 100) {
            a = 100;
        }
        if (a < 0) {
            a = 0;
        }
        final int a2 = a(n, 16711680, a);
        final int a3 = a(n, 65280, a);
        a = a(n, 255, a);
        final StringBuffer sb;
        (sb = new StringBuffer(20)).append("#");
        sb.append(Integer.toHexString(a2 + a3 + a));
        return sb.toString();
    }
    
    public static String a(final double n, final int n2, final int n3, final double n4, final double n5) {
        if (n > 0.0) {
            final double n6 = n4 * n2;
            return a((int)((n - n6) / (n5 * n2 - n6) * 100.0 + 0.5), COM.NextBus.a.a.o, 1.0);
        }
        final double n7 = n4 * n3;
        return a((int)((-n - n7) / (n5 * n3 - n7) * 100.0 + 0.5), COM.NextBus.a.a.p, 1.0);
    }
    
    static {
        COM.NextBus.a.a.a = 13395507;
        COM.NextBus.a.a.b = 15723745;
        COM.NextBus.a.a.c = 14474424;
        COM.NextBus.a.a.d = 13947080;
        COM.NextBus.a.a.e = 5066061;
        COM.NextBus.a.a.k = 16748688;
        COM.NextBus.a.a.l = 16755200;
        COM.NextBus.a.a.m = 16711807;
        COM.NextBus.a.a.n = 0;
        COM.NextBus.a.a.o = COM.NextBus.a.a.k;
        COM.NextBus.a.a.p = 16772976;
        COM.NextBus.a.a.f = 16777215;
        COM.NextBus.a.a.g = 12632256;
        COM.NextBus.a.a.h = 16777215;
        COM.NextBus.a.a.i = 0;
        COM.NextBus.a.a.j = 16711680;
    }
}
