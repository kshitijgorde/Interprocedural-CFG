// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class v
{
    public static void a(final int n, final int n2, final int n3, final int n4, final af af, final af af2) {
        if (n4 == 0) {
            if (n < 197) {
                af.a = new Integer((n + 2) / 3 + 19);
                af2.a = new Integer(n - af.a * 3 + 58);
            }
            else {
                af.a = new Integer(n - 112);
                af2.a = new Integer(0);
            }
        }
        else {
            int n5 = af.a - 5;
            if (n5 < n2) {
                n5 = n2;
            }
            if (n5 + 9 > n3) {
                n5 = n3 - 9;
            }
            final int n6 = (n + 2) / 3 - 1;
            af.a = new Integer(n6 + n5);
            af2.a = new Integer(n - 2 - n6 * 3);
        }
    }
}
