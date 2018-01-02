// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ao
{
    private static v a;
    
    public static String e(final String s) {
        if (ao.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String e = ao.a.e(s2);
        if (e == null) {
            return new String(s2);
        }
        return e;
    }
    
    public ao(final v a) {
        ao.a = a;
    }
}
