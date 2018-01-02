// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ar
{
    private static g a;
    
    public static String b(final String s) {
        if (ar.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String b = ar.a.b(s2);
        if (b == null) {
            return new String(s2);
        }
        return b;
    }
    
    public ar(final g a) {
        ar.a = a;
    }
}
